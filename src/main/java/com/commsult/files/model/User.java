package com.commsult.files.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    @Column(name = "username", unique = true)
    private String username;
    @NonNull
    @Column(name = "password")
    private String password;
    @NonNull
    @Column(name = "email", unique = true)
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(){
    }

}
