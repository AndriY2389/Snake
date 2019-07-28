package main.java.ua.verkholiak.andrii;

import java.util.ArrayList;
import java.util.List;

class Snake {

    private static final int DEFAULT_LENGTH = 3;
    private List<Segment> segments;
    private Direction direction;

    Snake(Segment headSegment) {
        segments = new ArrayList<>();
        segments.add(0, headSegment);
        initSnake();
    }

    void move() {
        segments.add(0, getFutureSegment(direction));
    }

    boolean wasEatenByItself() {
        for (int i = 1; i < segments.size(); i++) {
            if (segments.get(i).equals(getHeadSegment())) {
                return true;
            }
        }

        return false;
    }

    boolean wasAppleEaten(Segment apple) {
        return apple.equals(getHeadSegment());
    }

    void removeLastSegment() {
        segments.remove(segments.size() - 1);
    }

    Direction getDirection() {
        return direction;
    }

    void setDirection(Direction direction) {
        if (checkDirection(direction)) {
            this.direction = direction;
        }
    }

    private boolean checkDirection(Direction direction) {
        return !segments.contains(getFutureSegment(direction));
    }

    private Segment getFutureSegment(Direction direction) {
        switch (direction) {
            case UP:
                return new Segment(segments.get(0).getX(), (segments.get(0).getY() - Segment.SEGMENT_SIZE));

            case DOWN:
                return new Segment(segments.get(0).getX(), (segments.get(0).getY() + Segment.SEGMENT_SIZE));

            case LEFT:
                return new Segment((segments.get(0).getX() - Segment.SEGMENT_SIZE), segments.get(0).getY());
        }

        return new Segment((segments.get(0).getX() + Segment.SEGMENT_SIZE), segments.get(0).getY());
    }

    Segment getHeadSegment() {
        return segments.get(0);
    }

    private void initSnake() {
        addDefaultSegments();
        setDefaultDirection();
    }

    private void setDefaultDirection() {
        direction = Direction.RIGHT;
    }

    private void addDefaultSegments() {
        for (int i = 1; i <= DEFAULT_LENGTH; i++) {
            segments.add(new Segment((segments.get(i - 1).getX() - Segment.SEGMENT_SIZE), segments.get(i - 1).getY()));
        }
    }

    List<Segment> getSegments() {
        return segments;
    }
}
