package state.presstate;

import main.MainFrame;

public class SlideShowState implements PresentationViewState{
    @Override
    public void buildGUI() {
        MainFrame.getInstance().getProjectView().getPresentationView().setSlideShowMode();
    }
}
