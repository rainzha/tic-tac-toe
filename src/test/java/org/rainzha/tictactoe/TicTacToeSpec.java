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
}
