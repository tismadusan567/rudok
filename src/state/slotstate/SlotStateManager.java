package state.slotstate;

public class SlotStateManager {
    private final AddSlotState addSlotState = new AddSlotState();
    private final RemoveSlotState removeSlotState = new RemoveSlotState();
    private final MoveSlotState moveSlotState = new MoveSlotState();
    private final SelectSlotState selectSlotState = new SelectSlotState();
    private SlotState current = addSlotState;

    public void startAddState() {
        current = addSlotState;
    }

    public void startRemoveState() {
        current = removeSlotState;
    }

    public void startMoveState() {
        current = moveSlotState;
    }

    public void startSelectState() {
        current = selectSlotState;
    }

    public SlotState getCurrent() {
        return current;
    }
}
