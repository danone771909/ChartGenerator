package com.chart.generator;

/**
 * Created by Daniel on 2016-04-14.
 */
public class Key {
    private final int x;
    private final int y;

    public Key(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
        return (31*x + y);
    }
}
