package com.example.gestion_livres.services.implementation;

import com.example.gestion_livres.entities.Borrow;
import com.example.gestion_livres.repository.BorrowRepository;
import com.example.gestion_livres.services.interfaces.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    private  final BorrowRepository borrowRepository;
    @Override
    public Borrow borrowBook(Borrow borrow) {
        borrow.setBorrowingDate(LocalDateTime.now());
        return borrowRepository.save(borrow);
    }

    @Override
    public List<Borrow> getAllBorrow() {
        return borrowRepository.findAll();
    }

    @Override
    public List<Borrow> getBorrowsByBookId(Long bookId) {
        return borrowRepository.findByBookId(bookId);
    }
}
