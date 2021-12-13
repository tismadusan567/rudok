package model;

public class NotificationEvent {
    private final Object message;
    private final NotificationTypes type;
    public NotificationEvent(NotificationTypes type, Object message) {
        this.type = type;
        this.message = message;
    }

    public Object getMessage() {
        return message;
    }

    public NotificationTypes getType() {
        return type;
    }
}
