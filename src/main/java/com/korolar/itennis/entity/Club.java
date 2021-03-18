package com.korolar.itennis.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity public class Club {

	@Id @Column(name = "club_id") @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

	@NotNull private String name;
}
