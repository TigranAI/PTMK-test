package ru.tigran.ptmk_test.database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tigran.ptmk_test.database.entity.User;
import ru.tigran.ptmk_test.enums.Sex;
import ru.tigran.ptmk_test.database.service.UserService;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoTests {
    @Autowired private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    void writeTestValues() {
        assertThat(userRepo).isNotNull();

        userService.createUser("Test One Person", LocalDate.of(2023, 2, 17), Sex.Male);
        userService.createUser("Test Two Person", LocalDate.of(1999, 11, 26), Sex.Female);
        userService.createUser("Test Three Person", LocalDate.of(2001, 1, 1), Sex.Male);
    }

    @Test
    void checkTableIsNotEmpty() {
        List<User> users = userService.findAll();
        assertThat(users).isNotEmpty();
    }
}
