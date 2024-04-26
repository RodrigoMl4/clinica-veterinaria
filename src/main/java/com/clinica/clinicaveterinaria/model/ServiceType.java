package com.clinica.clinicaveterinaria.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "service_type")
public class ServiceType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "typeID")
  private Integer typeId;

  @Column(name = "service_name", unique = true, nullable = false, length = 255)
  private String serviceName;
}
