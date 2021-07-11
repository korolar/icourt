package com.korolar.itennis.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class SubscriptionPackage {

	@Id
	@Column(name = "package_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	private BigDecimal amount;

	@NotNull
	private LocalDateTime purchaseDate;

	@NotNull
	private LocalDateTime validUntil;
}
