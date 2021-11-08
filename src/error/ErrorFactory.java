package error;

import main.MainFrame;
import observer.IPublisher;
import observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class ErrorFactory implements IPublisher {
    public enum ErrorType {
        NO_PRESENTATION_SELECTED, ADD_TO_SLIDE, REMOVE_WORKSPACE
    }

    private static ErrorFactory instance = null;
    private final List<ISubscriber> subscribers = new ArrayList<>();

    private ErrorFactory() {

    }

    public static ErrorFactory getInstance() {
        if (instance == null) {
            instance = new ErrorFactory();
//            instance.init();
        }
        return instance;
    }

    public void generateError(ErrorType errorType) {
        String message = switch (errorType) {
            case ADD_TO_SLIDE -> "You cant add anything to slide(yet)!";
            case NO_PRESENTATION_SELECTED -> "You have to select a presentation!";
            case REMOVE_WORKSPACE -> "You cant remove a workspace!";
        };
        MyError error = new MyError(message, errorType);
        notify(error);
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
    public void notify(Object notification) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(notification);
        }
    }
}
