package ru.tigran.ptmk_test.tasks;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaskOne implements PtmkTask{

    private final JdbcTemplate template;

    @Value(value = "classpath:create_table.sql")
    private Resource createTableResource;

    public TaskOne(JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public int doTask(String[] args) {
        try {
            String query = new String(createTableResource.getInputStream().readAllBytes());
            template.execute(query);
            System.out.println("Create table success");
            return 0;
        } catch (Exception e) {
            System.out.println("Error:");
            e.printStackTrace();
            return 1;
        }
    }
}
