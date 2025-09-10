package com.diesgut.mongodb.features.student;

import com.diesgut.mongodb.features.student.entities.StudentDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public StudentDocument createStudent(@RequestBody StudentDocument student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/getById/{id}")
    public StudentDocument getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/all")
    public List<StudentDocument> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/update/{id}")
    public StudentDocument updateStudent(@PathVariable String id, @RequestBody StudentDocument student) {
        return studentService.updateStudent(id, student);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/allWithPagination")
    public List<StudentDocument> getAllWithPagination(@RequestParam int pageNro,
                                              @RequestParam int pageSize) {
        return studentService.getAllWithPagination(pageNro, pageSize);
    }

    @GetMapping("/allWithSorting")
    public List<StudentDocument> allWithSorting() {
        return studentService.allWithSorting();
    }
}
