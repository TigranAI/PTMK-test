package ru.tigran.ptmk_test.database.service;

import org.springframework.stereotype.Service;
import ru.tigran.ptmk_test.database.entity.User;
import ru.tigran.ptmk_test.database.repository.UserRepo;
import ru.tigran.ptmk_test.enums.Sex;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    public List<User> findAllDistinctByUsernameAndBirthdayOrderedByUsername() {
        List<UUID> ids = repo.findAllIdDistinctByUsernameAndBirthday();
        return repo.findByIdInOrderByUsername(ids);
    }

    public void saveAll(Iterable<User> buffer) {
        repo.saveAll(buffer);
    }

    public List<User> findAllMaleWithUsernameStartsF() {
        return repo.findAllMaleWithUsernameStarts();
    }
}
