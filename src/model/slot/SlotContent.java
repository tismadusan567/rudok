package model.slot;

import java.io.Serializable;

public class SlotContent implements Serializable {
    private final String contentString;

    public SlotContent(String content) {
        this.contentString = content;
    }

    public String getContentString() {
        return contentString;
    }
}
