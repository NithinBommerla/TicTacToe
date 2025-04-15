package model;

import model.constant.GameState;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private Player currentPlayer;
    private Player Winner;
    private List<Move> moveHistory;
    private List<Board> boardHistory;

    // Constructor
    public Game(Board board, List<Player> players) {
        this.board = board;
        this.boardHistory = new ArrayList<>();
        this.moveHistory = new ArrayList<>();
        this.players = players;
        this.gameState = GameState.YET_TO_START;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getWinner() {
        return Winner;
    }

    public void setWinner(Player winner) {
        Winner = winner;
    }

    public List<Move> getMoveHistory() {
        return moveHistory;
    }

    public void setMoveHistory(List<Move> moveHistory) {
        this.moveHistory = moveHistory;
    }

    public List<Board> getBoardHistory() {
        return boardHistory;
    }

    public void setBoardHistory(List<Board> boardHistory) {
        this.boardHistory = boardHistory;
    }
}
