package io.inputview;

public class AttemptCount {
    private final int count;

    public AttemptCount(int count) {
        this.count = validateCount(count);
    }

    private int validateCount(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
        return count;
    }

    public int getCount() {
        return count;
    }
}
