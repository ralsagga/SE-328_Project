package com.task.se_3282022s.weather;



import java.io.Serializable;
import java.util.List;

public class WeatherModel  {


    private Coord coord;
    private List<Weather> weather = null;
    private String base;
    private Main main;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Integer dt;
    private Sys sys;
    private Integer id;
    private String name;
    private Integer cod;
    public List<Weather> getWeather() {
        return weather;
    }
    public class Weather  {

        private Integer id;
        private String main;
        private String description;
        private String icon;
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {

            this.id = id;
        }
        public String getMain() {
            return main;
        }
        public void setMain(String main) {

            this.main = main;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {

            this.icon = icon;
        }
    }

    public Main getMain() {

        return main;
    }
    public Wind getWind() {

        return wind;
    }
    public Sys getSys() {
        return sys;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public class Wind   {

        private Double speed;
        private Integer deg;
        public Double getSpeed() {
            return speed;
        }
        public void setSpeed(Double speed) {
            this.speed = speed;
        }
        public Integer getDeg() {
            return deg;
        }
        public void setDeg(Integer deg) {
            this.deg = deg;
        }
    }

    public class Sys  {


        private Integer type;
        private Integer id;
        private Double message;
        private String country;
        private Integer sunrise;
        private Integer sunset;
        public Integer getType() {
            return type;
        }
        public void setType(Integer type) {
            this.type = type;
        }
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public Double getMessage() {
            return message;
        }
        public void setMessage(Double message) {
            this.message = message;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public Integer getSunrise() {
            return sunrise;
        }
        public void setSunrise(Integer sunrise) {
            this.sunrise = sunrise;
        }
        public Integer getSunset() {
            return sunset;
        }
        public void setSunset(Integer sunset) {
            this.sunset = sunset;
        }
    }

    public class Coord  {


        private Double lon;
        private Double lat;
        public Double getLon() {
            return lon;
        }
        public void setLon(Double lon) {
            this.lon = lon;
        }
        public Double getLat() {
            return lat;
        }
        public void setLat(Double lat) {
            this.lat = lat;
        }
    }
    public   class Clouds {

        private Integer all;
        public Integer getAll() {

            return all;
        }
        public void setAll(Integer all) {
            this.all = all;
        }
    }
    public   class Main  {

        private Double temp;
        private Integer pressure;
        private Integer humidity;
        private Double tempMin;
        private Double tempMax;

        public Double getTemp() {
            return temp;
        }

        public Integer getHumidity() {
            return humidity;
        }

        public Double getTempMin() {
            return tempMin;
        }

        public Double getTempMax() {
            return tempMax;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", tempMin=" + tempMin +
                    ", tempMax=" + tempMax +
                    '}';
        }
    }


}
