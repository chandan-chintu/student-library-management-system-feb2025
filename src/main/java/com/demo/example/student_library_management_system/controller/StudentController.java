package com.demo.example.student_library_management_system.controller;

import com.demo.example.student_library_management_system.model.Student;
import com.demo.example.student_library_management_system.requestdto.StudentRequestDto;
import com.demo.example.student_library_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/student/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public String saveStudent(@RequestBody StudentRequestDto studentRequestDto){
        String response = studentService.saveStudent(studentRequestDto);
        return response;
    }

    @GetMapping("/find/{id}")
    public Student findStudentById(@PathVariable int id){
        Student student = studentService.findStudentById(id);
        return student;
    }

    @GetMapping("/findAll")
    public List<Student> findAllStudents(){
        List<Student> studentList = studentService.findAllStudents();
        return studentList;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable int id){
        String response = studentService.deleteStudentById(id);
        return response;
    }

    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody StudentRequestDto studentRequestDto){
        String response = studentService.updateStudent(id,studentRequestDto);
        return response;
    }

    @GetMapping("/count")
    public String countStudents(){
        String response = studentService.countStudent();
        return response;
    }

    @GetMapping("/findAllByPage")
    public List<Student> findStudentByPage(@RequestParam int pageNo, @RequestParam int pageSize){
        List<Student> studentList = studentService.findStudentByPageAndSort(pageNo,pageSize);
        return studentList;
    }

}
