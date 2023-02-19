package ru.tigran.ptmk_test.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TaskFiveTests {

    @Autowired private TaskFive task;

    @Test
    public void testTask() {
        String[] args = new String[] {"5"};
        int returnCode = task.doTask(args);
        assertThat(returnCode).isEqualTo(0);
    }
}
