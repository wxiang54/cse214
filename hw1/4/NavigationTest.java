public class NavigationTest {

    public static void main(String[] args) {
        Location myCurrentLocation = new Location(354, 538);
        Location myDestination = new Location(108, 25);

        Car myCar = new Car();

        //Initializing GPS object and setting current and destination locations
        GPS myGPS = new GPS();

        myGPS.setCurrentLocation(myCurrentLocation);
        myGPS.setDestination(myDestination);
        myCar.setGPS(myGPS);

	    //Start the car and set current speed
        myCar.setCurrentSpeed(35.0);

        //See output
        System.out.println("Distance: " + ((GPS) myCar.getGPS()).getCalculatedDistance());
        System.out.println("Arrival : " + ((GPS) myCar.getGPS()).getArrivalTime());
    }
}
