package com.diesgut.mongodb.features.student;

import com.diesgut.mongodb.features.student.entities.StudentDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentDocument> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentDocument createStudent(StudentDocument student) {
        return studentRepository.save(student);
    }

    public StudentDocument updateStudent(String id, StudentDocument student) {
        StudentDocument existingStudent = this.getStudentById(id);

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        //  existingStudent.setDepartment(student.getDepartment());
        // existingStudent.setSubjects(student.getSubjects());
        return studentRepository.save(existingStudent);
    }

    public StudentDocument getStudentById(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public List<StudentDocument> getAllWithPagination (int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return studentRepository.findAll(pageable).getContent();
    }

    public List<StudentDocument> allWithSorting () {
        Sort sort = Sort.by(Sort.Direction.ASC, "name", "email");
        return studentRepository.findAll(sort);
    }
}
