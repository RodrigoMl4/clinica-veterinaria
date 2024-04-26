package com.clinica.clinicaveterinaria.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "services")
public class Service {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "serviceID")
  private Integer serviceId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pet_id", nullable = false)
  private Pet pet;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "serviceProvidedID", nullable = false)
  private ServiceProvided serviceProvided;

  @Column(length = 255)
  private String behavior;

  @Column(name = "description_case", length = 255)
  private String descriptionCase;

  @Column(name = "date_service")
  private Date dateService;

  @Column(precision = 5, scale = 2)
  private BigDecimal temperature;

  @Column(name = "heartRate", precision = 5, scale = 2)
  private BigDecimal heartRate;

  @Column(name = "respiratoryRate", precision = 5, scale = 2)
  private BigDecimal respiratoryRate;

  @Column(name = "systolicBloodPressure", precision = 5, scale = 2)
  private BigDecimal systolicBloodPressure;

  @Column(name = "diastolicBloodPressure", precision = 5, scale = 2)
  private BigDecimal diastolicBloodPressure;

  @Column(name = "meanBloodPressure", precision = 5, scale = 2)
  private BigDecimal meanBloodPressure;

  @Column(length = 255)
  private String mucousMembrane;

  @Column(precision = 5, scale = 2)
  private BigDecimal pulse;
}
