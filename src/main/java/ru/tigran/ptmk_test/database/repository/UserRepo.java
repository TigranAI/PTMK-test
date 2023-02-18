package ru.tigran.ptmk_test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tigran.ptmk_test.database.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    @Query(value =
            "select distinct (" +
                "select id " +
                "from users u2 " +
                "where u2.username = u1.username and u2.birthday = u1.birthday " +
                "limit 1" +
            ") as id " +
            "from users u1", nativeQuery = true)
    List<UUID> findAllIdDistinctByUsernameAndBirthday();

    List<User> findByIdInOrderByUsername(Collection<UUID> id);
}
