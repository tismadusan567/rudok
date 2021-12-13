package state.presstate;

public class PresentationViewStateManager {

    private EditState editState = new EditState();
    private SlideShowState slideShowState = new SlideShowState();
    private PresentationViewState currentState = editState;


    public void setEditState() {
        currentState = editState;
    }

    public void setSlideShowState() {
        currentState = slideShowState;
    }

    public PresentationViewState getCurrentState() {
        return currentState;
    }
}
