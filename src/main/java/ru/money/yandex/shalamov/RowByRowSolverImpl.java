package ru.money.yandex.shalamov;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viacheslav on 09.04.2016.
 */
public class RowByRowSolverImpl implements PuzzleSolver {
    @Override
    public <T> Puzzle<T> solvePuzzle(Puzzle<T> puzzle) {
        List<Element<T>> elements = puzzle.getElements();
        List<Element<T>> solution = new ArrayList<>(elements.size());


        // find corner element:
        Element<T> lastElement = null;

        for (Element<T> candidate : elements) {
            List<Element<T>> compatible = new ArrayList<>(4);


            for (Element<T> other : elements) {

                if (puzzle.checkSomeDirectionCompatibility(candidate, other)) {
                    compatible.add(other);
                }
            }
            if (compatible.size() == 2) {
                // here we've found a corner-element. let it be a top-left corner.
                if (checkIsLeftTopCornerElement(candidate, compatible.get(0), compatible.get(1), puzzle)) {
                    lastElement = candidate;
                    break;
                }
            }
        }

        // find the first column
        List<Element<T>> firstInEachLine = new ArrayList<>(puzzle.getHeight());
        firstInEachLine.add(lastElement);
        while (firstInEachLine.size() < puzzle.getHeight()) {
            for (Element<T> nextCandidate : elements) {
                if (puzzle.checkCompatible(lastElement, Direction.DOWN, nextCandidate, Direction.UP)) {
                    firstInEachLine.add(nextCandidate);
                    lastElement = nextCandidate;
                }
            }
        }


        // find row by row (possible multithreading here)
        int currentRow = 0;
        for (Element<T> columnStarter : firstInEachLine) {
            lastElement = columnStarter;
            ++currentRow;
            solution.add(lastElement);
            while (solution.size() < puzzle.getWidth() * currentRow) {
                for (Element<T> nextCandidate : elements) {
                    if (puzzle.checkCompatible(lastElement, Direction.RIGHT, nextCandidate, Direction.LEFT)) {
                        solution.add(nextCandidate);
                        lastElement = nextCandidate;
                    }
                }
            }
        }

        puzzle.offerSolution(solution);
        return puzzle;
    }


    private <T> boolean checkIsLeftTopCornerElement(Element<T> candidate, Element<T> first, Element<T> second, Puzzle<T> puzzle) {
        return (puzzle.checkCompatible(candidate, Direction.RIGHT, first, Direction.LEFT)
                && puzzle.checkCompatible(candidate, Direction.DOWN, second, Direction.UP))
                || (puzzle.checkCompatible(candidate, Direction.RIGHT, second, Direction.LEFT)
                && puzzle.checkCompatible(candidate, Direction.DOWN, first, Direction.UP));
    }
}
