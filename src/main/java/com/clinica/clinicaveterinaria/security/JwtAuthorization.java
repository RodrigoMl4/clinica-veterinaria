package com.clinica.clinicaveterinaria.security;

import com.clinica.clinicaveterinaria.util.PropertiesUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtAuthorization extends OncePerRequestFilter {

  private static final String HEADER = "Authorization";

  private static final String PREFIX = "Bearer ";

  private final String secretKey = PropertiesUtil.getJwtSecretKey();

  private Claims validateToken(final HttpServletRequest request) {
    String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
    if (TokenBlacklist.isTokenInvalidated(jwtToken)) {
      return null;
    }
    return Jwts.parser().setSigningKey(secretKey.getBytes())
        .parseClaimsJws(jwtToken).getBody();
  }

  private void setUpSpringAuthentication(final Claims claims) {
    if (claims == null) {
      SecurityContextHolder.clearContext();
      return;
    }
    List<String> authorities = (List<String>) claims.get("authorities");

    UsernamePasswordAuthenticationToken auth =
        new UsernamePasswordAuthenticationToken(
            claims.getSubject(), null,
            authorities.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  private boolean checkJWTToken(final HttpServletRequest request,
      final HttpServletResponse response) {
    String authenticationHeader = request.getHeader(HEADER);
    return authenticationHeader != null && authenticationHeader.startsWith(
        PREFIX);
  }

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
      final HttpServletResponse response, final FilterChain chain)
      throws ServletException, IOException {
    try {
      if (checkJWTToken(request, response)) {
        Claims claims = validateToken(request);
        if (claims != null && claims.get("authorities") != null) {
          setUpSpringAuthentication(claims);
        } else {
          SecurityContextHolder.clearContext();
        }
      } else {
        SecurityContextHolder.clearContext();
      }
      chain.doFilter(request, response);
    } catch (ExpiredJwtException | UnsupportedJwtException
             | MalformedJwtException e) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }
  }
}
