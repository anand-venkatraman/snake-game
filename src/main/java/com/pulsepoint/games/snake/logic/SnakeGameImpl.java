package com.pulsepoint.games.snake.logic;

import com.google.common.collect.ImmutableMap;
import com.pulsepoint.games.snake.dto.*;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.RandomUtils.nextInt;

/**
 * Sample implementation of the snake game.
 * 
 * @author avenkatraman
 */
public class SnakeGameImpl implements SnakeGame {

    private Map<Level, Integer> timerSpeed = ImmutableMap.of(Level.EASY, 160, Level.MEDIUM, 120, Level.HARD, 80);

    private List<Pos> snake;
    private Set<Pos> snakePos;
    private Direction dir = Direction.UP;
    private Pos food;
    private int dimension = 16;

    @Override
    public StartGameStatus start(Level level) {
        reset();
        Pos pos = new Pos((int) Math.ceil(dimension * 0.75), dimension/2);
        snake = newArrayList();
        snake.add(pos);
        snake.add(new Pos(pos.getRow() + 1, pos.getCol()));
        snake.add(new Pos(pos.getRow() + 2, pos.getCol()));
        snakePos = newHashSet(snake);
        return new StartGameStatus(dimension, dimension, snake, timerSpeed.get(level));
    }

    @Override
    public TimerStatus onTimer() {
        GameStatus status = GameStatus.IN_PLAY;
        Pos head = snake.get(0);
        Pos newHead = null;
        switch (dir) {
            case UP:
                newHead = new Pos(head.getRow() - 1, head.getCol());
                break;
            case DOWN:
                newHead = new Pos(head.getRow() + 1, head.getCol());
                break;
            case LEFT:
                newHead = new Pos(head.getRow(), head.getCol() - 1);
                break;
            case RIGHT:
                newHead = new Pos(head.getRow(), head.getCol() + 1);
                break;
        }
        if (snakePos.contains(newHead)) {
            status = GameStatus.LOST;
            System.out.println("Hit tail");
        } else if (newHead.getRow() >= 0 && newHead.getRow() < dimension && newHead.getCol() >= 0 && newHead.getCol() < dimension) {
            snake.add(0, newHead);
            snakePos.add(newHead);
            if (!newHead.equals(food)) {
                Pos removed = snake.remove(snake.size() - 1);
                snakePos.remove(removed);
            } else {
                food = null;
            }
        } else {
            System.out.println("Hit wall");
            status = GameStatus.LOST;
        }
        if (food == null) {
            while (food == null) {
                Pos pos = new Pos(nextInt(0, dimension), nextInt(0, dimension));
                if (!snakePos.contains(pos)) {
                    food = pos;
                }
            }
            
        }
        if ((snake.size() * 1.0) / (dimension * dimension) > 0.5) {
            status = GameStatus.WON;
        }
        return new TimerStatus(snake, food, status);
    }

    @Override
    public void onDirectionChanged(Direction direction) {
        Direction oppositeDir = null;
        switch(this.dir) {
            case UP: oppositeDir = Direction.DOWN; break;
            case DOWN: oppositeDir = Direction.UP; break;
            case LEFT: oppositeDir = Direction.RIGHT; break;
            case RIGHT: oppositeDir = Direction.LEFT; break;
        }
        if (direction != oppositeDir) {
            this.dir = direction;
        }
    }

    private void reset() {
        snake = null;
        snakePos = null;
        food = null;
        dir = Direction.UP;
    }
}
