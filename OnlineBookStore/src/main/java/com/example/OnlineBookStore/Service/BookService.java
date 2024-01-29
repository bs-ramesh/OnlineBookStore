package com.example.OnlineBookStore.Service;

import com.example.OnlineBookStore.Dto.BookDTO;

import java.util.List;

public interface BookService {
    void addBook(BookDTO bookDTO);
    void deleteBook(Long bookId);
    List<BookDTO> getAllBooks();
    

}