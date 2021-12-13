package view;

import model.NotificationEvent;
import model.Slot;
import observer.ISubscriber;

import java.awt.*;

public class SlotView implements ISubscriber {
    private Rectangle rectangle;
    private Slot slot;

    @Override
    public void update(NotificationEvent notification) {

    }
}
