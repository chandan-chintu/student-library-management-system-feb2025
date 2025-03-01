package com.demo.example.student_library_management_system.service;

import com.demo.example.student_library_management_system.converters.StudentConverter;
import com.demo.example.student_library_management_system.enums.CardStatus;
import com.demo.example.student_library_management_system.model.Card;
import com.demo.example.student_library_management_system.model.Student;
import com.demo.example.student_library_management_system.repository.StudentRepository;
import com.demo.example.student_library_management_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String saveStudent(StudentRequestDto studentRequestDto){
        // first convert the studentrequestdto into student object
        Student student =StudentConverter.convertStudentRequestDtoIntoStudent(studentRequestDto);

        // whenever student is saved card also saved/created at same time
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVE);
        card.setStudent(student);

        student.setCard(card);

        studentRepository.save(student);
        return "Student and card saved successfully";
    }

    public Student findStudentById(int id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        }
        return null;
    }

    public List<Student> findAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    /*
    Pagination - fetching or getting the records or data in the form of pages
    pagenumber - the number of page we want see(page number starts 0,1,2,3....)
    pagesize - total number of records in each page

    total number of records in database-28, page size-5
    0th page -1-5
    1st page -6-10
    2nd page -11-15
    3rd page -16-20
    4th page -21-25
    5th page -26-28
    6th page -0

    total number of records in database-11, page size-2
    0th page -1-2
    1st page -3-4
    2nd page -5-6
    3rd page -7-8
    4th page -9-10
    5th page -11

    sorting - arranging the records in the ascending or descending order
    only pagaination -
    public List<Student> findStudentByPage(int pageNo, int pageSize){
        List<Student> studentList = studentRepository.findAll(PageRequest.of(pageNo,pageSize)).getContent();
        return studentList;
    }
     */

    public List<Student> findStudentByPageAndSort(int pageNo, int pageSize){
        List<Student> studentList = studentRepository.findAll(PageRequest.of(pageNo,pageSize, Sort.by("name").ascending())).getContent();
        return studentList;
    }


    public String deleteStudentById(int id){
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }

    public String updateStudent(int id, StudentRequestDto studentRequestDto){
        Student student = findStudentById(id);
        if(student!=null){
            student.setName(studentRequestDto.getName());
            student.setSem(studentRequestDto.getSem());
            student.setDept(studentRequestDto.getDept());
            student.setDob(studentRequestDto.getDob());
            student.setEmail(studentRequestDto.getEmail());
            student.setMobile(studentRequestDto.getMobile());
            student.setGender(studentRequestDto.getGender());
            student.setAddress(studentRequestDto.getAddress());

            studentRepository.save(student);
            return "Student updated successfully";
        }else{
            return "Student cannot be updated";
        }
    }

    public String countStudent(){
        long totalCount  = studentRepository.count();
        return "Total students present are : "+totalCount;
    }
}
