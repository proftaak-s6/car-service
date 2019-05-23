package nl.fontysproject.government.api.web.dto;

import nl.fontysproject.government.api.model.Tracker;

import java.util.Date;

public class TrackerDto {
    private String manufacturer;
    private Date activationDate;

    public Tracker toModel(){
        Tracker tracker = new Tracker();
        tracker.setActivationDate(activationDate);
        tracker.setManufacturer(manufacturer);
        return tracker;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }
}
