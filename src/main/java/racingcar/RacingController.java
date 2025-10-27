package racingcar;

import io.inputview.AttemptCount;
import io.inputview.InputParser;
import io.inputview.InputView;
import io.inputview.RacingCarNames;
import io.outputview.OutputView;

public class RacingController {
    private final InputParser inputParser;
    private final RacingService racingService;

    public RacingController(InputParser inputParser, RacingService racingService) {
        this.inputParser = inputParser;
        this.racingService = racingService;
    }

    public void run() {
        RacingCarNames carNames = getCarNames();
        AttemptCount attemptCount = getAttemptCount();
        
        RacingGame racingGame = racingService.createRacingGame(carNames, attemptCount);
        
        startRacing(racingGame);
        showWinners(racingGame);
    }

    private RacingCarNames getCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = InputView.read();
        return inputParser.parseCarNames(input);
    }

    private AttemptCount getAttemptCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = InputView.read();
        return inputParser.parseAttemptCount(input);
    }

    private void startRacing(RacingGame racingGame) {
        System.out.println();
        System.out.println("실행 결과");
        racingGame.start();
    }

    private void showWinners(RacingGame racingGame) {
        OutputView.printWinners(racingGame.getWinners());
    }
}
