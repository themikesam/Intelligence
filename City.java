// ��l�ư򥻦�m
public class City {
    int x; // x�y��
    int y; // y�y��
    String cityName;

    // Constructs a randomly placed city �p�G�S���w�]�y�СA���ü�
    public City(){
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
    }

    // Constructs a city at chosen x, y location
    public City(String cityName, int x, int y){
        this.cityName = cityName;
        this.x = x;
        this.y = y;
    }

    // Gets city's name
    public String getName(){
        return this.cityName;
    }

    // Gets city's x coordinate
    public int getX(){
        return this.x;
    }

    // Gets city's y coordinate
    public int getY(){
        return this.y;
    }

    // Gets the distance to given city ���o�Z��
    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );

        return distance;
    }

    @Override
    public String toString(){
        return getName() + ":" + getX() + ", " + getY();
    }
}
