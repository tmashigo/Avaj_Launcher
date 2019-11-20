package simulator.vehicles;

import simulator.Logger;
import simulator.WeatherTower;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        HashMap<String, String> map = new HashMap<>();
        map.put("SUN", "Burn it up!");
        map.put("FOG", "Am I even visible?");
        map.put("SNOW", "Winter is here!");
        map.put("RAIN", "Shower time!");


        String weather = weatherTower.getWeather(this.coordinates);

        if (weather.equals("SUN")) {
            this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10,
                    coordinates.getHeight() + 2 > 100 ? 100 : coordinates.getHeight() + 2);
        }
        if (weather.equals("RAIN")) {
            this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5,
                    coordinates.getHeight());
        }
        if (weather.equals("FOG")) {
            this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1,
                    coordinates.getHeight());
        }
        if (weather.equals("SNOW")) {
            this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(),
                    coordinates.getHeight() + 7 > 100 ? 100 : coordinates.getHeight() + 7);
        }
        if (coordinates.getHeight() == 0) {
            Logger.addMessage("JetPlane# " + this.name + this.id + " landing.");
            this.weatherTower.unregister(this);
        }

        Logger.addMessage("JetPlane#" + this.name + "(" + this.id + "): " + map.get(weather));
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Logger.addMessage("Tower says: JetPlane#" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}
