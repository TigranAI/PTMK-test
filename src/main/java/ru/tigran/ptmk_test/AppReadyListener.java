package ru.tigran.ptmk_test;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.tigran.ptmk_test.tasks.*;

@Component
public class AppReadyListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String[] args = event.getArgs();
        if (args.length == 0) System.out.println("Please specify action:\n" +
                "1 - create users table\n" +
                "2 <\"Full name\"> <dd.MM.yyyy> <male|female> - create user\n" +
                "3 - show distinct username+date rows\n" +
                "4 - auto fill 1M rows\n" +
                "5 - show male users with username starts 'F' and lead time\n" +
                "6 - apply database optimization and do task #5");
        else try {
            run(Integer.parseInt(args[0]), args, event.getApplicationContext());
        } catch (NumberFormatException e) {
            System.out.println("First arg is not integer");
        }
    }

    private void run(int taskId, String[] args, ApplicationContext context) {
        switch (taskId) {
            case 1:
                context.getBean(TaskOne.class).doTask(args);
                break;
            case 2:
                context.getBean(TaskTwo.class).doTask(args);
                break;
            case 3:
                context.getBean(TaskThree.class).doTask(args);
                break;
            case 4:
                context.getBean(TaskFour.class).doTask(args);
                break;
            case 5:
                break;
            case 6:
                break;
            default: System.out.println("Unknown command");
        }
    }
}
