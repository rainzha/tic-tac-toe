package org.rainzha.tictactoe;


import org.junit.Before;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class TicTacToeSpec {
    private TicTacToe ticTacToe;

    @Before
    public final void before() {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXOutsideBoardThenRuntimeException() {
        catchException(ticTacToe).play(5, 2);

        assertTrue(caughtException() instanceof RuntimeException);
        assertThat(caughtException().getMessage()).isEqualTo("X is outside board");
    }

    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        catchException(ticTacToe).play(2, 5);

        assertTrue(caughtException() instanceof RuntimeException);
        assertThat(caughtException().getMessage()).isEqualTo("X is outside board");
    }

    @Test
    public void whenOccupiedThenRuntimeException() {
        ticTacToe.play(2, 1);
        catchException(ticTacToe).play(2, 1);

        assertTrue(caughtException() instanceof RuntimeException);
        assertThat(caughtException().getMessage()).isEqualTo("Box is occupied");
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {
        assertThat(ticTacToe.nextPlayer()).isEqualTo('X');
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThenO() {
        ticTacToe.play(1, 1);
        assertThat(ticTacToe.nextPlayer()).isEqualTo('O');
    }

    @Test
    public void whenPlayThenNoWinner() {
        String actual = ticTacToe.play(1, 1);
        assertThat(actual).isEqualTo("No winner");
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 1); // X
        ticTacToe.play(2, 2); // O
        String actual = ticTacToe.play(3, 1); // X

        assertThat(actual).isEqualTo("X is the winner");
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() {
        ticTacToe.play(2, 1); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(3, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        String actual = ticTacToe.play(1, 3); // O

        assertThat(actual).isEqualTo("O is the winner");
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() {
        ticTacToe.play(1, 3); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(1, 2); // O
        String actual = ticTacToe.play(3, 1); // O

        assertThat(actual).isEqualTo("X is the winner");
    }

    @Test
    public void whenAllBoxesAreFilledThenDraw() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 2);

        assertThat(actual).isEqualTo("The result is draw");
    }
}
