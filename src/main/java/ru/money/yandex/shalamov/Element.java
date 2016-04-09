package ru.money.yandex.shalamov;

/**
 * Created by viacheslav on 09.04.2016.
 */
public class Element<T> {
    private T value;
    private int rotation = 0;

    public Element(T value) {
        this.value = value;
    }

    public void rotateCW(int k) {
        rotation = (rotation + k) % 4;
    }

    public T getValue() {
        return value;
    }
}
