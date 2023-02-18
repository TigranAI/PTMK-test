package ru.tigran.ptmk_test.database.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "users")
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
}
