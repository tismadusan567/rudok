package model.slot;

import java.awt.*;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public class SerializableStrokeAdapter implements Serializable {

    private Stroke stroke;

    public SerializableStrokeAdapter(Stroke s) {
        this.stroke = s;
    }

    /**
     * Podrska za serijalizaciju objekata
     */
    @Serial
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        if (stroke instanceof BasicStroke s) {
            out.writeFloat(s.getLineWidth());
            out.writeInt(s.getEndCap());
            out.writeInt(s.getLineJoin());
            out.writeFloat(s.getMiterLimit());
            out.writeObject(s.getDashArray());
            out.writeFloat(s.getDashPhase());
        }
    }

    @Serial
    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        stroke = new BasicStroke(in.readFloat(), in.readInt(), in.readInt(),
                in.readFloat(), (float[]) in.readObject(), in.readFloat());
    }

    public Stroke getStroke() {
        return stroke;
    }
}
