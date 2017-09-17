public class Car {
    private GPS _gps;
    private double _speed;

    public Car() {
        _speed = 0.0;
    }

    public GPS getGPS() {
        return _gps;
    }

    public void setGPS(GPS gps) {
        _gps = gps;
        _gps.setCar(this); //so gps can access car speed
    }

    public double getCurrentSpeed() {
        return _speed;
    }

    public void setCurrentSpeed(double speed) {
        _speed = speed;
    }
}