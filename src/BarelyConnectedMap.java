import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BarelyConnectedMap {
    String startingPoint;
    String endingPoint;
    int usedMaterial;
    int finalDistanceFromStart;
    List<Road> roads = Road.roads;
    List<String> points = new ArrayList<>();
    List<Road> buildingRoads = new ArrayList<>();
    List<Road> finalRoads = new ArrayList<>();
    List<String> visitedList = new ArrayList<>();

    /**
     * Constructs the BarelyConnectedMap with starting and ending points.
     *
     * @param startingPoint The starting point for the fastest route.
     * @param endingPoint   The ending point for the fastest route.
     */
    public  BarelyConnectedMap(String startingPoint, String endingPoint){
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

    /**
     * Initializes the points and roads.
     *
     * @param logs List of the outputs of the process.
     */
    public void initializeStartingPoints(List<String> logs){
        for (Road road : roads) {
            if (!points.contains(road.pointA)) {
                points.add(road.pointA);
            }
            if (!points.contains(road.pointB)) {
                points.add(road.pointB);
            }
        }
        Collections.sort(points);
        visitedList.add(points.get(0));
        for (Road road : roads){
            if (road.pointA.equals(points.get(0))){
                buildingRoads.add(road);
            } else if (road.pointB.equals(points.get(0))) {
                buildingRoads.add(road);
            }
        }
        buildRoads(logs);// Starts buildRoads method.
    }

    /**
     * Chooses the smallest roads and build them till the every point is visited in the map.
     *
     * @param logs List of the outputs of the process.
     */
    public void buildRoads(List<String> logs){
        while (finalRoads.size() != points.size() -1) {
            buildingRoads.sort((r1, r2) -> {
                int distanceCompare = Integer.compare(r1.distance, r2.distance);
                if (distanceCompare != 0) {
                    return distanceCompare;
                } else {
                    return Integer.compare(r1.id, r2.id);
                }
            });
            Road currentRoad = buildingRoads.get(0);
            // Adds roads if it's not visited.
            if (!visitedList.contains(currentRoad.pointA)){
                String newPoint = currentRoad.pointA;
                finalRoads.add(currentRoad);
                visitedList.add(newPoint);
                for (Road road : roads) {
                    if (road.pointA.equals(newPoint) && !visitedList.contains(road.pointB)) {
                        buildingRoads.add(road);
                    } else if (road.pointB.equals(newPoint) && !visitedList.contains(road.pointA)) {
                        buildingRoads.add(road);
                    }
                }
            } else if (!visitedList.contains(currentRoad.pointB)) {
                String newPoint = currentRoad.pointB;
                finalRoads.add(currentRoad);
                visitedList.add( newPoint);
                for (Road road : roads) {
                    if (road.pointA.equals(newPoint) && !visitedList.contains(road.pointB)) {
                        buildingRoads.add(road);
                    } else if (road.pointB.equals(newPoint) && !visitedList.contains(road.pointA)) {
                        buildingRoads.add(road);
                    }
                }
            }
            buildingRoads.remove(0);
        }
        finalRoads.sort((r1, r2) -> {// Checks the distances of the roads chooses the smallest one.
            int distanceCompare = Integer.compare(r1.distance, r2.distance);
            if (distanceCompare != 0) {
                return distanceCompare;
            } else {
                return Integer.compare(r1.id, r2.id);// If their distances are equal checks the id's of the roads.
            }
        });
        logs.add("Roads of Barely Connected Map is:\n");
        for (Road road : finalRoads) {// Shows the roads in Barely Connected map.
            usedMaterial += road.distance;
            logs.add(String.format("%s\t%s\t%d\t%d\n", road.pointA, road.pointB, road.distance, road.id));
        }
        FastestRoute fastestRoute = new FastestRoute(startingPoint,endingPoint, finalRoads);
        fastestRoute.initializeStartingPoint();
        finalDistanceFromStart = fastestRoute.finalDistanceFromStart;// The total km of the shortest path in barely connected map.
        logs.add(String.format("Fastest Route from %s to %s on Barely Connected Map (%d KM):\n",startingPoint, endingPoint, fastestRoute.finalDistanceFromStart));
        for (int i = 0; i <= fastestRoute.fullPath.size() - 1; i++) {
            logs.add(fastestRoute.fullPath.get(i)+"\n");
        }
    }

}
