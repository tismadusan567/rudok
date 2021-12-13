package observer;

import model.NotificationEvent;

public interface ISubscriber {
    void update(NotificationEvent notification);
}
