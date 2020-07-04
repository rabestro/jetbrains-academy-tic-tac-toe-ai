package tictactoe;

public enum CellState {
    EMPTY(' '), X('X'), O('O');

    char symbol;

    CellState(char symbol) {
        this.symbol = symbol;
    }
}
