package com.clinica.clinicaveterinaria.security;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBlacklist {

  private static final ConcurrentHashMap<String, Boolean> INVALID_TOKENS
      = new ConcurrentHashMap<>();

  public static boolean isTokenInvalidated(final String token) {
    return INVALID_TOKENS.containsKey(token);
  }
  public static void invalidateToken(final String token) {
    INVALID_TOKENS.put(token, true);
  }
}
