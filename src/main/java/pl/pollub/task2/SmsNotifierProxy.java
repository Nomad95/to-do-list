package pl.pollub.task2;

import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SmsNotifierProxy implements Notifier{
    private SmsNotifier smsNotifier;
    private UserService userService;

    public SmsNotifierProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void notify(Task task) {
        List<Integer> contributors = task.getContributors();
        Set<Integer> collect = contributors.stream().map(userService::getUserById)
                .filter(User::isHasPaidForSms)
                .map(User::getId)
                .collect(Collectors.toSet());
        //jak powiadomić poprzez smsNotifier jeśli przekazuje tak tylko taska?
    }
}
