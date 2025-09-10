package com.diesgut.mongodb.features.student;

import com.diesgut.mongodb.features.student.entities.StudentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<StudentDocument, String> {
    List<StudentDocument> findByName(String name);
    StudentDocument findByEmailAndName (String email, String name);
    StudentDocument findByNameOrEmail (String name, String email);
    List<StudentDocument> findByDepartmentDepartmentName(String deptname);
    List<StudentDocument> findBySubjectsSubjectName (String subName);
    List<StudentDocument> findByEmailIsLike (String email);
    List<StudentDocument> findByNameStartsWith (String name);
}
