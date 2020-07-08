package tictactoe.ai;

import tictactoe.engine.Board;
import tictactoe.engine.Mark;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Hard extends Ai {
    public Hard(Board board, Mark mark) {
        super(board, mark);
    }

    @Override
    public int getMove() {
        System.out.println("Making move level Hard");
        if (board.isEmpty()) {
            return board.getRandomFree();
        }
        log.fine("Thinking MiniMax");
        return minimax(0, mark == Mark.X);
    }

    private int minimax(int level, boolean isMax) {
        final var scores = new HashMap<Integer, Integer>();
        for (var index : board.getFreeCells()) {
            board.set(index, isMax ? Mark.X : Mark.O);
            int score;
            switch (board.getState()) {
                case X_WINS:
                    score = 10 - level;
                    break;
                case O_WINS:
                    score = level - 10;
                    break;
                case PLAYING:
                    score = minimax(level + 1, !isMax);
                    break;
                case DRAW:
                default:
                    score = 0;
            }
            board.set(index, Mark.EMPTY);
            scores.put(index, score);
        }
        final var outcomes = scores.values().stream();
        final var target = isMax
                ? outcomes.max(Integer::compareTo).orElseThrow()
                : outcomes.min(Integer::compareTo).orElseThrow();

        if (level == 0) {
            log.fine(scores::toString);
            var cells = scores.keySet().stream()
                    .filter(key -> scores.get(key) == target)
                    .collect(Collectors.toList());
            return cells.get(random.nextInt(cells.size()));
        }
        return target;
    }
}
