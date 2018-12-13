package com.tsiukaris.springtask.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsiukaris.springtask.Utils.PBKDF2Hasher;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    private static final PBKDF2Hasher hasher = new PBKDF2Hasher();

    @Id
    @GeneratedValue
    private int id;
    private String lastName;
    private String firstName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthday;

    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnore
    private String password;

    public User(String lastName, String firstName, Date birthday, String email, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.email = email;
        this.password = hasher.hash(password.toCharArray());
    }

    //if it was needed

    public boolean checkPassword(String password){
        return hasher.checkPassword(password.toCharArray(), this.password);
    }

    @JsonProperty("password")
    public void setPassword(String password){
        this.password = hasher.hash(password.toCharArray());
    }

    @JsonProperty("birthday")
    public void setBirthday(String birthday){
        this.birthday = Date.valueOf(birthday);
    }

    @JsonIgnore
    public String getPassword(){
        return this.password;
    }
}
