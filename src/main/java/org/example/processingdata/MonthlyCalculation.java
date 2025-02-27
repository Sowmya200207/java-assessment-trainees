package org.example.processingdata;
import org.example.datainjestion.Sensordata;
import org.example.datainjestion.Sensor;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class MonthlyCalculation {

    public class MonthlyCalculationSensor {
        private static final Logger loggers=Logger.getLogger(MonthlyCalculationSensor.class.getName());
        public void monthlyStatistics(List<Sensor> sensorData){
            Map<String,Map<String,Double>>calc=new HashMap<>();

            Map<String, Map<String, List<Sensor>>> collect = sensorData.stream()
                    .collect(Collectors.groupingBy(
                            Sensor::getSensor_type, // Group by sensor type
                            Collectors.groupingBy(sensor -> new SimpleDateFormat("yyyy-MM").format(sensor.getDate()))));

            List<String[]> csvData = new ArrayList<>();
            csvData.add(new String[]{"sensor_type", "month", "avg_value", "max_value", "min_value"}); // CSV Header

            for (Map.Entry<String, Map<String, List<Sensor>>> sensorEntry : collect.entrySet()) {
                String sensorType = sensorEntry.getKey();

                for (Map.Entry<String, List<Sensor>> monthEntry : sensorEntry.getValue().entrySet()) {
                    String month = monthEntry.getKey();
                    List<Sensor> monthlySensors = monthEntry.getValue();

                    double avgValue = monthlySensors.stream().mapToDouble(Sensor::getValue).average().orElse(0.0);
                    double maxValue = monthlySensors.stream().mapToDouble(Sensor::getValue).max().orElse(0.0);
                    double minValue = monthlySensors.stream().mapToDouble(Sensor::getValue).min().orElse(0.0);

                    // Add the computed values to CSV data list
                    csvData.add(new String[]{sensorType, month, String.valueOf(avgValue), String.valueOf(maxValue), String.valueOf(minValue)});
                }
            }

            try (FileWriter writer = new FileWriter("C:\\Users\\SowmyaSaridi\\Downloads\\Core_Java\\Core_Java\\Sensorsystem\\src\\main\\java\\org\\example\\processingdata\\Monthlystats.csv")) {
                for (String[] rowData : csvData) {
                    writer.append(String.join(",", rowData)).append("\n");
                }
                loggers.info("Monthly statistics calculated and saved successfully.");
            } catch (IOException e) {
                loggers.severe("Error writing to CSV file: " + e.getMessage());
            }
        }

    }
}
