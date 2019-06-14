package nl.fontysproject.government.api.controller;

import nl.fontysproject.government.api.model.Car;
import nl.fontysproject.government.api.model.OwnershipHistory;
import nl.fontysproject.government.api.service.Implementations.CarServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;


@ApplicationScoped
public class CarController {

    private static final String RDW_URL_CAR = "https://opendata.rdw.nl/resource/m9d7-ebf2.json";
    private static final String RDW_APP_TOKEN = "6LlkP0MqOKimMkNdqipMXxOZN";

    @Inject
    CarServiceImpl carService;

    public Car getCarFromRDW(String licensePlateNumber) {
        try {
            String json = getJSONFromURL(String.format("%s?kenteken=%s", RDW_URL_CAR, licensePlateNumber.toUpperCase()));
            return getCarFromJSON(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Car getCarById(long id) {
        return carService.getById(id);
    }

    public Car getCarByLicensePlateNumber(String licensePlateNumber) {
        return carService.getByLicensePlateNumber(licensePlateNumber);
    }

    public List<Car> getAllCars() {
        return carService.getAll();
    }

    public Car createCar(Car model) {
        return carService.create(model);
    }

    public Car updateCar(Car model) {
        return carService.update(model);
    }

    public boolean deleteCar(long id) {
        return carService.delete(id);
    }

    public boolean deleteCarByLicensePlateNumber(String licensePlateNumber) {
        return carService.deleteByLicensePlateNumber(licensePlateNumber);
    }

    public Car assignOwnerToCar(long carId, long ownerId) {
        Car carToAssign = carService.getById(carId);
        List<OwnershipHistory> historyList = carToAssign.getOwnershipHistoryList();
        for(int i = 0; i < historyList.size(); i++){
            if(historyList.get(i).getOwnerId() == carToAssign.getOwnerId()){
                historyList.get(i).setEndDate(new Date());
            }
        }
        carToAssign.setOwnerId(ownerId);
        OwnershipHistory newOwnership = new OwnershipHistory(ownerId, new Date(), null);
        newOwnership.setCar(carToAssign);
        historyList.add(newOwnership);
        carToAssign.setOwnershipHistoryList(historyList);
        return carService.update(carToAssign);
    }

    public List<Car> getCarsWithTrackerByOwner(long ownerId) {
        return carService.getCarsWithTrackerByOwner(ownerId);
    }

    //region private methods
    private Car getCarFromJSON(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String licensePlateNumber = jsonObject.getString("kenteken");
        return new Car(
                licensePlateNumber,
                jsonObject.has("merk") ? jsonObject.getString("merk") : "N/A",
                jsonObject.has("handelsbenaming") ? jsonObject.getString("handelsbenaming") : "N/A",
                jsonObject.has("voertuigsoort") ? jsonObject.getString("voertuigsoort") : "N/A",
                jsonObject.has("cilinderinhoud") ? jsonObject.getString("cilinderinhoud") : "N/A",
                getRDWFuelType(jsonObject.getString("api_gekentekende_voertuigen_brandstof"),
                        licensePlateNumber.toUpperCase()),
                jsonObject.has("zuinigheidslabel") ? jsonObject.getString("zuinigheidslabel") : "N/A"
        );
    }

    private String getRDWFuelType(String url, String kenteken) {
        JSONObject jsonObject = new JSONObject(getJSONFromURL(String.format("%s?kenteken=%s", url, kenteken)));
        return jsonObject.has("brandstof_omschrijving") ? jsonObject.getString("brandstof_omschrijving") : "N/A";
    }

    private String getJSONFromURL(String url) {
        try {
            URL rdwUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) rdwUrl.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("X-App-Token", RDW_APP_TOKEN);

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line+"\n");
            }
            reader.close();

            JSONArray jsonArray = new JSONArray(builder.toString());
            return jsonArray.optString(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "[]";
    }
    //endregion
}
