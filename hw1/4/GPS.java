public class GPS {
    private Location _currentLocation;
    private Location _destination;
    private Car _car;

    public GPS() {}

    public void setCurrentLocation(Location currentLocation) {
        _currentLocation = currentLocation;
    }
    public void setDestination(Location destination) {
        _destination = destination;
    }
    public void setCar(Car car) {
        _car = car;
    }

    public double getCalculatedDistance() {
        return Math.sqrt(
                Math.pow(_destination.getX() - _currentLocation.getX(), 2) +
                Math.pow(_destination.getY() - _currentLocation.getY(), 2)
        );
    }

    public double getArrivalTime() {
        if (_car.getCurrentSpeed() == 0.0)
            throw new IllegalArgumentException("Car is not moving!");
        // t = x / v
        return getCalculatedDistance() / _car.getCurrentSpeed();
    }
}