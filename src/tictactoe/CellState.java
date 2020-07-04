package tictactoe;

public enum CellState {
    EMPTY(' '), X('X'), O('O');

    private char symbol;

    public char getSymbol() {
        return symbol;
    }

    CellState(char symbol) {
        this.symbol = symbol;
    }
}
