package com.example.gestion_livres.services.implementation;

import com.example.gestion_livres.entities.Book;
import com.example.gestion_livres.entities.User;
import com.example.gestion_livres.repository.UserRepository;
import com.example.gestion_livres.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByID(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(Long id, User user) {

    }

    @Override
    public User addBookToUser(Long userId, Book book) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
