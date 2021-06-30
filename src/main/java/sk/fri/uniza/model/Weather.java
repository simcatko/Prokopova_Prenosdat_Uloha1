package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("airTemperature")
    private Double airTemperature;

    @JsonProperty("humidity")
    private Integer humidity;

    @JsonProperty("rainIntensity")
    private Integer rainIntensity;

    @JsonProperty("time")
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public void setRainIntensity(Integer rainIntensity) {
        this.rainIntensity = rainIntensity;
    }

    public Integer getRainIntensity() {
        return rainIntensity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
