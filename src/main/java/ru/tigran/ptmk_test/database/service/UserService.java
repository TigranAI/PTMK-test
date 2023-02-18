package ru.tigran.ptmk_test.database.service;

import org.springframework.stereotype.Service;
import ru.tigran.ptmk_test.database.entity.User;
import ru.tigran.ptmk_test.database.repository.UserRepo;
import ru.tigran.ptmk_test.enums.Sex;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public User createUser(String username, LocalDate birthday, Sex sex) {
        User user = new User();
        user.setUsername(username);
        user.setBirthday(birthday);
        user.setSex(sex == Sex.Male);
        return repo.saveAndFlush(user);
    }

    public List<User> findAll() {
        return repo.findAll();
    }
}
