import java.util.ArrayList;
import java.util.List;

class Road {
    int id;
    String pointA;
    String pointB;
    int distance;
    static List<Road> roads = new ArrayList<>();

    /**
     * Constructs a road object withs its parameters.
     *
     * @param id The ID of the road object.
     * @param pointA The first point of the road object.
     * @param pointB The other point of the road object.
     * @param distance The distance from pointA to pointB.
     */

    public Road(int id, String pointA, String pointB, int distance) {
        this.id = id;
        this.pointA = pointA;
        this.pointB = pointB;
        this.distance = distance;
        roads.add(this);// Adds all the created roads to a list.
    }
}