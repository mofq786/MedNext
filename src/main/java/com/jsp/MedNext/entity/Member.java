package com.jsp.MedNext.entity;

import java.time.LocalDate;

import org.hibernate.annotations.Collate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name, gender,password;
	private LocalDate dob;
	private String email;
	private long mobile;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@Lob
	@Column(columnDefinition = "LONGBLOB", length = Integer.MAX_VALUE)
	private byte[] image;
	private boolean enabled;
	
}
