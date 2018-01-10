import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TSP_GA1 {
    public static void main(String[] args) throws IOException {
        
        // Open this file.
        BufferedReader reader = new BufferedReader(new FileReader(
        ("test1.txt")));
        // Read lines from file.
        int count = 1;
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            } else if(line.contains("\n") || line.contains("\r\n")) {
                break;
            }
            
            String[] parts = line.split(",");
            // City city = new City("C1", 60, 200);
            // TourManager.addCity(city);
            String tmp = "";
            for(int i = 0; i < parts.length; i++) { 
                if(i!=(parts.length-1)){
                    tmp = tmp + parts[i] + ",";
                } else {
                    tmp = tmp + parts[i];
                }
                // System.out.print(parts[i] + " "); 
            }
            
            // for (String part : parts) {
            //     tmp =parts;
            //     System.out.println(parts);
            // }
            System.out.println(tmp);
            // System.out.println();1
        }

        reader.close();
    }
}
import java.util.Scanner;

public class TSP_GA {
    public static void main(String[] args) {
        // Create and add our cities
        String str;
        Scanner input = new Scanner(System.in);
        
        City city = new City("C1", 60, 200);
        TourManager.addCity(city);

        System.out.println("(Old/Pregnant/Children/Normal)");
        System.out.println("Please Enter Ur Identity: ");
        str = input.next();
        if (str.equals("Old")) {
            TourManager.removeCity(city);
            TourManager.removeCity(city2);
            TourManager.removeCity(city3);
        } else if (str.equals("Pregnant")) {
            TourManager.removeCity(city4);
            TourManager.removeCity(city5);
            TourManager.removeCity(city6);
        } else if (str.equals("Children")) {
            TourManager.removeCity(city7);
            TourManager.removeCity(city8);
            TourManager.removeCity(city9);
        }
        
        // Initialize population
        Population pop = new Population(50, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
}
