package com.diesgut.mongodb.features.student.dto;

import com.diesgut.mongodb.features.student.entities.DepartmentSubDocument;
import com.diesgut.mongodb.features.student.entities.SubjectSubDocument;

import java.util.List;

public class CreateStudentDto {
    private String id;
    private String name;
    private String email;
    private DepartmentSubDocument department;
    private List<SubjectSubDocument> subjects;
}
