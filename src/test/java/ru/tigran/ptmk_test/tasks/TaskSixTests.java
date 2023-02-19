package ru.tigran.ptmk_test.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TaskSixTests {

    @Autowired private TaskSix task;
    @Autowired private JdbcTemplate template;

    @BeforeEach
    public void dropIndex() {
        assertThat(template).isNotNull();
        try {
            template.execute("DROP INDEX username_sex_idx ON users");
        } catch (Exception e) {
            System.out.println("Index does not exists");
        }
    }

    @Test
    public void testTask() {
        String[] args = new String[] {"6"};
        int returnCode = task.doTask(args);
        assertThat(returnCode).isEqualTo(0);
    }
}
