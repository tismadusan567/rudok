package view.slot;

import main.MainFrame;
import model.slot.Slot;
import model.slot.SlotContent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageSlotHandler extends SlotHandler {
    private Image image = null;

    public ImageSlotHandler(Slot slot) {
        super(slot);
    }

    @Override
    public void paintContent(Graphics2D g2d) {
        if(image == null) return;
        Dimension size = slot.getSize();
        Point pos = slot.getPos();
        g2d.drawImage(image, pos.x, pos.y, size.width, size.height, MainFrame.getInstance());
    }

    @Override
    public void format() {
        SlotContent slotContent = slot.getSlotContent();
        if(slotContent == null) return;
        try {
            Dimension size = slot.getSize();
            image = ImageIO.read(new File(slotContent.getContentString())).getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
