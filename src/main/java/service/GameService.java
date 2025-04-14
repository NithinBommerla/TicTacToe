package service;

import exception.InvalidCellException;
import model.*;
import model.constant.CellState;
import model.constant.GameState;
import service.strategy.O1WinnerCheckStrategy;
import service.strategy.WinnerCheckStrategy;

import java.util.Collections;
import java.util.List;

public class GameService {

    WinnerCheckStrategy winnerCheckStrategy;

    public GameService(int dimension) {
        this.winnerCheckStrategy = new O1WinnerCheckStrategy(dimension);
    }
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
    public Game createGame(List<Player> players, int size) {
        Board board = new Board(size);
        return new Game(board, players);
    }

    public Game startGame(Game game) {
        game.setGameState(GameState.IN_PROGRESS);
        Collections.shuffle(game.getPlayers());
        return game;
    }

    public GameState checkWinner(Game game, Move currentMove) {
        Player player = winnerCheckStrategy.checkWinner(game.getBoard(), currentMove);
        if(player != null) return GameState.WINNER;
        else return GameState.IN_PROGRESS;

    }

}
