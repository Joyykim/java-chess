package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMethodCallException;

public abstract class GamePieceExceptPawn extends GamePiece {

    public GamePieceExceptPawn(Side side, String initial) {
        super(side, initial);
    }

    @Override
    public final boolean isPawn() {
        return false;
    }

    @Override
    public final void moved() {
    }

    @Override
    public final boolean diagonal(Position from, Position to) {
        throw new InvalidMethodCallException();
    }

    @Override
    public final boolean forward(Position from, Position to) {
        throw new InvalidMethodCallException();
    }
}