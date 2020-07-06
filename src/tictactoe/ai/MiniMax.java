package tictactoe.ai;

import tictactoe.engine.Board;
import tictactoe.engine.Mark;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class MiniMax extends Ai {
    public MiniMax(Board board, Mark mark) {
        super(board, mark);
    }

    @Override
    public int getMove() {
        System.out.println("Making move level Hard");
        log.info("Thinking MiniMax");
        return mm(0, true);
    }

    private int minimax(boolean isMaximum) {
        var score = evaluate();
        if (score != 0 || board.getCellsCount(Mark.EMPTY) == 0) {
            return score;
        }
        final var freeCells = board.getFreeCells();
        final var values = new HashMap<Integer, Integer>();

        for (var i : freeCells) {
            board.set(i, isMaximum ? Mark.X : Mark.O);
            var v = minimax(!isMaximum);
            board.set(i, Mark.EMPTY);
            values.put(i, v);
        }
        score = isMaximum
                ? values.values().stream().max(Integer::compareTo).orElseThrow()
                : values.values().stream().min(Integer::compareTo).orElseThrow();

        return score;
    }

    private int mm(int level, boolean isMax) {
        final var values = new HashMap<Integer, Integer>();
        for (var index : board.getFreeCells()) {
            board.set(index, isMax ? Mark.X : Mark.O);
            int score;
            switch (board.getState()) {
                case X_WINS:
                    score = 10;
                    break;
                case O_WINS:
                    score = -10;
                    break;
                case PLAYING:
                    score = mm(level + 1, !isMax);
                    break;
                case DRAW:
                default:
                    score = 0;
            }
            board.set(index, Mark.EMPTY);
            values.put(index, score);
        }
        if (level == 0) {
            log.info(values.toString());
        }
        var outcomes = values.values().stream();
        var target = isMax
                ? outcomes.max(Integer::compareTo).orElseThrow()
                : outcomes.min(Integer::compareTo).orElseThrow();

        if (level == 0) {
            log.info(values.toString());
            var cells = values.keySet().stream()
                    .filter(key -> values.get(key) == target)
                    .collect(Collectors.toList());
            return cells.get(random.nextInt(cells.size()));
        }
        return target;
    }

    private int evaluate() {
        switch (board.getState()) {
            case X_WINS:
                return 10;
            case O_WINS:
                return -10;
            default:
                return 0;
        }
    }
}
