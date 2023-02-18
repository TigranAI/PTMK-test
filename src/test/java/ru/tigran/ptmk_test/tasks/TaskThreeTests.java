package ru.tigran.ptmk_test.tasks;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TaskThreeTests {

    @Autowired private TaskThree task;

    @Test
    @Order(1)
    public void testTask() {
        String[] args = new String[] {"3"};
        int returnCode = task.doTask(args);

        assertThat(returnCode).isEqualTo(0);
    }
}
