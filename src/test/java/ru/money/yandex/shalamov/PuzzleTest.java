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


    @Test
    public void testSomeDirectionCompatibility() {
        int h = 3, w = 3;
        Puzzle<Integer> puzzle = new NumericPuzzle(h, w);

        assertTrue(puzzle.checkSomeDirectionCompatibility(new Element<>(5), new Element<>(6)));
        assertFalse(puzzle.checkSomeDirectionCompatibility(new Element<>(4), new Element<>(6)));

        assertTrue(puzzle.checkSomeDirectionCompatibility(new Element<>(5), new Element<>(8)));
        assertFalse(puzzle.checkSomeDirectionCompatibility(new Element<>(8), new Element<>(6)));
    }

    @Test
    public void testSolver() {
        int h = 3, w = 3;
        Puzzle<Integer> puzzle = new NumericPuzzle(h, w);

        RowByRowSolverImpl solver = new RowByRowSolverImpl();
        assertTrue(solver.<Integer>solvePuzzle(puzzle).isSolved());
    }

}
