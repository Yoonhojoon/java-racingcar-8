package io.inputview;

import java.util.Collections;
import java.util.List;

public class RacingCarNames {
    private final List<String> names;

    public RacingCarNames(List<String> names) {
        this.names = validateNames(names);
    }

    private List<String> validateNames(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름 목록이 null이거나 비어있습니다.");
        }

        for (String name : names) {
            validateName(name);
        }
        return names;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 null이거나 빈 값일 수 없습니다.");
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
        }
    }

    public List<String> getNames() {
        return Collections.unmodifiableList(names);
    }

    public int getCount() {
        return names.size();
    }

    public boolean isEmpty() {
        return names.isEmpty();
    }
}
