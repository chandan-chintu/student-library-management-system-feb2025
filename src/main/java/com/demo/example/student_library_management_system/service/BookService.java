package com.demo.example.student_library_management_system.service;

import com.demo.example.student_library_management_system.converters.BookConverter;
import com.demo.example.student_library_management_system.model.Author;
import com.demo.example.student_library_management_system.model.Book;
import com.demo.example.student_library_management_system.model.Card;
import com.demo.example.student_library_management_system.repository.AuthorRepository;
import com.demo.example.student_library_management_system.repository.BookRepository;
import com.demo.example.student_library_management_system.repository.CardRepository;
import com.demo.example.student_library_management_system.requestdto.BookRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String saveBook(BookRequestDto bookRequestDto){

        Book book = BookConverter.convertBookRequestDtoIntoBook(bookRequestDto);

        // here we need to add authorid and cardid as well
        Card card = cardRepository.findById(bookRequestDto.getCardId()).get();
        book.setCard(card);

        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
        book.setAuthor(author);

        bookRepository.save(book);
        return "Book added successfully";
    }
}
