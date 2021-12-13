package observer;

import model.NotificationEvent;

public interface IPublisher {
    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
    void notify(NotificationEvent notification);
}
