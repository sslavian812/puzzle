package ru.money.yandex.shalamov;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by viacheslav on 09.04.2016.
 */
public class PuzzleTest {


    @Test
    public void testManualCompatibility() {
        Puzzle<Integer> puzzle = new NumericPuzzle(3,3);

        assertTrue(puzzle.checkCompatible(new Element<>(1),Direction.RIGHT, new Element<>(2), Direction.LEFT));
        assertTrue(puzzle.checkCompatible(new Element<>(2),Direction.RIGHT, new Element<>(3), Direction.LEFT));
        assertFalse(puzzle.checkCompatible(new Element<>(3), Direction.RIGHT, new Element<>(4), Direction.LEFT));

        assertFalse(puzzle.checkCompatible(new Element<>(1), Direction.LEFT, new Element<>(2), Direction.LEFT));
        assertFalse(puzzle.checkCompatible(new Element<>(2), Direction.LEFT, new Element<>(3), Direction.LEFT));

        assertFalse(puzzle.checkCompatible(new Element<>(2), Direction.LEFT, new Element<>(3), Direction.RIGHT));
    }

}
