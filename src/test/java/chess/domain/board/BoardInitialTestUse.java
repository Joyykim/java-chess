package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.Map;

public class BoardInitialTestUse implements BoardInitialization {

    private final Map<BoardSquare, Piece> chessBoard;

    public BoardInitialTestUse(Map<BoardSquare, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public Map<BoardSquare, Piece> getInitialize() {
        return chessBoard;
    }
}