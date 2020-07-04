package tictactoe;

public enum CellState {
    EMPTY(' '), PLAYER('X'), COMPUTER('O');

    char symbol;

    CellState(char symbol) {
        this.symbol = symbol;
    }
}
