package com.korolar.itennis.dto.subpackage;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.korolar.itennis.dto.user.UserDto;

import lombok.Data;

@Data
public class SubPackageDto {

	private Long id;
	private BigDecimal amount;
	private LocalDateTime purchaseDate;
	private LocalDateTime validUntil;
	private UserDto players;

}
