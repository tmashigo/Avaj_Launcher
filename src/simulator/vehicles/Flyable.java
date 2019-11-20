package simulator.vehicles;

import simulator.WeatherTower;

public interface Flyable {

    public void updateConditions();

    public void registerTower(WeatherTower weatherTower);
}
