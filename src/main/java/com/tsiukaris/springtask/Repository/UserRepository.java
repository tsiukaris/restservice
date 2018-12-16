package com.tsiukaris.springtask.Repository;

import com.tsiukaris.springtask.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//I know how to use JPA default implementation or Hibernate to work with DB, Spring Data is just faster for me
//You can check it in my website project on github

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
