package ru.money.yandex.shalamov;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Created by viacheslav on 09.04.2016.
 */
public abstract class AbstractPuzzle<T> implements Puzzle<T> {

    protected int height, width;
    protected List<Element<T>> solution = null;
    protected boolean isSolved = false;

    public AbstractPuzzle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public abstract boolean checkCompatible(Element<T> first, Direction firstDirection,
                                   Element<T> second, Direction secondDirection);

    @Override
    public boolean checkSomeDirectionCompatibility(Element<T> first, Element<T> second) {
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 4; ++j)
                if (checkCompatible(first, Direction.values()[i], second, Direction.values()[j]))
                    return true;
        return false;
    }

    protected boolean checkSolution(List<Element<T>> elements) {

        if (elements.size() != getWidth() * getHeight())
            return false;

        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        boolean[][] checked = new boolean[getHeight()][getWidth()];
        queue.add(new Pair<>(0, 0));

        Element<T> current;

        // BFS-checking of elements compatibility
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> p = queue.poll();
            int i = p.getKey(), j = p.getValue();

            current = elements.get(i * getWidth() + j);
            if (j + 1 < getWidth()) {
                if (!checkCompatible(current, Direction.RIGHT, elements.get(i * getWidth() + j + 1), Direction.LEFT)) {
                    return false;
                }
                if (!checked[i][j + 1]) {
                    queue.add(new Pair<>(i, j + 1));
                }
            }
            if (i + 1 < getHeight()) {
                if (!checkCompatible(current, Direction.DOWN, elements.get((i + 1) * getWidth() + j), Direction.UP)) {
                    return false;
                }
                if (!checked[i + 1][j]) {
                    queue.add(new Pair<>(i + 1, j));
                }
            }
            checked[i][j] = true;
        }
        return true;
    }

    @Override
    public boolean offerSolution(List<Element<T>> elements) {
        if (checkSolution(elements)) {
            solution = elements;
            return isSolved = true;
        }
        return false;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean isSolved() {
        return isSolved;
    }

    @Override
    public List<Element<T>> getSolution() {
        if (!isSolved)
            return null;
        return solution;
    }
}
