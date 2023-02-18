package ru.tigran.ptmk_test.tasks;

import org.springframework.stereotype.Component;
import ru.tigran.ptmk_test.database.entity.User;
import ru.tigran.ptmk_test.database.service.UserService;
import ru.tigran.ptmk_test.enums.Sex;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class TaskTwo implements PtmkTask{

    private final UserService service;

    public TaskTwo(UserService service) {
        this.service = service;
    }

    @Override
    public int doTask(String[] args) {
        if (args.length != 4) {
            System.out.println("Wrong args number");
            return 1;
        }
        else if (!args[3].equals("male") && !args[3].equals("female")) {
            System.out.println("Wrong sex identifier");
            return 1;
        }
        else {
            try {
                LocalDate date = LocalDate.parse(args[2], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                Sex sex = args[3].equals("male") ? Sex.Male : Sex.Female;
                User user = service.createUser(args[1], date, sex);
                System.out.println("Successfully created user: " + user);
                return 0;
            } catch (Error e) {
                System.out.println("Error:");
                e.printStackTrace();
                return 1;
            }
        }
    }
}
