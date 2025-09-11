package com.diesgut.mongodb.features.student.entities;

import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor // Opcional, pero útil para frameworks como Jackson
@AllArgsConstructor // Lombok crea el constructor con todos los argumentos
@Document(collection = "students")
public class StudentDocument {
	@Id
	private String id;
	private String name;
	@Field(name = "mail")
	private String email;
	private DepartmentSubDocument department;
	private List<SubjectSubDocument> subjects;

	@Transient
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private double percentage;
/*
	public Student(String id, Department department) {
		this.id = id;
		this.department = department;
	}

	//@PersistenceConstructor it was used for indicate to Spring Data wich constructor to use for reading data
	public Student(String id, String name, String email, Department department, List<Subject> subjects) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.department = department;
		this.subjects = subjects;
	}
*/
	public double getPercentage() {
		if (subjects != null && subjects.size() > 0) {
			int total = 0;
			for (SubjectSubDocument subject : subjects) {
				total += subject.getMarksObtained();
			}
			return total/subjects.size();
		}
		return 0.00;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

}
