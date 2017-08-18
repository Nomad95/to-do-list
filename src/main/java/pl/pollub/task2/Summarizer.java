package pl.pollub.task2;


import java.sql.Connection;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton
 * ZALOZENIA:
 * globalna
 * tylko jedna (kontrolowane)
 * rozszezalna
 *
 * sychronized albo od razu new -> thread safe
 *
 * //powiadamiac uzytkownika tak jak sb chce (mailowe, sms, push)
 */
public class Summarizer {
    private static  Summarizer ourInstance = new Summarizer();

    private final Connection databaseConnection = null;

    private Map<Task,Date> completedTasks = new HashMap<>();

    public Summarizer() {
    }

    public static Summarizer getInstance() {
        return ourInstance;
    }

    public void noteCompletion(Task task, NotifyType type,Collection<String> emails){
        completedTasks.put(task,new Date());
        NotifiersFactory.getNotifierByType(type).notify(task.getId(),emails);
    }

    public Map<Task,Date> getCompletedTasks() {
        return completedTasks;
    }

    //albo lazy inner class
    /*private static class LazySingletonHolder {
        public static Summarizer getInstance(){
            return Singleton.LazySingletonHolder.INSTANCE;
        }
    }*/
}
