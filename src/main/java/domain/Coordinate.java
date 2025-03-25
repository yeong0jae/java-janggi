package domain;

import domain.piece.movement.Movement;
import java.util.List;

public record Coordinate(int row, int col) {

    public static final int MAX_ROW = 10;
    public static final int MAX_COL = 9;

    public boolean isOutOfBoundary() {
        if (this.row < 1 || this.row > MAX_ROW) {
            return true;
        }
        return this.col < 1 || this.col > MAX_COL;
    }

    public Coordinate move(int increaseRow, int increaseCol) {
        return new Coordinate(row + increaseRow, col + increaseCol);
    }

    public Coordinate move(Movement movement) {
        return move(movement.getDirection().row(), movement.getDirection().col());
    }

    public void addGungMovement(List<Movement> movements) {
        gungCenter(movements);
        gungDownRight(movements);
        gungDownLeft(movements);
        gungUpRight(movements);
        gungUpLeft(movements);
    }

    private void gungDownRight(List<Movement> movements) {
        if ((row == 1 && col == 4) || (row == 8 && col == 4)) {
            movements.add(Movement.DOWN_RIGHT);
        }
    }

    private void gungDownLeft(List<Movement> movements) {
        if ((row == 1 && col == 6) || (row == 8 && col == 6)) {
            movements.add(Movement.DOWN_LEFT);
        }
    }

    private void gungUpRight(List<Movement> movements) {
        if ((row == 3 && col == 4) || (row == 10 && col == 4)) {
            movements.add(Movement.UP_RIGHT);
        }
    }

    private void gungUpLeft(List<Movement> movements) {
        if ((row == 3 && col == 6) || (row == 10 && col == 6)) {
            movements.add(Movement.UP_LEFT);
        }
    }

    private void gungCenter(List<Movement> movements) {
        if ((row == 2 && col == 5) || (row == 9 && col == 5)) {
            movements.add(Movement.DOWN_RIGHT);
            movements.add(Movement.DOWN_LEFT);
            movements.add(Movement.UP_LEFT);
            movements.add(Movement.UP_RIGHT);
        }
    }

}
