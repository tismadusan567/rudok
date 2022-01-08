package view.slot;

import model.slot.Slot;
import model.slot.SlotContent;
import model.slot.TextTags;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Map;

public class TextSlotHandler extends SlotHandler {
    private AttributedString attributedString = null;

    public TextSlotHandler(Slot slot) {
        super(slot);
    }

    @Override
    public void paintContent(Graphics2D g2d) {
        if (slot.getSlotContent() == null) return;
        g2d.setPaint(Color.BLACK);
        g2d.drawString(attributedString.getIterator(), slot.getPos().x + 2, slot.getPos().y + 20);
    }

    @Override
    public void format() {
        SlotContent slotContent = slot.getSlotContent();
        if (slotContent == null) return;
        attributedString = new AttributedString(TextTags.stripOfTags(slotContent.getContentString()));
        int pos = 0;
        Map<AttributedCharacterIterator.Attribute, Object> attributes = new HashMap<>();
        for (int i = 0; i < slotContent.getContentString().length(); i++) {
            char c = slotContent.getContentString().charAt(i);
            switch (c) {
                case TextTags.BOLD -> {
                    if(attributes.containsKey(TextAttribute.WEIGHT)) attributes.remove(TextAttribute.WEIGHT);
                    else attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
                }
                case TextTags.ITALIC -> {
                    if(attributes.containsKey(TextAttribute.POSTURE)) attributes.remove(TextAttribute.POSTURE);
                    else attributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
                }
                case TextTags.UNDERLINE -> {
                    if(attributes.containsKey(TextAttribute.UNDERLINE)) attributes.remove(TextAttribute.UNDERLINE);
                    else attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                }
                default -> {
                    attributedString.addAttributes(attributes, pos, pos+1);
                    pos++;
                }
            }
        }
    }
}
