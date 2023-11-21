package com.example.OnlineBookStore.Dto;

import java.util.List;

public class UserDTO {
    private int Userid;
    private String Username;
    private String email;
    private String password;
    private List<OrderDTO> orders;
    public UserDTO() {
    }
    public UserDTO(int Userid, String Username, String email, String password) {
        this.Userid = Userid;
        this.Username = Username;
        this.email = email;
        this.password = password;
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int Userid) {
        this.Userid = Userid;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}