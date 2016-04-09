package ru.money.yandex.shalamov;

import java.util.ArrayList;
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
    int height, width;

    public NumericPuzzle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean checkCompatible(java.lang.Integer first, Direction firstDirection, java.lang.Integer second, Direction secondDirection) {
        return true; // todo method stub here
    }

    @Override
    public List<Integer> getElements() {
        List<Integer> elements = IntStream.rangeClosed(1, height*width).boxed().collect(Collectors.toList());
        Collections.shuffle(elements);
        return elements;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
