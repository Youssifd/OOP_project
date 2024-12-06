package File_function;


import Area.*;

import java.io.*;
import java.util.ArrayList;

public class File_Processing {

    public static void Load_Data() {}

    public static void Sava_Data() {}

    public static void Save_Zones(ArrayList<Zone> zones) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("File/ZoneDate.txt"))) {
            for (Zone zone : zones) {
                bw.write(zone.getID() + "," + zone.getName() + "," + zone.getLocation() + "," + zone.traffic_light.size());
                bw.newLine();
                //TrafficLightID TrafficLightLocation TrafficLightStatus TrafficDuration TrafficLightTime(red|yellow|green)
                for (Traffic_Lights traffic_light : zone.traffic_light) {
                    bw.write(traffic_light.getID() + "," + traffic_light.getLocation() + "," + traffic_light.Status + "," + traffic_light.getDuration() + "," + traffic_light.redTime + "," + traffic_light.yellowTime + "," + traffic_light.greenTime);
                    bw.newLine();
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void Load_Zones(ArrayList<Zone> zones) {
        try (BufferedReader br = new BufferedReader(new FileReader("File/ZoneDate.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Zone zone = new Zone(data[0], data[1], data[2]);
                int traffic_light_count = Integer.parseInt(data[3]);
                for(int i = 0; i < traffic_light_count; i++) {
                    line = br.readLine();
                    data = line.split(",");
                    int[] arr= {Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6])};
                    Traffic_Lights traffic_light = new Traffic_Lights(data[0], data[1], Integer.parseInt(data[3]), data[2],arr);
                    traffic_light.redTime = Integer.parseInt(data[4]);
                    traffic_light.yellowTime = Integer.parseInt(data[5]);
                    traffic_light.greenTime = Integer.parseInt(data[6]);
                    zone.traffic_light.add(traffic_light);
                }

                zones.add(zone);
                br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
