package temp;

import java.awt.event.ActionEvent;

public class ChangeThemeAction extends AbstractRudokAction{
    public ChangeThemeAction() {
        putValue(NAME, "Change theme");
        putValue(SHORT_DESCRIPTION, "Change theme");
        putValue(SMALL_ICON, loadIcon("/res/icons/painters_palette_brush.png")); //todo

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //todo
        new ChangeThemeDialog();
    }
}
