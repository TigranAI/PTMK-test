package ru.tigran.ptmk_test.tasks;

import org.springframework.stereotype.Component;
import ru.tigran.ptmk_test.database.entity.User;
import ru.tigran.ptmk_test.database.service.UserService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Component
public class TaskFive implements PtmkTask {

    private final UserService service;

    public TaskFive(UserService service) {
        this.service = service;
    }

    @Override
    public int doTask(String[] args) {
        try {
            long startTime = System.nanoTime();
            List<User> users = service.findAllMaleWithUsernameStartsF();
            long stopTime = System.nanoTime();
            double requiredTime = (double) (stopTime - startTime) / 1000000;

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            for (User u : users) {
                writer.write(u.toString());
                writer.newLine();
            }
            writer.write("\nFound " + users.size() + " rows, required: " + requiredTime + "ms\n");
            writer.close();

            return 0;
        } catch (IOException e) {
            System.out.println("Error:");
            e.printStackTrace();
            return 1;
        }
    }
}
