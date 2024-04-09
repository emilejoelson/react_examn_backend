package com.example.gestion_livres.services.implementation;

import com.example.gestion_livres.Exception.AppException;
import com.example.gestion_livres.entities.Book;
import com.example.gestion_livres.repository.UserRepository;
import com.example.gestion_livres.repository.BookRepository;
import com.example.gestion_livres.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private  final UserRepository userRepository;
    private  final BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()->new AppException(HttpStatus.NOT_FOUND,"Book Not Found"));
    }



}
