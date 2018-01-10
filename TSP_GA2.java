import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TSP_GA2 {

    public JFrame mainMap;
    public Polygon poly;
    public TSP_GA2() {

        initComponents();

    }
static int xPoly[] = new int [50];
       static int yPoly[] = new int [50];
    public void initComponents() {

        mainMap = new JFrame();
        mainMap.setResizable(false);

        mainMap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        // {150, 250, 325, 375, 450, 275, 100};
        
        // {150, 100, 125, 225, 250, 375, 300};

        poly = new Polygon(xPoly, yPoly, xPoly.length);
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.drawPolygon(poly);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(800, 600);
            }
        };
        mainMap.add(p);
        mainMap.pack();
        mainMap.setVisible(true);

    }

    /**
     * @param args
     */


    public static void main(String[] args) throws IOException {
        // Create and add our cities
        String str;
        Scanner input = new Scanner(System.in);
        // Open this file.
        BufferedReader reader = new BufferedReader(new FileReader(
        ("test_result.txt")));
        // Read lines from file.
        City[] city = new City[34];
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
        System.out.println(city.length);
        for(int k=0;k<34;k++){
            xPoly[k] = city[k].getX()*7;
            yPoly[k] = city[k].getY()*7;
        }
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TSP_GA2();
            }
        });
        // JRisk poly = new JRisk();
        // poly.poly_x(x);
        // poly.poly_x(y);
        // poly = new Polygon(x, y, city.length);
    }
}

