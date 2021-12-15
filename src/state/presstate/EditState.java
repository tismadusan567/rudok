package state.presstate;

import main.MainFrame;

public class EditState implements PresentationViewState{

    @Override
    public void buildGUI() {
        MainFrame.getInstance().getProjectView().getPresentationView().setEditMode();
    }
}
