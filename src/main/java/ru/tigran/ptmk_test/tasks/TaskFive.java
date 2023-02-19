package ru.tigran.ptmk_test.tasks;

import org.springframework.stereotype.Component;
import ru.tigran.ptmk_test.database.entity.User;
import ru.tigran.ptmk_test.database.service.UserService;

import java.util.List;

@Component
public class TaskFive implements PtmkTask{

    private final UserService service;

    public TaskFive(UserService service) {
        this.service = service;
    }

    @Override
    public int doTask(String[] args) {
        long startTime = System.nanoTime();
        List<User> users = service.findAllMaleWithUsernameStarts("F");
        long stopTime = System.nanoTime();
        double requiredTime = (double)(stopTime - startTime) / 1000000;
        System.out.println("Found " + users.size() + " rows, required: " + requiredTime + "ms");
        return 0;
    }
}
