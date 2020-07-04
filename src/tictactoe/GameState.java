package tictactoe;

enum GameState {
    GAME("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMPOSSIBLE("Impossible");
    final String message;

    GameState(String message) {
        this.message = message;
    }
}