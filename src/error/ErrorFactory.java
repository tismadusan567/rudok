package error;

import main.MainFrame;
import model.NotificationEvent;
import model.NotificationTypes;
import observer.IPublisher;
import observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class ErrorFactory implements IPublisher {
    public enum ErrorType {
        NO_PRESENTATION_SELECTED, ADD_TO_SLIDE, REMOVE_WORKSPACE, BLANK_RENAME, NO_THEME_SELECTED
    }

    private static ErrorFactory instance = null;
    private final List<ISubscriber> subscribers = new ArrayList<>();

    public static ErrorFactory getInstance() {
        if (instance == null) {
            instance = new ErrorFactory();
        }
        return instance;
    }

    public void generateError(ErrorType errorType) {
        String message = switch (errorType) {
            case ADD_TO_SLIDE -> "You cant add anything to slide(yet)!";
            case NO_PRESENTATION_SELECTED -> "You have to select a presentation!";
            case REMOVE_WORKSPACE -> "You cant remove a workspace!";
            case BLANK_RENAME -> "The name cant be blank!";
            case NO_THEME_SELECTED -> "You have to select a theme";
        };
        MyError error = new MyError(message, errorType);
        notify(new NotificationEvent(NotificationTypes.ERROR, error));
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if (subscribers.contains(subscriber)) return;
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notify(NotificationEvent notification) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(notification);
        }
    }
}
