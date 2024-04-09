package com.example.gestion_livres.services.interfaces;

import com.example.gestion_livres.entities.Borrow;

import java.time.LocalDateTime;
import java.util.List;

public interface BorrowService {
    Borrow borrowBook(Borrow borrow);
    List<Borrow> getAllBorrow();
    List<Borrow> getBorrowsByBookId(Long bookId);
}
