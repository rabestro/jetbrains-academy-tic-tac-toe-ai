package tictactoe.engine;

public enum State {
    PLAYING("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMPOSSIBLE("Impossible");
    final String message;

    State(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }
}