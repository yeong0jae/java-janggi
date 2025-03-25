package domain.piece;

import domain.Coordinate;
import domain.board.Board;
import domain.piece.movement.Movement;
import java.util.ArrayList;
import java.util.List;

public class Byeong extends Piece {

    private static final List<Movement> MOVEMENTS = new ArrayList<>(
            List.of(Movement.UP, Movement.DOWN, Movement.RIGHT, Movement.LEFT));

    public Byeong(Country country) {
        super(country, PieceType.BYEONG);
    }

    @Override
    public List<Coordinate> availableMovePositions(Coordinate from, Board board) {
        from.addGungMovement(MOVEMENTS);
        return MOVEMENTS.stream()
                .filter(this::selectUpOrDown)
                .map(from::move)
                .filter(to -> !to.isOutOfBoundary())
                .filter(to -> !board.isMyTeam(country, to))
                .toList();
    }

    private boolean selectUpOrDown(Movement movement) {
        if (country.isCho()) {
            return isHanDirection(movement);
        }
        return isChoDirection(movement);
    }

    private boolean isHanDirection(Movement movement) {
        return movement != Movement.DOWN && movement != Movement.DOWN_RIGHT && movement != Movement.DOWN_LEFT;
    }

    private boolean isChoDirection(Movement movement) {
        return movement != Movement.UP && movement != Movement.UP_RIGHT && movement != Movement.UP_LEFT;
    }
}
