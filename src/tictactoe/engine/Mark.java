package tictactoe.engine;

public enum Mark {
    EMPTY(' '), X('X'), O('O');

    private char mark;

    public char getMark() {
        return mark;
    }

    Mark(char mark) {
        this.mark = mark;
    }
}
