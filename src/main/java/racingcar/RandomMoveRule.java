package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMoveRule implements MoveRule {
    private static final int MOVE_THRESHOLD = 4;
    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 9;

    @Override
    public boolean canMove(int randomValue) {
        return randomValue >= MOVE_THRESHOLD;
    }

    @Override
    public void moveCar(Car car) {
        int randomValue = Randoms.pickNumberInRange(RANDOM_MIN, RANDOM_MAX);
        car.move(this, randomValue);
    }
}
