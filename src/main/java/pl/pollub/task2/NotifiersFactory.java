package pl.pollub.task2;

/**
 * Fabryka
 */
public final class NotifiersFactory {

    public static Notifier getNotifierByType(NotifyType type){
        switch (type){
            case EMAIL: return new EmailNotifier();
            case SMS: return new SmsNotifier();
            case PUSH: return new PushNotifier();
            default: return null;
        }
    }
}
