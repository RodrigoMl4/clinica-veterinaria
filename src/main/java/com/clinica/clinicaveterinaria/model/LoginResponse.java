package com.clinica.clinicaveterinaria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the response after a successful user login, encapsulating user
 * details and authentication token.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

  private int id;

  private String name;

  private String login;

  private String token;

}
