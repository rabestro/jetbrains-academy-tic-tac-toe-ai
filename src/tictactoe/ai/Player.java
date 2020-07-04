package tictactoe.ai;

import tictactoe.engine.Board;
import tictactoe.engine.Mark;

public interface Player {
    Ai create(Board board, Mark mark);
}
