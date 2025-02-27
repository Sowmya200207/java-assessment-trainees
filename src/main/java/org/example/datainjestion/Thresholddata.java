package org.example.datainjestion;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class Thresholddata {
        private static final Logger loggers=Logger.getLogger(Thresholddata.class.getName());

        public void readThreshold() throws FileNotFoundException {
            ArrayList<Threshold> thresholdData = new ArrayList<>();

            try(BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\SowmyaSaridi\\Downloads\\Core_Java\\Core_Java\\Sensorsystem\\src\\main\\java\\org\\example\\datainjestion\\thresholds.csv"))) {
                String line;
                while((line=br.readLine())!=null){
                    String[] split = line.split(",");

                     thresholdData.add(new Threshold(split[0],Integer.parseInt(split[1]),Integer.parseInt(split[2])));


                }
                loggers.info("Data is successfully uploaded");
            }

            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
