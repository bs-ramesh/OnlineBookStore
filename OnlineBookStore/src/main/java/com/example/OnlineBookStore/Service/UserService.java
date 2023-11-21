package com.example.OnlineBookStore.Service;

import com.example.OnlineBookStore.Dto.BookDTO;
import com.example.OnlineBookStore.Dto.UserDTO;
import com.example.OnlineBookStore.Dto.LoginDTO;
import com.example.OnlineBookStore.payload.response.LoginMesage;

import java.util.List;

public interface UserService {
    String addUser(UserDTO userDTO);
    LoginMesage loginUser(LoginDTO loginDTO);
    List<UserDTO> getAllUsers();
    void deleteUser(int userId);

    List<BookDTO> getAvailableBooks();
    void placeOrder(int userId, Long bookId);
    List<BookDTO> getOrderedBooks(int userId);
}