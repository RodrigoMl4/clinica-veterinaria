package com.clinica.clinicaveterinaria.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import com.clinica.clinicaveterinaria.util.PropertiesUtil;
@Service
public class TokenService {

  private final String secretKey = PropertiesUtil.getJwtSecretKey();

  public String getUserIdFromToken(final String token) {
    Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes())
        .parseClaimsJws(token)
        .getBody();
    return claims.getId();
  }
}
