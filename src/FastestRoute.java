import java.util.*;

public class FastestRoute {
    String startingPoint;
    String endingPoint;
    List<Road> roads;
    List<Path> paths = new ArrayList<>();
    Map<String, Path> pathMap = new HashMap<>();
    int finalDistanceFromStart;
    List<Path> fullPath = new ArrayList<>();
    int usedMaterial;

    /**
     * Constructs  fastest route object with starting point ending point and roads  parameters.
     *
     * @param startingPoint Starting point of the destination.
     * @param endingPoint Ending point of the destination.
     * @param roads All roads in the map.
     */
    public FastestRoute(String startingPoint, String endingPoint, List<Road> roads){
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.roads = roads;
        findUsedMaterial();
    }

    /**
     * Finds used material in the original map.
     */
    public void findUsedMaterial(){
        for (Road road : roads){
             usedMaterial += road.distance;
        }
    }

    /**
     * Initialize the starting point to find the shortest map.
     */
    public void initializeStartingPoint(){
        for (Road road : roads){
            if (road.pointA.equals(startingPoint)){
                Path startingPath = new Path(road.id,road.pointA,road.pointB,road.distance,road);
                pathMap.put(road.pointA, startingPath);
                paths.add(startingPath);// Adds roads that is connected the starting point.
            } else if (road.pointB.equals(startingPoint)) {
                Path startingPath = new Path(road.id, road.pointB, road.pointA, road.distance,road);
                pathMap.put(road.pointB, startingPath);// Adds roads that is connected the starting point.
                paths.add(startingPath);
            }
        }
        paths.sort((p1, p2) -> {
            int distanceCompare = Integer.compare(p1.distanceFromStart, p2.distanceFromStart);// Compares the distances of the paths.
            if (distanceCompare != 0) {
                return distanceCompare;
            } else {
                return Integer.compare(p1.id, p2.id);// Compares id of the paths if distances are equal.
            }
        });
        findShortestRoute();
    }

    /**
     * Tries going to the ending point while adding the shortest paths from the points that we reach.
     * It doesn't visit the point again so there is no cycle.
     */
    public void findShortestRoute(){
        while (!pathMap.containsKey(endingPoint)){
            paths.sort((p1, p2) -> Integer.compare(p1.distanceFromStart, p2.distanceFromStart));
            for (Road road : roads){
                if (paths.get(0).pointB.equals(road.pointA) && !(pathMap.containsKey(road.pointA)) && !(pathMap.containsKey(road.pointB))){
                    Path newPath = new Path(road.id, road.pointA, road.pointB, (paths.get(0).distanceFromStart + road.distance), road);
                    paths.add(newPath);//Adds new path from the points.
                } else if (paths.get(0).pointB.equals(road.pointB) && (!pathMap.containsKey(road.pointA)) && (!pathMap.containsKey(road.pointB))) {
                    Path newPath = new Path(road.id, road.pointB, road.pointA,(paths.get(0).distanceFromStart + road.distance), road);
                    paths.add(newPath);//Adds new path from the points.
                }
            }
            if (!pathMap.containsKey(paths.get(0).pointB)){
                pathMap.put(paths.get(0).pointB, paths.get(0));// Adds in the map if the points aren't already visited.
            }
            paths.remove(0);// Removes the first element.

        }
        finalDistanceFromStart = pathMap.get(endingPoint).distanceFromStart;
        String currentPoint = endingPoint;
        // Getting the whole path from the start.
        while (!currentPoint.equals(startingPoint)) {
            Path currentPath = pathMap.get(currentPoint);
            fullPath.add(0, currentPath); // Add the current point to the beginning of the list
            currentPoint = currentPath.pointA; // Move to the previous point
        }
    }


}
