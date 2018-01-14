import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

public class TSP_GA3 extends JFrame {
    public JFrame mainMap;
    public Polygon poly;
    public TSP_GA3() {
        initComponents();
    }
    static int xPoly[] = new int [50];
    static int yPoly[] = new int [50];
    public Image image;
    public void initComponents() {
        mainMap = new JFrame();
        mainMap.setResizable(false);
        mainMap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        poly = new Polygon(xPoly, yPoly, TourManager.getSize());
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                try {
                    image = ImageIO.read(new File("lulu.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 800, 600, null);
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
        BufferedReader reader = new BufferedReader(new FileReader(("test_result.txt")));
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
        System.out.println("(Old/Pregnant/Children/Normal)");
        System.out.println("Please Enter Ur Identity: ");
        str = input.next();
        int[] Old = {2,3,4,5,7,8,9,13,14,15,17,23,25,28,30,34};
        int[] Pregnant = {2,3,4,5,7,8,9,13,14,15,17,20,21,23,25,28,30,31,33,34};
        int[] Children = {5,7,9,10,12,13,14,15,23,30};
        if (str.equals("Old")) {
            for(int k = 0; k < Old.length; k++){
                TourManager.removeCity(city[Old[k] - 1]);
            }
        } else if (str.equals("Pregnant")) {
            for(int k = 0; k < Pregnant.length; k++){
                TourManager.removeCity(city[Pregnant[k] - 1]);
            }
        } else if (str.equals("Children")) {
            for(int k = 0; k < Children.length; k++){
                TourManager.removeCity(city[Children[k] - 1]);
            }
        }

        System.out.println(TourManager.getSize());
        for(int k = 0; k < TourManager.getSize(); k++){
            xPoly[k] = (int)(city[k].getX() * 8.25 - 20); //調整成符合圖像大小
            yPoly[k] = (int)(city[k].getY() * 8.2 + 140); //調整成符合圖像大小
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
                new TSP_GA3();
            }
        });
    }
}
