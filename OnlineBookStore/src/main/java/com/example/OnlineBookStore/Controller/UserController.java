package com.example.OnlineBookStore.Controller;


import com.example.OnlineBookStore.Dto.BookDTO;
import com.example.OnlineBookStore.Dto.UserDTO;
import com.example.OnlineBookStore.Dto.LoginDTO;
import com.example.OnlineBookStore.Service.UserService;
import com.example.OnlineBookStore.payload.response.LoginMesage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(path = "/register")
    public String saveUser(@RequestBody UserDTO userDTO)
    {
        String id = userService.addUser(userDTO);
        return id;
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO)
    {
        LoginMesage loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
    @GetMapping(path = "/getAvailableBooks")
    public List<BookDTO> getAvailableBooks() {
        return userService.getAvailableBooks();
    }

    @PostMapping(path = "/placeOrder/{userId}/{bookId}")
    public void placeOrder(@PathVariable int userId, @PathVariable Long bookId) {
        userService.placeOrder(userId, bookId);
    }

    @GetMapping(path = "/getOrderedBooks/{userId}")
    public List<BookDTO> getOrderedBooks(@PathVariable int userId) {
        return userService.getOrderedBooks(userId);
    }
}
