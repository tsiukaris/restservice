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
import java.util.Objects;

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

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return true;
        User user = (User) o;
        return email.equals(user.email) &&
                lastName.equals(user.lastName) &&
                firstName.equals(user.firstName) &&
                password.equals(user.password) &&
                birthday.equals(user.birthday);
    }

    @Override
    public int hashCode(){
        return Objects.hash(lastName, firstName, email, password, birthday);
    }

    @Override
    public String toString(){
        return "User: id = " + id +
                " Last Name = " + lastName +
                " First Name = " + firstName +
                " Email = " + email +
                " Birthday = " + birthday;
    }
}
