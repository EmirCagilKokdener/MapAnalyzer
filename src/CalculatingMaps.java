import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class CalculatingMaps {
    private List<String> logs = new ArrayList<>();

    /**
     * Reads the input from the file.
     * Creates the road objects from the input.
     *
     * @param input The input from reading the file.
     */
    public void readInput(String[] input){
        List<String> inputList = Arrays.asList(input);
        String[] destination = inputList.get(0).split("\t");
        String startingPoint = destination[0];
        String endingPoint = destination[1];
        List<String> roadInputs = inputList.subList(1, inputList.size());
        for (String line : roadInputs){
            String[] parts = line.split("\t");
            String pointA = parts[0];
            String pointB = parts[1];
            int distance = Integer.parseInt(parts[2]);
            int id = Integer.parseInt(parts[3]);
            Road road = new Road(id, pointA, pointB, distance);// Creates roads.
        }
        outputGenerator(startingPoint, endingPoint);

    }

    /**
     * Analyzer between the original map barely connected map.
     *
     * @param fastestRoute The fastest route object to calculate the fastest route in original map and
     *                    find usage of used material in original map .
     * @param barelyConnectedMap The barely connected map object to find used material in this map and
     *                           get fastest how long is the fastest route in this map.
     */
    public void analyzer(FastestRoute fastestRoute, BarelyConnectedMap barelyConnectedMap){
        double usedMaterialRatio = barelyConnectedMap.usedMaterial * 1.00 / fastestRoute.usedMaterial;
        double fastestRouteRatio = barelyConnectedMap.finalDistanceFromStart * 1.00 / fastestRoute.finalDistanceFromStart;
        logs.add("Analysis:\n");
        logs.add(String.format("Ratio of Construction Material Usage Between Barely Connected and Original Map: %.2f\n",usedMaterialRatio));
        logs.add(String.format("Ratio of Fastest Route Between Barely Connected and Original Map: %.2f",fastestRouteRatio));
    }

    /**
     * Generates the outputs of the processes of finding fastest route in original map and barely connected map.
     * Starts analyzer method.
     *
     * @param startingPoint Starting point of the destination.
     * @param endingPoint Ending point of the destination.
     */
    public void outputGenerator(String startingPoint, String endingPoint){
        FastestRoute fastestRoute = new FastestRoute(startingPoint, endingPoint,Road.roads);
        fastestRoute.initializeStartingPoint();
        logs.add(String.format("Fastest Route from %s to %s (%d KM):\n",startingPoint, endingPoint, fastestRoute.finalDistanceFromStart));
        for (int i = 0; i <= fastestRoute.fullPath.size() - 1; i++) {
            logs.add(fastestRoute.fullPath.get(i)+"\n");
        }
        BarelyConnectedMap barelyConnectedMap = new BarelyConnectedMap(startingPoint, endingPoint);
        barelyConnectedMap.initializeStartingPoints(logs);
        analyzer(fastestRoute, barelyConnectedMap);

    }
    /**
     * Getter for list logs.
     *
     * @return The list of logs.
     */
    public List<String> getLogs() {
        return logs;
    }
}
