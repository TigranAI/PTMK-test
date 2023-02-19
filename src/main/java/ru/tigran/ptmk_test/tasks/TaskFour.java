package ru.tigran.ptmk_test.tasks;

import org.springframework.stereotype.Component;
import ru.tigran.ptmk_test.database.entity.User;
import ru.tigran.ptmk_test.database.service.UserService;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TaskFour implements PtmkTask{
    private final UserService service;

    public TaskFour(UserService service) {
        this.service = service;
    }

    @Override
    public int doTask(String[] args) {
        int requiredRows = 1000000;
        int chunkSize = 100000;
        int threadGoal = 10000;
        int threadPoolSize = 10;
        int threadsCount = requiredRows / threadGoal;

        BufferedAtomicUserWriter writer = new BufferedAtomicUserWriter(chunkSize, service);
        LocalDate now = LocalDate.now();

        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        System.out.println("Please wait ... \n");

        for (int i = 0; i < threadsCount; ++i) {
            Runnable task = new GenerateUsersRunnable(threadGoal, writer, now);
            executor.execute(task);
        }
        executor.shutdown();

        while (!executor.isTerminated()) {}

        writer.saveAll();
        System.out.println("\nSuccessfully generated 1 million rows");
        return 0;
    }
}

class BufferedAtomicUserWriter {
    private final int chunkSize;
    private final LinkedList<User> buffer;

    private final UserService service;

    public BufferedAtomicUserWriter(int chunkSize, UserService service) {
        this.chunkSize = chunkSize;
        this.buffer = new LinkedList<>();
        this.service = service;
    }

    public final synchronized void write(User user) {
        buffer.addLast(user);
        if (buffer.size() >= chunkSize) saveAll();
    }

    public final synchronized void saveAll() {
        service.saveAll(buffer);
        buffer.clear();
    }
}

class GenerateUsersRunnable implements Runnable {
    private final LocalDate now;
    private final int count;
    private final BufferedAtomicUserWriter writer;

    public GenerateUsersRunnable(int count, BufferedAtomicUserWriter writer, LocalDate now) {
        this.count = count;
        this.writer = writer;
        this.now = now;
    }

    @Override
    public void run() {
        System.out.println("\tNew task at thread id: " + Thread.currentThread().getId() + " is started");
        for (int i = 0; i < count; ++i) {
            User user = generateUser();
            writer.write(user);
        }
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

        String uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        sb.append(uppercaseAlphabet.charAt(r.nextInt(26)));

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 1; i < strSize; ++i)
            sb.append(alphabet.charAt(r.nextInt(26)));

        return sb.toString();
    }

    private LocalDate generateDate(Random r) {
        int days = r.nextInt(100) * 365;
        return now.minusDays(days);
    }
}
