package ru.tigran.ptmk_test.database.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "users", indexes = @Index(columnList = "username"))
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private Boolean sex;

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "User(" + username + ", " +
                formatter.format(birthday) + ", " +
                (sex ? "male" : "female") + ")";
    }

    public String toStringWithAge(LocalDate dateNow) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Period period = Period.between(birthday, dateNow);
        return String.format("User: %s %s %s %d", username, formatter.format(birthday), (sex ? "male" : "female"), period.getYears());
    }
}
