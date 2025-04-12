package service;

import exception.InvalidCellException;
import model.Cell;
import model.Game;
import model.Move;
import model.Player;
import model.constant.CellState;

public class GameService {

    // Methods

    public Move executeMove(Player player, Game game, int row, int col) {
        Cell cell = game.getBoard().getCells().get(row).get(col); // row and col are used to identify the position of cell
                                                                // in the board which is an (matrix) List<List<>>().
        if(!cell.getCellState().equals(CellState.EMPTY)) { // Checking whether the cell to which move is made is empty or not.
            throw new InvalidCellException("Invalid cell has been chosen for the move, Please Try with another move");
        }
        cell.setCellState(CellState.FULL);
        cell.setPlayer(player);
        Move move = new Move(player, cell);
        game.getMoveHistory().add(move);
        game.getBoardHistory().add(game.getBoard().clone());
        return move;
    }

    public Move executeMove(Player player, Game game) {

        return null;
    }
}
