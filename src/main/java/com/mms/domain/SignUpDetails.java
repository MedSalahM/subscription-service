package com.mms.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SignUpDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	
	private String email;

	private String dateRequest;
	
	private String activationLink;
	
	private LocalDateTime expriesAt;

}
