package com.example.gestion_livres.controller;

import com.example.gestion_livres.entities.Book;
import com.example.gestion_livres.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book response = bookService.addBook(book);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{bookId}/borrowedBy/{userId}")
    public ResponseEntity<Book> borrowBook(@PathVariable Long bookId, @PathVariable Long userId) {
        Book borrowedBook = bookService.borrowBook(userId, bookId);
        return new ResponseEntity<>(borrowedBook, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book response = bookService.getBookById(id);
        if(response != null){
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBook();
        return ResponseEntity.ok(books);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Book> addBookWithuserId(@RequestBody Book book, @PathVariable Long userId){
        Book addedBook = bookService.addBookWithUserId(book, userId);
        return ResponseEntity.ok(addedBook);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Book>> getAllBooksByuserId(@PathVariable Long userId){
        List<Book> books = bookService.getAllBooksByUserId(userId);
        return ResponseEntity.ok(books);
    }

}
