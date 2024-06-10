package com.snipe.myTuitionCenter.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tutor_institution")
public class Institution {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="institution_id")
	long institutionId;
	
	@Column(name="institution_name")
	private String name;

}
