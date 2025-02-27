package org.example.datainjestion;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Sensordata {
        private static final Logger logger = Logger.getLogger(Sensordata.class.getName());

        public void readSensor() throws FileNotFoundException {
            ArrayList<Sensordata> sensorData = new ArrayList<>();

            try(BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\SowmyaSaridi\\Downloads\\Core_Java\\Core_Java\\Sensorsystem\\src\\main\\java\\org\\example\\datainjestion\\sensor_data.csv "))) {
                String line;
                while((line= br.readLine())!=null){
                    String[] split = line.split(",");
                    sensorData.add(new Sensordata(Date.valueOf(split[0]),split[1],Double.parseDouble(split[2]),split[3],Double.parseDouble(split[4])));
                }
                logger.info("Data uploaded Successfully");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



