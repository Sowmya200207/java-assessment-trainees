package org.example.processingdata;
import org.example.dataingestion.Sensor;
import org.example.dataingestion.Thresholddata;
import org.example.datainjestion.Threshold;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class Outlierdetection {
    private static final Logger loggers=Logger.getLogger(Outlierdetection.class.getName());

    public <Sensor> void outliers(List<Sensor> sensorData, List<Threshold> thresholds){
        Map<String, Threshold> threshold = thresholds.stream()
                .collect(Collectors.toMap(Threshold::getSensor_type, t -> t));

        List<String []>outlierData=new ArrayList<>();
        outlierData.add(new String[]{"date","sensor_type","value","unit","location_id","threshold_exceeded [Min/Max]"});

        for(Sensor s:sensorData){
            Threshold th = threshold.get(s.getSensor_type());

            if (th != null) {
                if (s.getValue() < th.getMin_threshold()) {
                    outlierData.add(new String[]{s.getDate().toString(), s.getSensor_type(),
                            String.valueOf(s.getValue()), s.getUnit(),
                            String.valueOf(s.getLocation_id()), "Min"});
                } else if (s.getValue() > th.getMax_threshold()) {
                    outlierData.add(new String[]{s.getDate().toString(), s.getSensor_type(),
                            String.valueOf(s.getValue()), s.getUnit(),
                            String.valueOf(s.getLocation_id()), "Max"});
                }
            }
        }


        try (FileWriter writer = new FileWriter("")) {
            for (String[] row : outlierData) {
                writer.append(String.join(",", row)).append("\n");
            }
            loggers.info("Outlier detection completed and saved successfully.");
        }

        catch (IOException e) {
            loggers.severe("Error writing outliers.csv: " + e.getMessage());
        }

    }

}




