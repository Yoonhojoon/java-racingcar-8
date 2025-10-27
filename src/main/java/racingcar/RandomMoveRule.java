package racingcar;

public class RandomMoveRule implements MoveRule {
    private static final int MOVE_THRESHOLD = 4;

    @Override
    public boolean canMove(int randomValue) {
        return randomValue >= MOVE_THRESHOLD;
    }
}
