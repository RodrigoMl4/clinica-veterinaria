package com.clinica.clinicaveterinaria.util;

import java.util.ResourceBundle;

/**
 * This class provides utilities for accessing properties from a resource
 * bundle.
 */
public class PropertiesUtil {

  /**
   * The resource bundle instance used to access properties.
   */
  private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(
      "application");

  /**
   * Retrieves the value of the "jwt.secretKey" property from the resource
   * bundle.
   *
   * @return The value of the "jwt.secretKey" property, or null if not found.
   */
  public static String getJwtSecretKey() {
    return BUNDLE.getString("jwt.secretKey");
  }
}
