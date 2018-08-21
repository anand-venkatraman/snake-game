package com.pulsepoint.games.snake.dto;

import java.util.Collection;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents the status at the start of the game.
 * 
 * @author avenkatraman
 */
public class StartGameStatus {
    
    private final int rows;
    private final int columns;
    private final Collection<Pos> snakePositions;
    private final int timerDelay;

    public StartGameStatus(int rows, int columns, Collection<Pos> snakePositions, int timerDelay) {
        this.rows = rows;
        this.columns = columns;
        this.snakePositions = snakePositions;
        this.timerDelay = timerDelay;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Collection<Pos> getSnakePositions() {
        return snakePositions;
    }

    public int getTimerDelay() {
        return timerDelay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartGameStatus that = (StartGameStatus) o;
        return rows == that.rows &&
                columns == that.columns &&
                timerDelay == that.timerDelay &&
                Objects.equals(snakePositions, that.snakePositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, columns, snakePositions, timerDelay);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StartGameStatus.class.getSimpleName() + "[", "]")
                .add("rows=" + rows)
                .add("columns=" + columns)
                .add("snakePositions=" + snakePositions)
                .add("timerDelay=" + timerDelay)
                .toString();
    }
}
