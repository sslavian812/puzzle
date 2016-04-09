package ru.money.yandex.shalamov;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class represents a numeric puzzle.
 * As example, 3x3 numeric puzzle is:
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * <p>
 * Created by viacheslav on 09.04.2016.
 */
public class NumericPuzzle implements Puzzle<Integer> {
    private int height, width;
    private List<Element<Integer>> solution = null;
    private boolean isSolved = false;

    public NumericPuzzle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean checkCompatible(Element<Integer> first, Direction firstDirection,
                                   Element<Integer> second, Direction secondDirection) {
        return checkCompatible(first, firstDirection.ordinal(), second, secondDirection.ordinal());
    }

    @Override
    public boolean checkSomeDirectionCompatibility(Element<Integer> first, Element<Integer> second) {
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 4; ++j)
                if (checkCompatible(first, i, second, j))
                    return true;
        return false;
    }

    private boolean checkCompatible(Element<Integer> first, int firstDirection,
                                    Element<Integer> second, int secondDirection) {
        return first != second; // todo method stub here
    }

    @Override
    public List<Element<Integer>> getElements() {
        List<Element<Integer>> elements = IntStream.rangeClosed(1, height * width)
                .boxed()
                .map(i -> new Element<Integer>(i))
                .collect(Collectors.toList());
        Collections.shuffle(elements);
        return elements;
    }

    @Override
    public boolean isSolved() {
        return false; // todo method stub here
    }

    @Override
    public List<Element<Integer>> getSolution() {
        if (!isSolved)
            return null;
        return solution;
    }

    @Override
    public void provideSolution(List<Element<Integer>> elements) {
        if(checkSolution(elements))
            solution = elements;
    }

    private boolean checkSolution(List<Element<Integer>> elements) {
        return true;   // todo method stub here.
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
