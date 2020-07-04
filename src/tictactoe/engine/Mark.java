package tictactoe.engine;

public enum Mark {
    EMPTY(' '), X('X'), O('O');

    private char symbol;

    public char getSymbol() {
        return symbol;
    }

    Mark(char symbol) {
        this.symbol = symbol;
    }
}
