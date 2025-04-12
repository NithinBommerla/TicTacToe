package model;

import model.constant.CellState;

public class Cell {
    private Position position;
    private CellState cellState;
    // private Player player;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

//    public Player getPlayer() {
//        return player;
//    }
//
//    public void setPlayer(Player player) {
//        this.player = player;
//    }

    public Cell (Position position, CellState cellState) {
        this.position = position;
        this.cellState = cellState;
    }

}
