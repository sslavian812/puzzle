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
public class NumericPuzzle extends AbstractPuzzle<Integer> implements Puzzle<Integer> {
    protected List<Element<Integer>> elements;

    public NumericPuzzle(int height, int width) {
        super(height, width);
        elements = IntStream.rangeClosed(1, getHeight() * getWidth())
                .boxed()
                .map(i -> new Element<Integer>(i))
                .collect(Collectors.toList());
        Collections.shuffle(elements);
    }

    public NumericPuzzle(int height, int width, List<Element<Integer>> elements) {
        super(height, width);
        this.elements = elements;
    }

    @Override
    public boolean checkCompatible(Element<Integer> first, Direction firstDirection,
                                   Element<Integer> second, Direction secondDirection) {
        return checkCompatible(first, firstDirection.ordinal(), second, secondDirection.ordinal());
    }


    private boolean checkBounds(int value, int direction) {
        if (direction == 0 && (value - 1) % width == 0)
            return false;
        if (direction == 1 && value - height <= 0)
            return false;
        if (direction == 2 && value % width == 0)
            return false;
        if (direction == 3 && value + height > width * height)
            return false;
        return true;
    }

    private boolean checkCompatible(Element<Integer> first, int firstDirection,
                                    Element<Integer> second, int secondDirection) {
        if (!checkBounds(first.getValue(), firstDirection)
                || !checkBounds(second.getValue(), secondDirection))
            return false;

        int t = -1;
        switch (firstDirection) {
            case 0:
                t=-1;
                break;
            case 1:
                t = - width;
                break;
            case 2:
                t = 1;
                break;
            case 3:
                t = width;
        }

        switch (secondDirection) {
            case 0:
                return first.getValue() + t == second.getValue() && first.getValue() == second.getValue() - 1;
            case 1:
                return first.getValue() + t == second.getValue() && first.getValue() == second.getValue() - width;
            case 2:
                return first.getValue() + t == second.getValue() && first.getValue() == second.getValue() + 1;
            case 3:
                return first.getValue() + t == second.getValue() && first.getValue() == second.getValue() + width;
        }
        
        return false;
    }

    @Override
    public List<Element<Integer>> getElements() {
        return elements;
    }

}
