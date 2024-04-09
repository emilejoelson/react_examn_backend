package com.example.gestion_livres.services.interfaces;


import com.example.gestion_livres.entities.Book;
import com.example.gestion_livres.entities.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User getUserByID(Long id);
    List<User> getAllUser();
}
