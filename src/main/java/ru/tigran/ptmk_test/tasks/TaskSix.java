package ru.tigran.ptmk_test.tasks;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tigran.ptmk_test.database.service.UserService;

@Component
public class TaskSix implements PtmkTask {
    private final JdbcTemplate template;
    private final TaskFive taskFive;
    @Value(value = "classpath:create_index.sql")
    private Resource createIndexResource;

    public TaskSix(JdbcTemplate template, TaskFive taskFive) {
        this.template = template;
        this.taskFive = taskFive;
    }


    @Override
    public int doTask(String[] args) {
        try {
            String query = new String(createIndexResource.getInputStream().readAllBytes());
            System.out.println("Please wait ...");
            try {
                template.execute(query);
                System.out.println("Create 'username' index success\n");
            } catch (Exception e) {
                System.out.println("Index already applied\n");
            }

            taskFive.doTask(args);

            return 0;
        } catch (Exception e) {
            System.out.println("Error:");
            e.printStackTrace();
            return 1;
        }
    }
}
