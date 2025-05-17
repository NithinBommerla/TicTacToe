package service.strategy.winnerCheckStrategy;

import exception.GameDrawnException;
import model.Board;
import model.Move;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class O1WinnerCheckStrategy implements WinnerCheckStrategy{
    HashMap<Character, Integer> leftDiagonalMap;
    HashMap<Character, Integer> rightDiagonalMap;
    HashMap<Character, Integer> cornerMap;
    List<HashMap<Character, Integer>> rowMaps;
    List<HashMap<Character, Integer>> columnMaps;

    // Constructor

    public O1WinnerCheckStrategy(int size) {
        this.leftDiagonalMap = new HashMap<> ();
        this.rightDiagonalMap = new HashMap<> ();
        this.cornerMap = new HashMap<>();
        this.rowMaps = new ArrayList<>();
        this.columnMaps = new ArrayList<>();
        initializeMaps(size);
    }

    @Override
    public Player checkWinner(Board board, Move currentMove) {
        int size = board.getSize();
        // fetch details
        char symbol = currentMove.getPlayer().getSymbol();
        int row = currentMove.getCell().getPosition().getRow();
        int column = currentMove.getCell().getPosition().getColumn();

        // Update the maps
        // Update row and column Maps
        HashMap<Character, Integer> rowMap = rowMaps.get(row);
        HashMap<Character, Integer> columnMap = columnMaps.get(column);
        rowMap.put(symbol, rowMap.getOrDefault(symbol, 0) + 1 );
        columnMap.put(symbol, columnMap.getOrDefault(symbol, 0) + 1 );
        // Check for winner in row and column Maps
        if(rowMap.get(symbol) == size || columnMap.get(symbol) == size) return currentMove.getPlayer(); // Not using Get or default because every move is definitely associated with a row and a column.
        // Update the diagonalMaps if applicable
        if(row == column) leftDiagonalMap.put(symbol, leftDiagonalMap.getOrDefault(symbol, 0) + 1 );
        if(row + column == size - 1) rightDiagonalMap.put(symbol, rightDiagonalMap.getOrDefault(symbol, 0) + 1 );
        // Check for winner in diagonal map
        if(leftDiagonalMap.getOrDefault(symbol, 0) == size || rightDiagonalMap.getOrDefault(symbol, 0) == size) return currentMove.getPlayer();
        // Update the cornerMap if applicable
        if((row == 0 || row == size-1) && (column == 0 || column == size-1)) cornerMap.put(symbol, cornerMap.getOrDefault(symbol, 0) + 1 );
        // Check for winner in corner map
        if(cornerMap.getOrDefault(symbol, 0) == 4) return currentMove.getPlayer();
        // Check for draw if there is no winner
        if(checkDraw()) {
            throw new GameDrawnException("Game is Drawn and there can be No Winner");
        }

        return null;
    }

    public boolean checkDraw() {
        // TODO: Optimise the TC by using count of Hashmaps instead of iterating everytime.
        for(HashMap<Character, Integer> map : rowMaps) if(map.size() <= 1) return false;
        for(HashMap<Character, Integer> map : columnMaps) if(map.size() <= 1) return false;
        if(leftDiagonalMap.size() <= 1 || rightDiagonalMap.size() <= 1 || cornerMap.size() <= 1) return false;
        // return topLeftDiagonal.size() > 1 && topRightDiagonal.size() > 1 && cornerMap.size() > 1;
        return true;
    }

    public void initializeMaps(int size) {
        for(int i = 0; i < size; i++) {
            rowMaps.add(new HashMap<>());
            columnMaps.add(new HashMap<>());
        }
    }

    @Override
    public void undoMove(Board board, Move currentMove) {
        int size = board.getSize();
        // fetch details
        char symbol = currentMove.getPlayer().getSymbol();
        int row = currentMove.getCell().getPosition().getRow();
        int column = currentMove.getCell().getPosition().getColumn();

        // Update the maps
        // Update row and column Maps
        HashMap<Character, Integer> rowMap = rowMaps.get(row);
        HashMap<Character, Integer> columnMap = columnMaps.get(column);
        rowMap.put(symbol, rowMap.get(symbol) - 1 );
        columnMap.put(symbol, columnMap.get(symbol) - 1 );
        // Update the diagonalMaps if applicable
        if(row == column) leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol) - 1 );
        if(row + column == size - 1) rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol) - 1 );
        // Update the cornerMap if applicable
        if((row == 0 || row == size-1) && (column == 0 || column == size-1)) cornerMap.put(symbol, cornerMap.get(symbol) - 1 );
    }

}

/*
In O(1) Method : As we already figured that in order to check for winner in O(1) Time
we maintain Hashmaps for each row, column, diagonal and also corner(Special case for fun)
When a move is made by any player the hashmaps which are needed to updated by that particular move will be updated.

              0 1 2
            0 _ _ _ -> HM1
            1 _ _ _ -> HM2
            2 _ _ _ -> HM3
             HM HM HM

Total we have 2N + 3 Maps

When a move is made which maps to check and update?

Every move is associated with a row and column
1. rowMap -> Update and check
2. columnMap -> Update and check
3. if(row == column) topLeftMap -> Update and check
4. if(row + column == N-1) topRightMap -> Update and check
5. if((row == 0 || row == N-1) && (column == 0 || column == N-1)) corner -> Update and check

Checking Draw

A Game is said to be drawn when neither of players are eligible to win. i.e.
If in row or column or diagonal or corner map, there is more than one symbol then winner cannot be formed through that.
(each symbol represents a player)
If in all rows, all columns, all diagonals and Corner Maps there is more than one symbol
Then the board cannot have a winner and the game can be declared draw.

 */
