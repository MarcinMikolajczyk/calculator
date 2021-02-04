package com.marcin.calculator.data;

/**
 * Object <code>Pair</code> represent pair of two generic types
 * @author Marcin Mikołajczyk
 * @version 1.0
 */

public class Pair<L, R> {
    private R right;
    private L left;

    public Pair(L left, R right) {
        this.right = right;
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public void setRight(R right) {
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public void setLeft(L left) {
        this.left = left;
    }
}