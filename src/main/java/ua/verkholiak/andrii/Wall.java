package main.java.ua.verkholiak.andrii;

import java.util.ArrayList;
import java.util.List;

class Wall {

    private List<Segment> walls;

    Wall(int fieldSize) {
        this.walls = new ArrayList<>();
        initWalls(fieldSize);
    }

    void initWalls(int fieldSize) {
        createSideX(fieldSize, 0);
        createSideX(fieldSize, fieldSize);
        createSideY(fieldSize, 0);
        createSideY(fieldSize, fieldSize);
    }

    List<Segment> getWalls() {
        return walls;
    }

    private void createSideY(int fieldSize, int x) {
        for (int i = 0; i <= fieldSize; i += Segment.SEGMENT_SIZE) {
            walls.add(new Segment(x, i));
        }
    }

    private void createSideX(int fieldSize, int y) {
        for (int i = 0; i <= fieldSize; i += Segment.SEGMENT_SIZE) {
            walls.add(new Segment(i, y));
        }
    }
}
