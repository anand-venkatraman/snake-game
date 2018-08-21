package com.pulsepoint.games.snake.dto;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Position 0-indexed coordinates of row and column 
 * 
 * @author avenkatraman
 */
public class Pos {
    
    private final int row;
    private final int col;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pos pos = (Pos) o;
        return row == pos.row &&
                col == pos.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pos.class.getSimpleName() + "[", "]")
                .add("row=" + row)
                .add("col=" + col)
                .toString();
    }
}
