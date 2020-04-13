package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;
import chess.domain.player.User;

class KnightTest {

    private GamePiece gamePiece;

    @BeforeEach
    void setUp() {
        gamePiece = new Knight(PlayerColor.WHITE);
    }

    @ParameterizedTest
    @DisplayName("이동 경로 찾기")
    @MethodSource("createSourceToTarget")
    void findMovePath(Position target, List<Position> expected) {
        Position source = Position.from("d5");
        Map<Position, GamePiece> boardMap = new TreeMap<>(
                BoardFactory.createEmptyBoard(User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER).getBoard());
        boardMap.put(source, gamePiece);

        Board board = BoardFactory.of(boardMap, 0, User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER);

        assertThatCode(() -> {
            gamePiece.validateMoveTo(board, source, target);
        }).doesNotThrowAnyException();

    }

    static Stream<Arguments> createSourceToTarget() {
        return Stream.of(
                Arguments.of(Position.from("e7"), Collections.emptyList()),
                Arguments.of(Position.from("f6"), Collections.emptyList()),
                Arguments.of(Position.from("f4"), Collections.emptyList()),
                Arguments.of(Position.from("e3"), Collections.emptyList()),
                Arguments.of(Position.from("c3"), Collections.emptyList()),
                Arguments.of(Position.from("b4"), Collections.emptyList()),
                Arguments.of(Position.from("b6"), Collections.emptyList()),
                Arguments.of(Position.from("c7"), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @DisplayName("이동할 수 없는 source, target")
    @MethodSource("createInvalidTarget")
    void invalidMovementException(Position target) {
        Map<Position, GamePiece> boardMap = new TreeMap<>(
                BoardFactory.createEmptyBoard(User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER).getBoard());
        Position source = Position.from("d5");

        boardMap.put(source, gamePiece);

        Board board = BoardFactory.of(boardMap, 0, User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER);

        assertThatThrownBy(() -> {
            gamePiece.validateMoveTo(board, source, target);
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n이동할 수 없는 경로입니다.");
    }

    static Stream<Arguments> createInvalidTarget() {
        return Stream.of(
                Arguments.of(Position.from("b5")),
                Arguments.of(Position.from("d6")),
                Arguments.of(Position.from("c5")),
                Arguments.of(Position.from("d7")),
                Arguments.of(Position.from("g6")),
                Arguments.of(Position.from("e5")),
                Arguments.of(Position.from("e4")),
                Arguments.of(Position.from("d3"))
        );
    }
}