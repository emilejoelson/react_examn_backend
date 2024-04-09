package com.example.gestion_livres.controller;

import com.example.gestion_livres.entities.Borrow;
import com.example.gestion_livres.services.interfaces.BorrowService;
import com.example.gestion_livres.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/borrows")
@CrossOrigin("*")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @PostMapping
    public ResponseEntity<Borrow> borrowBook(
            @Valid @RequestBody Borrow borrow) {
        Borrow response = borrowService.borrowBook(borrow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Borrow>> getAllBorrow(){
        List<Borrow> allBorrows = borrowService.getAllBorrow();
        return new ResponseEntity<>(allBorrows,HttpStatus.OK);
    };
    @GetMapping("/byBookId/{bookId}")
    public ResponseEntity<List<Borrow>> getBorrowsByBookId(@PathVariable Long bookId) {
        List<Borrow> borrows = borrowService.getBorrowsByBookId(bookId);
        return new ResponseEntity<>(borrows, HttpStatus.OK);
    }
}
