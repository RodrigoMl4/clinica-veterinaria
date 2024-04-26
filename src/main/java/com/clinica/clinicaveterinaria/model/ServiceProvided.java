package com.clinica.clinicaveterinaria.model;

import java.math.BigDecimal;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "service_provided")
public class ServiceProvided {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "serviceProvidedID")
  private Integer serviceProvidedId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "typeID", nullable = false)
  private ServiceType serviceType;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column(length = 255)
  private String imageVideo;

  @Column(length = 255)
  private String reason;
}
