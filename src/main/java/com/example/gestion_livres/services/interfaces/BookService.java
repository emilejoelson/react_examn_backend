package com.example.gestion_livres.services.interfaces;


import com.example.gestion_livres.entities.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    List<Book> getAllBook();
    Book getBookById(Long id);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    Book addBookWithUserId(Book book,Long userId);
    List<Book> getAllBooksByUserId(Long userId);
    Book borrowBook(Long userId, Long bookId);
}
