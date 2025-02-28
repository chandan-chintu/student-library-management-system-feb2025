package com.demo.example.student_library_management_system.service;

import com.demo.example.student_library_management_system.converters.StudentConverter;
import com.demo.example.student_library_management_system.enums.CardStatus;
import com.demo.example.student_library_management_system.model.Card;
import com.demo.example.student_library_management_system.model.Student;
import com.demo.example.student_library_management_system.repository.StudentRepository;
import com.demo.example.student_library_management_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
