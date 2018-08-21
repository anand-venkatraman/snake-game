package com.pulsepoint.games.snake.ui;

import com.pulsepoint.games.snake.dto.*;
import com.pulsepoint.games.snake.logic.SnakeGame;
import com.pulsepoint.games.snake.logic.SnakeGameImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

/**
 * UI controller for the snake game.
 * 
 * @author avenkatraman
 */
public class SnakeController {

    public Label status;
    public ComboBox level;
    public GridPane mainGrid;
    public Button start;
    private Timer timer;
    private SnakeGame game = new SnakeGameImpl();
    private Map<Pos, Label> labels = new HashMap<>();
    private double size = 50d;

    @FXML
    public void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList();
        level.setItems(items);
        Arrays.stream(Level.values()).forEach(value -> items.add(value.toString()));
        level.getSelectionModel().select("EASY");
    }

    @FXML
    protected void btnClick(ActionEvent event) {
        reset();
        level.setDisable(true);
        start.setDisable(true);
        Level levelChosen = Level.valueOf(level.getSelectionModel().getSelectedItem().toString());
        StartGameStatus startStatus = game.start(levelChosen);
        GridPane snakeGrid = setupGrid();
        mainGrid.add(snakeGrid, 0, 1, 3, 1);
        snakeGrid.requestFocus();
        for (int row = 0; row < startStatus.getRows(); row++) {
            for (int col = 0; col < startStatus.getColumns(); col++) {
                Label label = new Label();
                Pos position = new Pos(row, col);
                if (startStatus.getSnakePositions().contains(position)) {
                    label.getStyleClass().add("snake");
                }
                label.setPrefHeight(size);
                label.setPrefWidth(size);
                snakeGrid.add(label, col, row);
                labels.put(position, label);
            }
        }
        snakeGrid.setPrefWidth(startStatus.getRows() * size);
        snakeGrid.setPrefHeight(startStatus.getColumns() * size);

        timer = new Timer();
        Collection<Pos> snakePos = newArrayList(startStatus.getSnakePositions());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerStatus timerStatus = game.onTimer();
                if (timerStatus.getStatus() == GameStatus.LOST || timerStatus.getStatus() == GameStatus.WON) {
                    gameOver(timerStatus.getStatus());
                } else {
                    snakePos.forEach(pos -> labels.get(pos).getStyleClass().clear());
                    timerStatus.getSnakePositions().forEach(pos -> labels.get(pos).getStyleClass().add("snake"));
                    labels.get(timerStatus.getFoodPosition()).getStyleClass().add("food");
                    snakePos.clear();
                    snakePos.addAll(timerStatus.getSnakePositions());
                }
            }
        }, 0, startStatus.getTimerDelay());
    }


    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        Direction dir = null;
        switch (keyEvent.getCode()) {
            case LEFT:
                dir = Direction.LEFT;
                break;
            case RIGHT:
                dir = Direction.RIGHT;
                break;
            case UP:
                dir = Direction.UP;
                break;
            case DOWN:
                dir = Direction.DOWN;
                break;
        }
        if (dir != null) {
            game.onDirectionChanged(dir);
        }
    }

    private GridPane setupGrid() {
        GridPane snakeGrid = new GridPane();
        snakeGrid.setAlignment(javafx.geometry.Pos.CENTER);
        snakeGrid.setId("snakeGrid");
        return snakeGrid;
    }

    private void gameOver(GameStatus gameStatus) {
        timer.cancel();
        Platform.runLater(() -> {
            status.setText("GAME " + gameStatus);
        });
        level.setDisable(false);
        start.setDisable(false);
    }

    private void reset() {
        status.setText("");
        if (timer != null) {
            timer.cancel();
        }
        labels.clear();
        Optional<Node> snakeGrid = mainGrid.getChildren().stream().filter(child -> child instanceof GridPane).findAny();
        snakeGrid.ifPresent(node -> mainGrid.getChildren().remove(node));
    }
}
