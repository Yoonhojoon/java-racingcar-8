package racingcar;

import java.util.List;

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
        OutputView.printCarNamePrompt();
        String input = InputView.read();
        return inputParser.parseCarNames(input);
    }

    private AttemptCount getAttemptCount() {
        OutputView.printAttemptCountPrompt();
        String input = InputView.read();
        return inputParser.parseAttemptCount(input);
    }

    private void startRacing(RacingGame racingGame) {
        OutputView.printRacingStartMessage();
        
        racingGame.startWithCallback(() -> printCurrentResults(racingGame));
    }
    
    private void printCurrentResults(RacingGame racingGame) {
        List<RacingResult> results = racingGame.getResults();
        OutputView.printRacingResults(results);
    }

    private void showWinners(RacingGame racingGame) {
        OutputView.printWinners(racingGame.getWinners());
    }
}
