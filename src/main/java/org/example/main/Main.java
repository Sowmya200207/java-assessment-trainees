package org.example.main;
import org.example.datainjestion.Sensordata;
import org.example.datainjestion.Thresholddata;
import org.example.datainjestion.Sensor;
import org.example.datainjestion.Threshold;
import org.example.processingdata.MonthlyCalculation;
import org.example.processingdata.Outlierdetection;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
         final Logger loggers = Logger.getLogger(Main.class.getName());

            try {
                Sensordata loadSensorData = new Sensordata();
                List<Sensor> sensorData = Sensordata.readSensor();

                Thresholddata loadThresholdData = new Thresholddata();
                List<Threshold> thresholds = Thresholddata.readThreshold();


                MonthlyCalculation monthlyCalculationSensorData = new MonthlyCalculation();
                monthlyCalculationSensorData.monthlyStatistics(sensorData);

                Outlierdetection outlierDetection = new Outlierdetection();
                outlierDetection.outliers(sensorData, thresholds);
            } catch (FileNotFoundException e) {
                loggers.severe(e.getMessage());
            }
        }
    }

