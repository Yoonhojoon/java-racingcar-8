package racingcar;

public class RacingResult implements Comparable<RacingResult> {
    private final String carName;
    private final int position;

    public RacingResult(String carName, int position) {
        this.carName = carName;
        this.position = position;
    }

    public String getCarName() {
        return carName;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int compareTo(RacingResult other) {
        return Integer.compare(this.position, other.position);
    }
}
