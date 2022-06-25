package demo.example.model;

public class WeatherUnit {

    private String startTime;
    private String temperature;
    private String temperatureApparent;
    private String cloudCover;
    private String windSpeed;
    private String windGust;
    private String humidity;
    private String precipitationProbability;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

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

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(String precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }


    @Override
    public String toString() {
        return "WeatherUnit{" +
                "startTime='" + startTime + '\'' +
                ", temperature='" + temperature + '\'' +
                ", temperatureApparent='" + temperatureApparent + '\'' +
                ", cloudCover='" + cloudCover + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windGust='" + windGust + '\'' +
                ", humidity='" + humidity + '\'' +
                ", precipitationProbability='" + precipitationProbability + '\'' +
                '}';
    }
}
