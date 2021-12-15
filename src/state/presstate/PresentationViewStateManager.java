package state.presstate;

public class PresentationViewStateManager {

    private final EditState editState = new EditState();
    private final SlideShowState slideShowState = new SlideShowState();
    private PresentationViewState current = editState;


    public void startEditState() {
        current = editState;
    }

    public void startSlideShowState() {
        current = slideShowState;
    }

    public PresentationViewState getCurrent() {
        return current;
    }
}
