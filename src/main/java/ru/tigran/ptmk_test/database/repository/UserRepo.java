package ru.tigran.ptmk_test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tigran.ptmk_test.database.entity.User;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
}
