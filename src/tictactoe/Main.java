package tictactoe;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        game.askField();
        game.printBoard();
        game.askUser();
        game.checkState();
        game.printBoard();
        game.printMessage();
    }
}


