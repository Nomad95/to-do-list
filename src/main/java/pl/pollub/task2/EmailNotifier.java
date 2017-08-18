package pl.pollub.task2;

import java.util.Collection;

public interface EmailNotifier {

    void notify(int taskId, Collection<String> emails);

}
