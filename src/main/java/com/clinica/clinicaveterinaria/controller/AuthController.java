package com.clinica.clinicaveterinaria.controller;

import com.clinica.clinicaveterinaria.model.LoginResponse;
import com.clinica.clinicaveterinaria.security.TokenBlacklist;
import com.clinica.clinicaveterinaria.service.EmployeeService;
import com.clinica.clinicaveterinaria.util.PropertiesUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {


  @Autowired
  private EmployeeService employeeService;

  private static final String SECRET_KEY = PropertiesUtil.getJwtSecretKey();
  private static final long TOKEN_VALIDITY = 3600000;

  private static final int TOKEN_PREFIX_LENGTH = 7;


  @PostMapping("/user/logout")
  public ResponseEntity<?> logout(
      @RequestHeader(value = "Authorization") final String token) {
    String jwtToken = token.substring(TOKEN_PREFIX_LENGTH);
    TokenBlacklist.invalidateToken(jwtToken);
    return ResponseEntity.ok().body("Session has been closed successfully.");
  }

  @PostMapping("/user/login")
  public ResponseEntity<LoginResponse> login(
      @RequestParam("user") final String username,
      @RequestParam("password") final String pwd) {
    return employeeService.validateUserLogin(username, pwd).map(user -> {
      String token = getJwtToken(username, "" + user.getId());
      LoginResponse response = new LoginResponse();
      response.setId(user.getId());
      response.setName(user.getName());
      response.setLogin(user.getEmail());
      response.setToken(token);
      return ResponseEntity.ok(response);
    }).orElse(ResponseEntity.badRequest().build());
  }

  private String getJwtToken(final String username, final String id) {
    List<GrantedAuthority> grantedAuthorities =
        AuthorityUtils.commaSeparatedStringToAuthorityList(
            "ROLE_USER");

    String token = Jwts.builder().setId(id).setSubject(username)
        .claim("authorities", grantedAuthorities.stream()
            .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(
            new Date(System.currentTimeMillis()
                + TOKEN_VALIDITY)) // Token valid for 1 hour
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes()).compact();

    return "Bearer " + token;
  }
}
