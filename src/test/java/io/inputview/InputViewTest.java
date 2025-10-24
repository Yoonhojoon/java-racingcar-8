package io.inputview;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InputViewTest {

    @Test
    @DisplayName("입출력 라인을 입력받는다")
    void read_input_success() {
        // given
        String simulatedInput = "HelloWorld\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        // when
        String result = InputView.read();

        // then
        assertThat(result).isEqualTo("HelloWorld");
    }

    @Test
    @DisplayName("입력이 되지 않으면 에러반환")
    void read_input_error() {
        String simulatedInput = null;
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        //when
        String result = InputView.read();

        //then
        assertThat(result).isNull();
    }
}