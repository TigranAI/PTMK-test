package ru.tigran.ptmk_test.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TaskOneTests {

    @Autowired private TaskOne task;
    @Autowired private JdbcTemplate template;

    @BeforeEach
    public void dropTable() {
        assertThat(template).isNotNull();
        template.execute("DROP TABLE users");
    }

    @Test
    public void testTask() {
        String[] args = new String[] {"1"};
        int returnCode = task.doTask(args);
        assertThat(returnCode).isEqualTo(0);
    }
}
