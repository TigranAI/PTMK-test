package ru.tigran.ptmk_test.tasks;

import org.springframework.stereotype.Component;
import ru.tigran.ptmk_test.database.entity.User;
import ru.tigran.ptmk_test.database.service.UserService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.List;

@Component
public class TaskThree implements PtmkTask{

    private final UserService service;

    public TaskThree(UserService service) {
        this.service = service;
    }

    @Override
    public int doTask(String[] args) {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        LocalDate date = LocalDate.now();
        List<User> users = service.findAllDistinctByUsernameAndBirthdayOrderedByUsername();
        try {
            for (User user : users) {
                writer.write(user.toStringWithAge(date));
                writer.newLine();
            }
            writer.close();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }
}
