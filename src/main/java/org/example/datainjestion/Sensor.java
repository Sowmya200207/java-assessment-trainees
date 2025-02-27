package org.example.datainjestion;
import java.util.Date;

public class Sensor {
        private Date date;
        private String sensor_type;
        private double value;
        private String unit;
        private double location_id;

        public Sensor(Date date, String sensor_type, double value, String unit, double location_id) {
            this.date = date;
            this.sensor_type = sensor_type;
            this.value = value;
            this.unit = unit;
            this.location_id = location_id;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getSensor_type() {
            return sensor_type;
        }

        public void setSensor_type(String sensor_type) {
            this.sensor_type = sensor_type;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public double getLocation_id() {
            return location_id;
        }

        public void setLocation_id(double location_id) {
            this.location_id = location_id;
        }

        @Override
        public String toString() {
            return "Sensor{" +
                    "date=" + date +
                    ", sensor_type='" + sensor_type + '\'' +
                    ", value=" + value +
                    ", unit='" + unit + '\'' +
                    ", location_id=" + location_id +
                    '}';
        }
    }




