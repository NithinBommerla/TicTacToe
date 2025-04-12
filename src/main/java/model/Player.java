package model;

import model.constant.PlayerType;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;
    private boolean hasUsedUndo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHasUsedUndo() {
        return hasUsedUndo;
    }

    public void setHasUsedUndo(boolean hasUsedUndo) {
        this.hasUsedUndo = hasUsedUndo;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Constructors

    public Player(int id, String name, char symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.hasUsedUndo = false;
    }

}
