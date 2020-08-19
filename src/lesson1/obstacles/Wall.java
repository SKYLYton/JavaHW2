package lesson1.obstacles;

import lesson1.elements.Action;

public class Wall implements Obstacles {
    private int distance;

    public Wall(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean competition(Action action) {
        return action.jump(distance);
    }
}
