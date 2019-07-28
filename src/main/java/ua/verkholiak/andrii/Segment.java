package main.java.ua.verkholiak.andrii;

import java.util.Objects;

class Segment {

    static final int SEGMENT_SIZE = 16;

    private int x;
    private int y;

    Segment(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return x == segment.x &&
                y == segment.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
