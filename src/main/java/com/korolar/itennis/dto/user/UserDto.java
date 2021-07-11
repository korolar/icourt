package com.korolar.itennis.dto.user;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDto implements Serializable {

	private Long id;
	private String firstName;
	private String lastName;

}
