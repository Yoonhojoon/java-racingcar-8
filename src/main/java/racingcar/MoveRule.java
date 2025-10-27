package racingcar;

public interface MoveRule {
    boolean canMove(int randomValue);
    
    void moveCar(Car car);
}
