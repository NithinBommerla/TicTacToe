package service.strategy.winnerCheckStrategy;

import model.Board;
import model.Move;
import model.Player;

public interface WinnerCheckStrategy {
    Player checkWinner(Board board, Move currentMove);
    void undoMove(Board board, Move currentMove);
}
