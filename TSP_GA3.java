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
    public Polygon poly2;
    public TSP_GA3() {
        initComponents();
    }
    static int xPoly[] = new int [50];
    static int yPoly[] = new int [50];
    static int x2Poly[] = new int [50];
    static int y2Poly[] = new int [50];
    public Image image;
    public void initComponents() {
        mainMap = new JFrame();
        mainMap.setResizable(false);
        mainMap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        poly = new Polygon(xPoly, yPoly, TourManager.getSize());
        poly2 = new Polygon(x2Poly, y2Poly, TourManager.getSize());
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
                g.setColor(Color.RED);
                g.drawPolygon(poly2);
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
        BufferedReader reader = new BufferedReader(new FileReader(("test1.txt")));
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
            city[i] = new City(line);
            TourManager.addCity(city[i]);
            i++;

        }
        reader.close();
        System.out.println("(Old/Pregnant/Children/Normal)");
        System.out.println("Please Enter Ur Identity: ");
        str = input.next();
        input.close();
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
        // for(int k = 0; k < TourManager.getSize(); k++){
        //     xPoly[k] = (int)(city[k].getX() * 8.25 - 20); //調整成符合圖像大小
        //     yPoly[k] = (int)(city[k].getY() * 8.2 + 140); //調整成符合圖像大小
        // }

        // Initialize population
        Population pop = new Population(50, true);
        System.out.println("Initial:");
        System.out.println("Distance: " + pop.getFittest().getDistance());
        System.out.println("Path: ");
        System.out.println(pop.getFittest());
        System.out.println(pop.getFittest());
        PrintWriter writer_before = new PrintWriter("result_before.txt", "UTF-8");
        writer_before.println(pop.getFittest());
        writer_before.close();
        BufferedReader reader_before = new BufferedReader(new FileReader(("result_before.txt")));
        // Read lines from file.
        int m = 0;
        String[] name_before = new String [100];
        String[] x_before = new String [100];
        String[] y_before = new String [100];
        while (m!=TourManager.getSize()) {
            String line_result_before = reader_before.readLine();
            if (line_result_before == null) {
                break;
            } else if(line_result_before.contains("\n") || line_result_before.contains("\r\n")) {
                break;
            }
            String[] value = line_result_before.split(",");
            // System.out.println(l+":"+value.length);
            name_before[m] = value[0];
            x_before[m] = value[1];
            y_before[m] = value[2];
            m++;
        }
        reader_before.close();
        for(int k = 0; k < TourManager.getSize(); k++){
            xPoly[k] = (int)(Integer.parseInt(x_before[k] )* 8.25 - 20);
            yPoly[k] = (int)(Integer.parseInt(y_before[k].substring(0, y_before[k].length() - 1))* 8.2 + 140);
        }
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
        PrintWriter writer = new PrintWriter("result.txt", "UTF-8");
        writer.println(pop.getFittest());
        writer.close();
        BufferedReader reader1 = new BufferedReader(new FileReader(("result.txt")));
        // Read lines from file.
        int l = 0;
        String[] name = new String [100];
        String[] x = new String [100];
        String[] y = new String [100];
        while (l!=TourManager.getSize()) {
            String line_result = reader1.readLine();
            if (line_result == null) {
                break;
            } else if(line_result.contains("\n") || line_result.contains("\r\n")) {
                break;
            }
            String[] value = line_result.split(",");
            // System.out.println(l+":"+value.length);
            name[l] = value[0];
            x[l] = value[1];
            y[l] = value[2];
            l++;
        }
        reader1.close();
        for(int k = 0; k < TourManager.getSize(); k++){
            x2Poly[k] = (int)(Integer.parseInt(x[k] )* 8.25 - 20);
            y2Poly[k] = (int)(Integer.parseInt(y[k].substring(0, y[k].length() - 1))* 8.2 + 140);
        }
        for(int k = 0;k<TourManager.getSize();k++){
            // System.out.print(name[k].substring(1));
            // System.out.print(x[k]);
            // System.out.print(y[k].substring(0, y[k].length() - 1));
            // System.out.println();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TSP_GA3();
            }
        });
    }
}
