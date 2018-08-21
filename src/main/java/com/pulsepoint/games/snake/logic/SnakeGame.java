package com.pulsepoint.games.snake.logic;

import com.pulsepoint.games.snake.dto.Direction;
import com.pulsepoint.games.snake.dto.Level;
import com.pulsepoint.games.snake.dto.StartGameStatus;
import com.pulsepoint.games.snake.dto.TimerStatus;

/**
 * Logic for the Snake game. 
 * 
 * @author avenkatraman
 */
public interface SnakeGame {

    /**
     * Starts the game given a certain level of complexity
     * @param level level of complexity
     * @return Status of the game at the start.
     */
    StartGameStatus start(Level level);

    /**
     * Timer event which occurs during every interval of the game.
     * The logic needs to check if the snake has hit the wall or its tail.
     * Also if the snake consumes food at this step, the snake needs to grow by 1.
     * Game could end of the snake hits the wall or its tail. Also game could be won
     * if the snake has grown by a certain configurable size.
     * 
     * @return Status of the timer operation.
     */
    TimerStatus onTimer();

    /**
     * Direction change has been requested by the player.
     * 
     * @param direction direction that is requested.
     */
    void onDirectionChanged(Direction direction);
    
    
}
