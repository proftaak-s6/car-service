package nl.fontysproject.government.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {

    //region properties

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;                        // 1

    @Column
    private long ownerId;                   //12345

    @Column
    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OwnershipHistory> ownershipHistoryList = new ArrayList<>();

    @Column(unique = true)
    private String licensePlateNumber;      // 32LPVV

    @Column
    private String brand;                   // OPEL

    @Column
    private String series;                  // CORSA-C

    @Column
    private String vehicleType;             // Personenauto

    @Column
    private String engineType;              // 1199

    @Column
    private String fuelType;                // Benzine

    @Column
    private String energyLabel;             // C

    //region constructors
    public Car() {

    }

    public Car(String licensePlateNumber, String brand, String series, String vehicleType, String engineType,
               String fuelType, String energyLabel) {
        this.licensePlateNumber = licensePlateNumber;
        this.brand = brand;
        this.series = series;
        this.vehicleType = vehicleType;
        this.engineType = engineType;
        this.fuelType = fuelType;
        this.energyLabel = energyLabel;
    }
    //endregion

    //endregion

    //region relations

    @OneToOne
    @JoinColumn
    @JsonManagedReference
    private Tracker tracker;

    //endregion

    //region getters & setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public List<OwnershipHistory> getOwnershipHistoryList() {
        return ownershipHistoryList;
    }

    public void setOwnershipHistoryList(List<OwnershipHistory> ownershipHistoryList) {
        this.ownershipHistoryList = ownershipHistoryList;
    }

    //endregion
}
