package ru.money.yandex.shalamov;

import java.util.List;

/**
 * This interface represents a puzzle, which elements have some type T.
 * T might be and integer, and image, a matrix, etc.
 * <p>
 * Created by viacheslav on 09.04.2016.
 */
public interface Puzzle<T> {

    /**
     * This method check compatibility of two puzzle elements.
     * As example,  (A, RIGHT, B LEFT) are compatibl, if "AB" is allowed in this puzzle.
     *
     * @param first           first puzzle element
     * @param firstDirection  direction of first puzzle element
     * @param second          second puzzle element
     * @param secondDirection direction of second puzzle element
     * @return true, if this two puzzle elements are compatible on two specified sides.
     */
    boolean checkCompatible(Element<T> first, Direction firstDirection,
                            Element<T> second, Direction secondDirection);


    /**
     * This method provides all puzzle elements in any order as a list.
     *
     * @return all puzzle elements.
     */
    List<Element<T>> getElements();

    /**
     * Checks, is the puzzle solved or not.
     * @return true, if solved.
     */
    boolean isSolved();

    /**
     * Provides the solution of this puzzle, if solved.
     * @return list of elements in correct order and with correct rotations,
     * if solved. Null - otherwise.
     */
    List<Element<T>> getSolution();

    int getHeight();

    int getWidth();
}
