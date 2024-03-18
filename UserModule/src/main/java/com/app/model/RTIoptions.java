package com.app.model;

import com.app.enums.Decision;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="rtioptions")
@AllArgsConstructor
@NoArgsConstructor
public class RTIoptions {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "question_id")
	private Question question;
	 
	private String optionString;
	private String description;
	private Long weightage;
	
	@Enumerated(EnumType.STRING)
	private Decision rational;
	
}
