package com.diesgut.mongodb.features.student.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class SubjectSubDocument {
	@Field(name = "subject_name")
	private String subjectName;
	@Field(name = "marks_obtained")
	private int marksObtained;
}
