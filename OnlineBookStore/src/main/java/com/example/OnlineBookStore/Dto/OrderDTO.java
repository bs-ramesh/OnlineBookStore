package com.example.OnlineBookStore.Dto;


public class OrderDTO {
    private Long id;
    private int userId;
    private Long bookId;

    public OrderDTO() {
    }

    public OrderDTO(Long id, int userId, Long bookId) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}