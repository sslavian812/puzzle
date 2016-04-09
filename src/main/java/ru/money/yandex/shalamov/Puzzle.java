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
     * This method checks, if two elements are compatible on some sides.
     * It checks any possible directions and returns true, if this elements
     * are compatible in at least one of them.
     * @param first first element
     * @param second second element
     * @return true, if two elements are compatible is at least one possible combination.
     */
    boolean checkSomeDirectionCompatibility(Element<T> first, Element<T> second);

    /**
     * This method provides all puzzle elements in any order as a list.
     *
     * @return all puzzle elements.
     */
    List<Element<T>> getElements();

    /**
     * Checks, is the puzzle solved or not.
     *
     * @return true, if solved.
     */
    boolean isSolved();

    /**
     * Provides the solution of this puzzle, if solved.
     *
     * @return list of elements in correct order and with correct rotations,
     * if solved. Null - otherwise.
     */
    List<Element<T>> getSolution();

    void provideSolution(List<Element<T>> elements);

    int getHeight();

    int getWidth();
}
