package demo.example.model;

public class ForecastRequest {

    private String location;
    private String temperature;
    private String temperatureApparent;
    private String cloudCover;
    private String windSpeed;
    private String windGust;


    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureApparent() {
        return temperatureApparent;
    }

    public void setTemperatureApparent(String temperatureApparent) {
        this.temperatureApparent = temperatureApparent;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindGust() {
        return windGust;
    }

    public void setWindGust(String windGust) {
        this.windGust = windGust;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
