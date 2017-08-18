package pl.pollub.task2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    UserService userService;

    @Mock
    EmailNotifier emailNotifier;

    @InjectMocks
    TaskService taskService;

    @Captor
    private ArgumentCaptor<List<String>> emailsCaptor;

    @Test
    public void sendEmailToOwnerAndContributors() {
        Task task = taskService.createTaskForUser(1, 2, 3);

        Mockito.when(userService.getUserById(Mockito.anyInt())).then(invocationOnMock -> {
            Integer id = invocationOnMock.getArgumentAt(0, int.class);
            return new User(id, "user" + id + "@wp.pl");
        });

        taskService.completeTask(task.getId());
        //check if email sent to users: [1,2,3]

        Mockito.verify(emailNotifier).notify(Mockito.eq(task.getId()), emailsCaptor.capture());

        HashSet<String> notified = new HashSet<>(emailsCaptor.getValue());
        HashSet<String> expected = new HashSet<>(Arrays.asList("user1@wp.pl", "user2@wp.pl", "user3@wp.pl"));

        Assert.assertEquals(expected, notified);
    }

}