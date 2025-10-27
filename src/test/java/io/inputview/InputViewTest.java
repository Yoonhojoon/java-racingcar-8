package io.inputview;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {

    @Test
    @DisplayName("입출력 라인을 입력받는다")
    void read_input_success() {
        // given
        String simulatedInput = "HelloWorld";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        InputStream originalIn = System.in;
        System.setIn(in);

        try {
            // when
            String result = InputView.read();

            // then
            assertThat(result).isEqualTo("HelloWorld");
        } finally {
            System.setIn(originalIn);
        }
    }


}