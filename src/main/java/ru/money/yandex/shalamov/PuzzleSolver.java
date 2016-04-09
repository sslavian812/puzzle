package ru.money.yandex.shalamov;

import java.util.List;

/**
 * Created by viacheslav on 09.04.2016.
 */
public interface PuzzleSolver {

    /**
     * This method solves a puzzle.
     * @param puzzle
     * @param <T>
     * @return
     */
    <T> List<T> solvePuzzle(Puzzle<T> puzzle);
}
