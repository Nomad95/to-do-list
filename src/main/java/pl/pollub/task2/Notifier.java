package pl.pollub.task2;

import java.util.Collection;

/**
 * Strategia
 */
public interface Notifier {

    void notify(int taskId, Collection<String> emails);
}
