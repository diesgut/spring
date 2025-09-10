package com.diesgut.mongodb.features.student.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class DepartmentSubDocument {
	@Field(name = "department_name")
	private String departmentName;
	private String location;

}
