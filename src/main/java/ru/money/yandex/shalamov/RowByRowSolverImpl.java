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

        // add left top corner element
        solution.add(lastElement);


        // add elements one-by-one
        for (Element<T> nextCandidate : elements) {
            if (checkNextCompatible(lastElement, nextCandidate, solution.size(), puzzle)) {
                solution.add(nextCandidate);
                lastElement = nextCandidate;
            }
        }

        puzzle.provideSolution(solution);

        return puzzle;
    }

    private <T> boolean checkNextCompatible(Element<T> current,
                                            Element<T> next,
                                            int builtSize, Puzzle<T> puzzle) {
        if (builtSize % puzzle.getWidth() == 0) {
            return puzzle.checkCompatible(current, Direction.DOWN, next, Direction.UP);
        } else {
            return puzzle.checkCompatible(current, Direction.RIGHT, next, Direction.LEFT);
        }
    }

    private <T> boolean checkIsLeftTopCornerElement(Element<T> candidate, Element<T> first, Element<T> second, Puzzle<T> puzzle) {
        return (puzzle.checkCompatible(candidate, Direction.RIGHT, first, Direction.LEFT)
                && puzzle.checkCompatible(candidate, Direction.DOWN, second, Direction.UP))
                || (puzzle.checkCompatible(candidate, Direction.RIGHT, second, Direction.LEFT)
                && puzzle.checkCompatible(candidate, Direction.DOWN, first, Direction.UP));
    }


}
