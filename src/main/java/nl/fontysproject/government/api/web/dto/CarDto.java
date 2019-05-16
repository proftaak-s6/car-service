package nl.fontysproject.government.api.web.dto;

import nl.fontysproject.government.api.model.Car;
import nl.fontysproject.government.api.model.Tracker;



public class CarDto {

    private String licensePlateNumber;
    private String brand;
    private String series;
    private String vehicleType;
    private String engineType;
    private String fuelType;
    private String energyLabel;
    private long trackerId;

    public Car toModel(){
        Car car = new Car(licensePlateNumber,brand,series,vehicleType,engineType, fuelType, energyLabel);
        Tracker tracker = new Tracker();
        tracker.setId(trackerId);
        car.setTracker(tracker);
        return car;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEnergyLabel() {
        return energyLabel;
    }

    public void setEnergyLabel(String energyLabel) {
        this.energyLabel = energyLabel;
    }

    public long getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(long trackerId) {
        this.trackerId = trackerId;
    }
}
