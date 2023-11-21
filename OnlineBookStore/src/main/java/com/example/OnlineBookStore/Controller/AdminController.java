package com.example.OnlineBookStore.Controller;

import com.example.OnlineBookStore.Dto.BookDTO;
import com.example.OnlineBookStore.Dto.UserDTO;
import com.example.OnlineBookStore.Service.BookService;
import com.example.OnlineBookStore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping(path = "/addBook")
    public void addBook(@RequestBody BookDTO bookDTO) {
        bookService.addBook(bookDTO);
    }

    @GetMapping(path = "/getAllUsers")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping(path = "/deleteUser/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
    @GetMapping(path = "/getAllBooks")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @DeleteMapping(path = "/deleteBook/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }

}