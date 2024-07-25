import java.util.Locale;

public class MapAnalyzer {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        // Reads input.
        String[] inputFile = FileInput.readFile(args[0], true, true);
        // Finds fastest route barely connected map and analysis.
        CalculatingMaps calculatingMaps = new CalculatingMaps();
        calculatingMaps.readInput(inputFile);
        // Writes logs to the output.
        FileOutput.writeToFile(args[1], calculatingMaps.getLogs(), false, false);
    }
}
