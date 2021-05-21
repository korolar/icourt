package com.korolar.itennis.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class SubscriptionPackage {

	@Id
	@Column(name = "package_id")
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	private BigDecimal amount;

	@NotNull
	private LocalDateTime purchaseDate;

	@NotNull
	private LocalDateTime validUntil;

	public Long getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public LocalDateTime getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(LocalDateTime validUntil) {
		this.validUntil = validUntil;
	}
}
