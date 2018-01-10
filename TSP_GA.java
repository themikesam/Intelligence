import java.util.Scanner;

public class TSP_GA {
    public static void main(String[] args) {
        // Create and add our cities
        String str;
        Scanner input = new Scanner(System.in);

        City city = new City("C1", 60, 200);
        TourManager.addCity(city);
        City city2 = new City("C2", 180, 200);
        TourManager.addCity(city2);
        City city3 = new City("C3", 80, 180);
        TourManager.addCity(city3);
        City city4 = new City("C4", 140, 180);
        TourManager.addCity(city4);
        City city5 = new City("C5", 20, 160);
        TourManager.addCity(city5);
        City city6 = new City("C6", 100, 160);
        TourManager.addCity(city6);
        City city7 = new City("C7", 200, 160);
        TourManager.addCity(city7);
        City city8 = new City("C8", 140, 140);
        TourManager.addCity(city8);
        City city9 = new City("C9", 40, 120);
        TourManager.addCity(city9);
        City city10 = new City("C10", 100, 120);
        TourManager.addCity(city10);
        City city11 = new City("C11", 180, 100);
        TourManager.addCity(city11);
        City city12 = new City("C12", 60, 80);
        TourManager.addCity(city12);
        City city13 = new City("C13", 120, 80);
        TourManager.addCity(city13);
        City city14 = new City("C14", 180, 60);
        TourManager.addCity(city14);
        City city15 = new City("C15", 20, 40);
        TourManager.addCity(city15);
        City city16 = new City("C16", 100, 40);
        TourManager.addCity(city16);
        City city17 = new City("C17", 200, 40);
        TourManager.addCity(city17);
        City city18 = new City("C18", 20, 20);
        TourManager.addCity(city18);
        City city19 = new City("C19", 60, 20);
        TourManager.addCity(city19);
        City city20 = new City("C20", 160, 20);
        TourManager.addCity(city20);

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
