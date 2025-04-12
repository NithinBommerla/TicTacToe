package model;

import model.constant.CellState;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> cells;
    private int size;

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Board(int size) {
        this.size = size;
        // Given size N we have to create a board(cells) Matrix of N;
        this.cells = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            cells.add(new ArrayList<>(size));
            for(int j = 0; j < size; j++) {
                cells.get(i).add(new Cell(new Position(i, j)));
            }
        }
    }

    public Board clone() {
        Board boardClone = new Board(this.size);
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                boardClone.cells.get(i).set(j, this.getCells().get(i).get(j));
            }
        }
        return boardClone;
    }
}
