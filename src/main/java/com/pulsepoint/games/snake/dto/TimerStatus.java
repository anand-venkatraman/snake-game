package com.pulsepoint.games.snake.dto;

import java.util.*;

/**
 * Status of the Game after the timer event is processed
 * 
 * @author avenkatraman
 */
public class TimerStatus {
    
    private final Collection<Pos> snakePositions;
    private final Pos foodPosition;
    private final GameStatus status;
    
    public TimerStatus(Collection<Pos> snakePositions, Pos foodPosition, GameStatus status) {
        this.snakePositions = snakePositions;
        this.foodPosition = foodPosition;
        this.status = status;
    }

    public Collection<Pos> getSnakePositions() {
        return snakePositions;
    }

    public Pos getFoodPosition() {
        return foodPosition;
    }

    public GameStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimerStatus that = (TimerStatus) o;
        return Objects.equals(snakePositions, that.snakePositions) &&
                Objects.equals(foodPosition, that.foodPosition) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(snakePositions, foodPosition, status);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TimerStatus.class.getSimpleName() + "[", "]")
                .add("snakePositions=" + snakePositions)
                .add("foodPosition=" + foodPosition)
                .add("status=" + status)
                .toString();
    }
}
