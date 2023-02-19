package ru.tigran.ptmk_test.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tigran.ptmk_test.database.repository.UserRepo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TaskFourTests {

    @Autowired
    private TaskFour task;
    @Autowired
    private JdbcTemplate template;
    @Autowired
    private UserRepo repo;

    @BeforeEach
    public void clearTable() {
        assertThat(template).isNotNull();
        template.execute("DELETE FROM users WHERE TRUE");
    }

    @Test
    public void testTask() {
        String[] args = new String[]{"4"};
        int returnCode = task.doTask(args);
        assertThat(returnCode).isEqualTo(0);
        assertThat(repo.count()).isEqualTo(1000000);
    }
}
