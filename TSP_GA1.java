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
        int[] Old = {1,2,3}; 
        int[] Pregnant = {4, 5, 6}; 
        int[] Children = {7,8,9}; 
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
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int j = 0; j < 100; j++) {
            pop = GA.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.print(pop.getFittest());
    }
}
