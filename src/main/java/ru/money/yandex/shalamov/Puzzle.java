package ru.money.yandex.shalamov;

import java.util.List;

/**
 * This interface represents a puzzle.
 *
 * Created by viacheslav on 09.04.2016.
 */
public interface Puzzle<T> {

    /**
     * This method check compatibility of two puzzle elements.
     * As example,  (A, RIGHT, B LEFT) are compatibl, if "AB" is allowed in this puzzle.
     *
     * @param first first puzzle element
     * @param firstDirection direction of first puzzle element
     * @param second second puzzle element
     * @param secondDirection direction of second puzzle element
     * @return true, if this two puzzle elements are compatible on two specified sides.
     */
    boolean checkCompatible(T first, Direction firstDirection,
                            T second, Direction secondDirection);


    /**
     * This method provides all puzzle elements in any order as a list.
     * @return all puzzle elements.
     */
    List<T> getElements();
}
