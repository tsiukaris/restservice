package com.tsiukaris.springtask.Repository;

import com.tsiukaris.springtask.Entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByEmail_thenReturnUser(){

        //given
        User olga = new User("Hopkee", "Olga",
                Date.valueOf("1993-11-10"),
                "olgahop@gmail.com",
                "olga19827364");

        entityManager.persist(olga);
        entityManager.flush();

        //when
        User found = userRepository.findByEmail(olga.getEmail()).get();

        //then
        assertThat(found.getLastName())
                .isEqualTo(olga.getLastName());
        assertThat(found.getFirstName())
                .isEqualTo(olga.getFirstName());
        assertThat(found.getBirthday())
                .isEqualTo(olga.getBirthday());
        assertThat(found.getEmail())
                .isEqualTo(olga.getEmail());
        assertThat(found.getPassword())
                .isEqualTo(olga.getPassword());
    }
}
