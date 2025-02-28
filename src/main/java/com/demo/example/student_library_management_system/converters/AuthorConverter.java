package com.demo.example.student_library_management_system.converters;


import com.demo.example.student_library_management_system.model.Author;
import com.demo.example.student_library_management_system.model.Book;
import com.demo.example.student_library_management_system.model.Student;
import com.demo.example.student_library_management_system.requestdto.AuthorRequestDto;
import com.demo.example.student_library_management_system.requestdto.BookRequestDto;
import com.demo.example.student_library_management_system.requestdto.StudentRequestDto;

public class AuthorConverter {

    public static Author convertAuthorRequestDtoIntoAuthor(AuthorRequestDto authorRequestDto){
       Author author = new Author();

       author.setName(authorRequestDto.getName());
       author.setEmail(authorRequestDto.getEmail());
       author.setRating(authorRequestDto.getRating());
       author.setCountry(authorRequestDto.getCountry());
       author.setGender(authorRequestDto.getGender());
        return  author;
    }


}
