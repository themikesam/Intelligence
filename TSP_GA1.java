import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class TSP_GA1 {
    public static void main(String[] args) throws IOException {
        // Create and add our cities
        String str;
        Scanner input = new Scanner(System.in);
        // Open this file.
        BufferedReader reader = new BufferedReader(new FileReader(
        ("test1.txt")));
        // Read lines from file.
        City[] city = new City[50];
        int i = 0;
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            } else if(line.contains("\n") || line.contains("\r\n")) {
                break;
            }
            String[] parts = line.split(",");
            city[i] = new City(line);
            TourManager.addCity(city[i]);
            i++;
            
        }
        reader.close();
        System.out.println("(Old/Pregnant/Children/Normal)");
        System.out.println("Please Enter Ur Identity: ");
        str = input.next();
        int[] Old = {2,3,4,5,7,8,9,13,14,15,17,23,25,28,30,34}; 
        int[] Pregnant = {2,3,4,5,7,8,9,13,14,15,17,20,21,23,25,28,30,31,33,34}; 
        int[] Children = {5,7,9,10,12,13,14,15,23,30}; 
        if (str.equals("Old")) {
            for(int k=0;k<Old.length;k++){
                TourManager.removeCity(city[Old[k]-1]);
            }
        } 
        else if (str.equals("Pregnant")) {
            for(int k=0;k<Old.length;k++){
                TourManager.removeCity(city[Pregnant[k]-1]);
            }
        } else if (str.equals("Children")) {
            for(int k=0;k<Old.length;k++){
                TourManager.removeCity(city[Children[k]-1]);
            }
        }
        
        // Initialize population
        Population pop = new Population(50, true);
        System.out.println("Initial:");
        System.out.println("Distance: " + pop.getFittest().getDistance());
        System.out.println("Path: ");
        System.out.println(pop.getFittest());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int j = 0; j < 100; j++) {
            pop = GA.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("\n\nEvolution Finished\n\n");
        System.out.println("Final");
        System.out.println("Distance: " + pop.getFittest().getDistance());
        System.out.println("Path:");
        System.out.println(pop.getFittest());
    }
}

