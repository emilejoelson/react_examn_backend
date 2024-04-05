package com.example.gestion_livres.services.implementation;

import com.example.gestion_livres.entities.Book;
import com.example.gestion_livres.entities.User;
import com.example.gestion_livres.repository.UserRepository;
import com.example.gestion_livres.repository.BookRepository;
import com.example.gestion_livres.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {
            bookRepository.deleteById(id);
    }

    @Override
    public Book addBookWithUserId(Book book, Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(()->new IllegalArgumentException("Room not found with id : "+ userId));
        book.setUser(user);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooksByUserId(Long userId) {
        return bookRepository.findByUserId(userId);
    }

    @Override
    public Book borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));

        if (book.getUser() != null) {
            throw new IllegalStateException("Book is already borrowed by another user");
        }

        book.setUser(user);
        book.setBorrowingDate(LocalDateTime.now());
        return bookRepository.save(book);
    }


}
