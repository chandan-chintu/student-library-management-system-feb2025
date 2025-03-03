package com.demo.example.student_library_management_system.repository;

import com.demo.example.student_library_management_system.model.Author;
import com.demo.example.student_library_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // writing customized queries - writing our own user defined queries

    //1. with JPA support - using attributes or fields
    public Student findByEmail(String inputEmail);

    public List<Student> findByDept(String inDept);

    public List<Student> findBySemAndDept(String inSem, String inDept);

    public List<Student> findBySemOrDeptOrEmail(String inSem, String inDept, String inEmail);

    //2. with own sql queries
    //select * from student_library_feb2025.student where dept="CS"

    @Query(nativeQuery = true, value = "select * from student where dept=:inDept")
    public List<Student> getStudentByDept(String inDept);

    @Query(nativeQuery = true, value = "select * from student where email=:inEmail")
    public List<Student> getStudentByEmail(String inEmail);



}
