package slidesort;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int[][] _grid;

    /**
     * Create a new grid
     *
     * @param seedArray is not null
     *                  and seedArray.length > 0
     *                  and seedArray[0].length > 0
     */
    public Grid(int[][] seedArray) {
        int rows = seedArray.length;
        int cols = seedArray[0].length;
        _grid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                _grid[i][j] = seedArray[i][j];
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Grid) {
            Grid g2 = (Grid) other;
            if (this._grid.length != g2._grid.length) {
                return false;
            }
            if (this._grid[0].length != g2._grid[0].length) {
                return false;
            }
            int rows = _grid.length;
            int cols = _grid[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (this._grid[i][j] != g2._grid[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if this grid is a valid grid.
     * A grid is valid if, for c = min(rows, cols),
     * the grid contains zero or more values in [1, c]
     * exactly once and all other entries are 0s.
     *
     * @return true if this is a valid grid and false otherwise
     */
    public boolean isValid() {
        int row = _grid.length;
        int col = _grid[0].length;
        List<Integer> seq = seq();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int a = 0; a < seq.size(); a++) {
                    if (seq.get(a) == _grid[i][j]) {
                        seq.remove(a);
                    } else if (_grid[i][j] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if this grid is sorted.
     * A grid is sorted iff it is valid and,
     * for all pairs of entries (x, y)
     * such that x > 0 and y > 0,
     * if x < y then the position(x) < position(y).
     * If x is at location (i, j) in the grid
     * then position(x) = i * (number of cols) + j.
     *
     * @return true if the grid is sorted and false otherwise.
     */
    public boolean isSorted() {
        int row = _grid.length;
        int col = _grid[0].length;
        int xposition = -1;
        List<Integer> seq = seq();
        for (int a = 0; a < seq.size(); a++) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (_grid[i][j] == seq.get(a)) {
                        int yposition = (col * i) + j;
                        if (yposition > xposition) {
                            xposition = yposition;
                        } else {
                            return false;
                        }
                    }

                }
            }
        }

        return true;
    }

    /**
     * Check if a list of moves is feasible.
     * A move is feasible if it starts with a non-zero entry,
     * does not move that number off the grid,
     * and it does not involve jumping over another non-zero number.
     *
     * @param moveList is not null.
     * @return true if the list of moves are all feasible
     * and false otherwise.
     * By definition an empty list is always feasible.
     */
    public boolean validMoves(List<Move> moveList) {
        int col = _grid[0].length;
        int row = _grid.length;
        int[][] newgrid = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newgrid[i][j] = _grid[i][j];

            }
        }
        for (Move m : moveList) {
            int xstart = m.startingPosition.i;
            int ystart = m.startingPosition.j;
            int xend = xstart;
            int yend = ystart;
            if (newgrid[xstart][ystart] == 0) {
                return false;
            }

            if (m.rowMove) {
                yend += m.displacement;
                if (yend >= col || yend<0) {
                    return false;
                }
                if (m.displacement > 0) {
                    for (int j = yend; j > ystart; j--) {
                        if (newgrid[xstart][j] != 0) {
                            return false;
                        }
                    }
                } else {
                    for (int j = yend; j < ystart; j++) {
                        if (newgrid[xstart][j] != 0) {
                            return false;
                        }
                    }
                }

            } else {
                xend += m.displacement;
                if (xend >= row || xend<0) {
                    return false;
                }
                if (m.displacement > 0) {
                    for (int i = xend; i > xstart; i--) {
                        if (newgrid[i][ystart] != 0) {
                            return false;
                        }
                    }
                } else {
                    for (int i = xend; i < xstart; i++) {
                        if (newgrid[i][ystart] != 0) {
                            return false;
                        }
                    }
                }
            }
            newgrid[xend][yend] = newgrid[xstart][ystart];
            newgrid[xstart][ystart] = 0;
        }
        return true;
    }

    /**
     * Apply the moves in moveList to this grid
     *
     * @param moveList is a valid list of moves
     */
    public void applyMoves(List<Move> moveList) {
        for (Move m : moveList) {
            int xstart = m.startingPosition.i;
            int ystart = m.startingPosition.j;
            int xend = xstart;
            int yend = ystart;
            int displacement = m.displacement;
            boolean MoveAlongRow = m.rowMove;
            if (MoveAlongRow) {
                yend += displacement;
            } else {
                xend += displacement;
            }
            _grid[xend][yend] = _grid[xstart][ystart];
            _grid[xstart][ystart] = 0;

        }
    }
    private Move tryMove(Integer value, Position startPosition, boolean rowMove){
        Move move;
        int diff = value -(rowMove? startPosition.j: startPosition.i)-1;
        if(diff != 0) {
            for (int i = diff; i != 0; ) {
                move = new Move(startPosition, rowMove, diff);
                if (validMoves(List.of(move))) {
                    return move;
                }
                if (i < 0) {
                    i++;
                } else {
                    i--;
                }
            }
        }
        return null;
    }

    /**
     * Return a list of moves that, when applied, would convert this grid
     * to be sorted
     *
     * @return a list of moves that would sort this grid
     */
    public List<Move> getSortingMoves() {
        int[][] newGrid = _grid.clone();
        List<Move> result = new ArrayList<>();
        while(!isSorted()){
            for (int i = 0; i< _grid.length; i++){
                for (int j = 0; j < _grid[i].length; j++) {
                    int value = _grid[i][j];
                    if(value!=0 && (value!=i+1 || value!=j+1)){
                        Move moveCol = tryMove(value, new Position(i,j),true);
                        if(moveCol!=null){
                            result.add(moveCol);
                            applyMoves(List.of(moveCol));
                        }else{
                            Move moveRow = tryMove(value, new Position(i,j),false);
                            if(moveRow!=null){
                                result.add(moveRow);
                                applyMoves(List.of(moveRow));
                            }
                        }
                    }

                }
            }
        }
        _grid= newGrid;
        return result;
    }

    public List<Integer> seq() {
        int row = _grid.length;
        int col = _grid[0].length;
        int c = 0;
        if (row <= col) {
            c = row;
        } else {
            c = col;
        }
        ArrayList<Integer> seq = new ArrayList<Integer>(c);
        for (int k = 1; k <= c; k++) {
            seq.add(k);
        }
        return seq;//create a seq of integers from 1 to c;
    }
}