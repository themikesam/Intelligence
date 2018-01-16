/*
* 將我們放在TSP_GA(主程式)的座標放進來
* 控制City座標資訊
*/
import java.util.ArrayList;

public class TourManager {

    // Holds our cities
    private static ArrayList destinationCities = new ArrayList<City>();

    // Adds a destination city
    public static void addCity(City city) {
        destinationCities.add(city);
    }

    public static void removeCity(City city) {
        destinationCities.remove(city);
    }

    public static int getSize() {
        return destinationCities.size();
    }

    // Get a city
    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }

    // Get the number of destination cities
    public static int numberOfCities(){
        return destinationCities.size();
    }
}
