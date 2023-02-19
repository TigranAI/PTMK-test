package ru.tigran.ptmk_test.tasks;

import org.springframework.stereotype.Component;
import ru.tigran.ptmk_test.database.entity.User;
import ru.tigran.ptmk_test.database.service.UserService;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Random;

@Component
public class TaskFour implements PtmkTask{
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private final String uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final LocalDate now;
    private final UserService service;

    public TaskFour(UserService service) {
        this.service = service;
        this.now = LocalDate.now();
    }

    @Override
    public int doTask(String[] args) {
        BufferedUserWriter writer = new BufferedUserWriter(1000, service);
        for (int i = 0; i < 1000000; ++i) {
            User user = generateUser();
            writer.write(user);
        }
        writer.saveAll();
        System.out.println("Successfully generated 1 million rows");
        return 0;
    }

    private User generateUser() {
        User user = new User();
        Random r = new Random(System.identityHashCode(user));
        user.setUsername(generateUsername(r));
        user.setBirthday(generateDate(r));
        user.setSex(r.nextBoolean());
        return user;
    }

    private String generateUsername(Random r) {
        StringBuilder sb = new StringBuilder();
        int strSize = r.nextInt(5,10);

        sb.append(uppercaseAlphabet.charAt(r.nextInt(26)));

        for (int i = 1; i < strSize; ++i)
            sb.append(alphabet.charAt(r.nextInt(26)));

        return sb.toString();
    }

    private LocalDate generateDate(Random r) {
        int days = r.nextInt(100) * 365;
        return now.minusDays(days);
    }
}

class BufferedUserWriter {
    private final int chunkSize;
    private final LinkedList<User> buffer;

    private final UserService service;

    public BufferedUserWriter(int chunkSize, UserService service) {
        this.chunkSize = chunkSize;
        this.buffer = new LinkedList<>();
        this.service = service;
    }

    public void write(User user) {
        buffer.addLast(user);
        if (buffer.size() >= chunkSize) saveAll();
    }

    public void saveAll() {
        service.saveAll(buffer);
        buffer.clear();
    }
}
