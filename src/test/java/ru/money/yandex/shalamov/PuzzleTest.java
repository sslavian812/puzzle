package ru.money.yandex.shalamov;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by viacheslav on 09.04.2016.
 */
public class PuzzleTest {


    /**
     * Manual checks of compatibility function.
     */
    @Test
    public void testManualCompatibility() {
        Puzzle<Integer> puzzle = new NumericPuzzle(3, 3);

        assertTrue(puzzle.checkCompatible(new Element<>(1), Direction.RIGHT, new Element<>(2), Direction.LEFT));
        assertTrue(puzzle.checkCompatible(new Element<>(2), Direction.RIGHT, new Element<>(3), Direction.LEFT));
        assertFalse(puzzle.checkCompatible(new Element<>(3), Direction.RIGHT, new Element<>(4), Direction.LEFT));

        assertFalse(puzzle.checkCompatible(new Element<>(1), Direction.LEFT, new Element<>(2), Direction.LEFT));
        assertFalse(puzzle.checkCompatible(new Element<>(2), Direction.LEFT, new Element<>(3), Direction.LEFT));

        assertFalse(puzzle.checkCompatible(new Element<>(2), Direction.LEFT, new Element<>(3), Direction.RIGHT));
    }

    /**
     * test of full solution.
     */
    @Test
    public void testFullSolution() {
        int h = 3, w = 3;
        Puzzle<Integer> puzzle = new NumericPuzzle(h, w);

        List<Element<Integer>> manualSolution = IntStream.rangeClosed(1, h * w)
                .boxed()
                .map(i -> new Element<Integer>(i))
                .collect(Collectors.toList());

        assertTrue(puzzle.offerSolution(manualSolution));

        List<Element<Integer>> wrongSolution = IntStream.rangeClosed(1, h * w)
                .boxed()
                .map(i -> new Element<Integer>(i))
                .collect(Collectors.toList());
        Collections.reverse(wrongSolution);

        assertFalse(puzzle.offerSolution(wrongSolution));
    }

}
