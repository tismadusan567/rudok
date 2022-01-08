package action.dialog;

import main.MainFrame;
import model.slot.Slot;
import model.slot.SlotContent;
import model.slot.TextTags;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class EditTextSlotDialog extends JDialog {
    private final Slot slot;
    private final JTextPane textPane = new JTextPane();
    private final JButton btnBold = new JButton("Bold");
    private final JButton btnItalic = new JButton("Italic");
    private final JButton btnUnderline = new JButton("Underline");
    private final JButton btnSave = new JButton("Save");


    public EditTextSlotDialog(Slot slot) {
        super(MainFrame.getInstance(), "Edit text", true);
        this.slot = slot;

        Font f = textPane.getFont();
        textPane.setFont(new Font(f.getFamily(), Font.PLAIN, 25));

        setSize(500, 500);
        setLocationRelativeTo(MainFrame.getInstance());
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnBold);
        buttonPanel.add(btnItalic);
        buttonPanel.add(btnUnderline);

        add(buttonPanel, BorderLayout.NORTH);
        add(textPane, BorderLayout.CENTER);
        add(btnSave, BorderLayout.SOUTH);

        initText();

        addListeners();

        setVisible(true);
    }

    private void addListeners() {
        btnSave.addActionListener(e -> saveFormattedText());
        btnBold.addActionListener(e -> editText(Style.BOLD));
        btnItalic.addActionListener(e -> editText(Style.ITALIC));
        btnUnderline.addActionListener(e -> editText(Style.UNDERLINE));
    }

    private void initText() {
        if (slot.getSlotContent() == null) return;
        String strippedString = TextTags.stripOfTags(slot.getSlotContent().getContentString());
        StyledDocument doc = textPane.getStyledDocument();
        textPane.setText(strippedString);

        int pos = 0;
        boolean bold = false;
        boolean italic = false;
        boolean underline = false;
        for (int i = 0; i < slot.getSlotContent().getContentString().length(); i++) {
            char c = slot.getSlotContent().getContentString().charAt(i);
            switch (c) {
                case TextTags.BOLD -> bold = !bold;
                case TextTags.ITALIC -> italic = !italic;
                case TextTags.UNDERLINE -> underline = !underline;
                default -> {
                    AttributeSet as = doc.getCharacterElement(pos).getAttributes();
                    MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());

                    StyleConstants.setBold(asNew, bold);
                    StyleConstants.setItalic(asNew, italic);
                    StyleConstants.setUnderline(asNew, underline);
                    doc.setCharacterAttributes(pos, 1, asNew, true);

                    pos++;
                }
            }
        }
    }

    private void saveFormattedText() {
        if (textPane.getText().isEmpty()) return;

        boolean bold = false;
        boolean italic = false;
        boolean underline = false;
        StyledDocument doc = textPane.getStyledDocument();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < textPane.getText().length(); i++) {
            AttributeSet as = doc.getCharacterElement(i).getAttributes();
            if (bold != StyleConstants.isBold(as)) {
                sb.append(TextTags.BOLD);
                bold = !bold;
            }
            if (italic != StyleConstants.isItalic(as)) {
                sb.append(TextTags.ITALIC);
                italic = !italic;
            }
            if (underline != StyleConstants.isUnderline(as)) {
                sb.append(TextTags.UNDERLINE);
                underline = !underline;
            }
            sb.append(textPane.getText().charAt(i));
        }

        SlotContent newContent = new SlotContent(sb.toString());
        slot.setSlotContent(newContent);
        dispose();
    }

    private enum Style {
        BOLD,
        ITALIC,
        UNDERLINE
    }

    private void editText(Style style) {
        int selectionStart = textPane.getSelectionStart();
        int selectionEnd = textPane.getSelectionEnd();
        if (selectionStart == selectionEnd) return;

        StyledDocument doc = textPane.getStyledDocument();
        String selectedText = textPane.getSelectedText();

        boolean hasPlain = false;
        for (int i = 0; i < selectedText.length(); i++) {
            AttributeSet as = doc.getCharacterElement(selectionStart + i).getAttributes();
            if (!switch (style) {
                case BOLD -> StyleConstants.isBold(as);
                case ITALIC -> StyleConstants.isItalic(as);
                case UNDERLINE -> StyleConstants.isUnderline(as);
            }) {
                hasPlain = true;
            }
        }

        for (int i = 0; i < selectedText.length(); i++) {
            AttributeSet as = doc.getCharacterElement(selectionStart + i).getAttributes();
            MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
            switch (style) {
                case BOLD -> StyleConstants.setBold(asNew, hasPlain);
                case ITALIC -> StyleConstants.setItalic(asNew, hasPlain);
                case UNDERLINE -> StyleConstants.setUnderline(asNew, hasPlain);
            }
            doc.setCharacterAttributes(selectionStart + i, 1, asNew, true);
        }
    }
}
