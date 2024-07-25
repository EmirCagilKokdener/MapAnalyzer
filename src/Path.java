public class Path {
    int id;
    String pointA;
    String pointB;
    int distanceFromStart;
    Road road;

    /**
     * Constructs a path object with its parameters.
     * @param id ID of the path.
     * @param pointA First point of the path.
     * @param pointB Other point of the path.
     * @param distanceFromStart The distance from the start to that path.
     * @param road The road object that has the distance of that path.
     */
    public Path(int id, String pointA, String pointB, int distanceFromStart, Road road) {
        this.id = id;
        this.pointA = pointA;
        this.pointB = pointB;
        this.distanceFromStart = distanceFromStart;
        this.road = road;

    }

    /**
     * Changes the string representation of the path.
     *
     * @return The string representation of the path.
     */
    public String toString(){

        return String.format("%s\t%s\t%d\t%d",road.pointA,road.pointB,road.distance,id);
    }
}
