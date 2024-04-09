package com.example.gestion_livres.services.interfaces;


import com.example.gestion_livres.entities.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    List<Book> getAllBook();
    Book getBookById(Long id);
}
