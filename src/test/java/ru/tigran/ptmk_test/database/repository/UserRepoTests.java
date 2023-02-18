package ru.tigran.ptmk_test.database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import ru.tigran.ptmk_test.database.entity.User;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoTests {
    @Autowired private UserRepo userRepo;

    @BeforeEach
    void writeTestValues() {
        Assert.notNull(userRepo, "User Repository is null!");

        userRepo.saveAllAndFlush(List.of(
                new User("Test One Person", LocalDate.of(2023, 2, 17), false),
                new User("Test Two Person", LocalDate.of(1999, 11, 26), true),
                new User("Test Three Person", LocalDate.of(2001, 1, 1), false)
        ));
    }

    @Test
    void checkTableIsNotEmpty() {
        List<User> users = userRepo.findAll();
        assertThat(users).isNotEmpty();
    }
}
