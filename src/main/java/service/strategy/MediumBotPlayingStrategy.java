package service.strategy;

import model.*;
import model.constant.CellState;

import java.util.List;

public class MediumBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move executeMove(Player player, Game game) {
        Board board = game.getBoard();
        Move move = null;
        for(List<Cell> cells : board.getCells()) {
            for(Cell cell : cells) {
                if(cell.getCellState().equals(CellState.EMPTY)) {
                    cell.setCellState(CellState.FULL);
                    cell.setPlayer(player);
                    move = new Move(player, cell);
                    game.getMoveHistory().add(move);
                    game.getBoardHistory().add(board.clone());
                    // TODO: Randomize the move for the Bot
                    // Identify all the empty cells and choose a cell randomly

                }
            }
        }
        return move;
    }
}
