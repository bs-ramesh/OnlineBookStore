package com.example.OnlineBookStore.Service.impl;


import com.example.OnlineBookStore.Dto.BookDTO;
import com.example.OnlineBookStore.Dto.UserDTO;
import com.example.OnlineBookStore.Dto.LoginDTO;
import com.example.OnlineBookStore.Entity.Book;
import com.example.OnlineBookStore.Entity.Order;
import com.example.OnlineBookStore.Entity.User;
import com.example.OnlineBookStore.Repo.BookRepository;
import com.example.OnlineBookStore.Repo.OrderRepository;
import com.example.OnlineBookStore.Repo.UserRepository;
import com.example.OnlineBookStore.Service.UserService;
import com.example.OnlineBookStore.payload.response.LoginMesage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getUserid(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        userRepository.save(user);
        return user.getUsername();
    }
    UserDTO userDTO;
    @Override
    public LoginMesage loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepository.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> User = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (User.isPresent()) {
                    return new LoginMesage("Login Success", true);
                } else {
                    return new LoginMesage("Login Failed", false);
                }
            } else {
                return new LoginMesage("password Not Match", false);
            }
        }else {
            return new LoginMesage("Email not exits", false);
        }
    }
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<BookDTO> getAvailableBooks() {
        List<Book> availableBooks = bookRepository.findAll();
        return availableBooks.stream()
                .map(this::convertBookToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void placeOrder(int userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        Order order = new Order();
        order.setUser(user);
        order.setBook(book);
        orderRepository.save(order);
    }

    @Override
    public List<BookDTO> getOrderedBooks(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Order> orders = user.getOrders();
        return orders.stream()
                .map(order -> convertBookToDTO(order.getBook()))
                .collect(Collectors.toList());
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserid(user.getUserid());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword()); // Note: It's generally not recommended to expose passwords like this
        return userDTO;
    }
    private BookDTO convertBookToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPrice(book.getPrice());
        return bookDTO;
    }
}