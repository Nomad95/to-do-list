package pl.pollub.task2;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TaskService {

    public TaskService(UserService userService, EmailNotifier emailNotifier, Summarizer summarizer) {
        this.userService = userService;
        this.emailNotifier = emailNotifier;
        this.summarizer = summarizer;
    }

    private final UserService userService;

    private final EmailNotifier emailNotifier;

    private final Map<Integer, Task> tasks = new HashMap<>();

    private final AtomicInteger counter = new AtomicInteger();

    private final Summarizer summarizer;

    public Task createTaskForUser(int userId, Integer... contributors){
        Task task = new Task(counter.incrementAndGet(), userId,
                             contributors != null ? Arrays.asList(contributors) : Collections.emptyList());
        tasks.put(task.getId(), task);
        return task;
    }

    public void completeTask(int taskId){
        Task task = tasks.get(taskId);
        List<Integer> userIds = new ArrayList(task.getContributors());
        userIds.add(task.getUserId());

        Set<String> emails = userIds.stream()
                                    .map(userService::getUserById)
                                    .map(User::getEmail)
                                    .collect(Collectors.toSet());

        summarizer.noteCompletion(task,NotifyType.EMAIL,emails);
    }

}
