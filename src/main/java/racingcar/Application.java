package racingcar;

import io.inputview.InputParser;

public class Application {
    public static void main(String[] args) {
        try {
            InputParser inputParser = new InputParser();
            MoveRule moveRule = new RandomMoveRule();
            RacingService racingService = new RacingService(moveRule);
            RacingController racingController = new RacingController(inputParser, racingService);
            
            racingController.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
