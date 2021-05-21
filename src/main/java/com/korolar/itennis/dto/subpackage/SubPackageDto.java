package com.korolar.itennis.dto.subpackage;

import com.korolar.itennis.dto.user.UserDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SubPackageDto {

	private Long id;
	private BigDecimal amount;
	private LocalDateTime purchaseDate;
	private LocalDateTime validUntil;
	private UserDto players;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public UserDto getPlayers() {
		return players;
	}

	public void setPlayers(UserDto players) {
		this.players = players;
	}
}
