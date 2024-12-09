package File_function;


import Account.Owner;
import Admin.Admin;
import Traffic_Officer.TrafficOfficer;
import Area.*;
import Vehicle.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.ArrayList;

public class File_Processing {

    public static void Load_Data() {
    }

    public static void Sava_Data() {
    }

    public static void Save_Zones(ArrayList<Zone> zones) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("File/ZoneData.txt"))) {
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
        try (BufferedReader br = new BufferedReader(new FileReader("File/ZoneData.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Zone zone = new Zone(data[0], data[1], data[2]);
                int traffic_light_count = Integer.parseInt(data[3]);
                for (int i = 0; i < traffic_light_count; i++) {
                    line = br.readLine();
                    data = line.split(",");
                    int[] arr = {Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6])};
                    Traffic_Lights traffic_light = new Traffic_Lights(data[0], data[1], Integer.parseInt(data[3]), data[2], arr);
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

    public static void Save_Accounts(ArrayList<Admin> admins, ArrayList<TrafficOfficer> officers, ArrayList<Owner> owners) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("File/AccData.txt"))) {
            bw.write(admins.size() + "," + officers.size() + "," + owners.size());
            bw.newLine();
            for (Admin admin : admins) {
                bw.write(admin.getID() + "," + admin.Name + "," + admin.Email + "," + admin.getPassword() + "," + admin.Contact);
                bw.newLine();
            }
            bw.newLine();
            for (TrafficOfficer officer : officers) {
                bw.write(officer.getID() + "," + officer.Name + "," + officer.Email + "," + officer.getPassword() + "," + officer.Contact + "," + officer.getassignedZone());
                bw.newLine();
            }
            bw.newLine();
            for (Owner owner : owners) {
                bw.write(owner.getID() + "," + owner.Name + "," + owner.Email + "," + owner.getPassword() + "," + owner.Contact + "," + owner.vehicle.size());
                bw.newLine();
                for (Vehicle vehicle : owner.vehicle) {
                    // bw.write(vehicle.getPlateNumber() + "," + vehicle.getBrand() + "," + vehicle.getModel() + "," + vehicle.getColor() + "," + vehicle.getYear());
                    bw.write(vehicle.getId() + "," + vehicle.getType() + "," + vehicle.getLicensePlate() + "," + owner.Name + "," + vehicle.TV.size());
                    bw.newLine();
                    for (Traffic_Violation tv : vehicle.TV) {
                        bw.write(tv.getViolationID() + "," + tv.getVehicle_ID() + "," + tv.getViolation_type() + "," + tv.getDate() + "," + tv.getZoneName() + "," + tv.getFine_amount() + "," + tv.getWhoIssued() + "," + tv.Status);
                        bw.newLine();
                    }

                    // bw.newLine();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void Load_Accounts(ArrayList<Admin> admins, ArrayList<TrafficOfficer> officers, ArrayList<Owner> owners) {
        try (BufferedReader br = new BufferedReader(new FileReader("File/AccData.txt"))) {
            String line = br.readLine();
            String[] data = line.split(",");
            int admin_count = Integer.parseInt(data[0]);
            int officer_count = Integer.parseInt(data[1]);
            int owner_count = Integer.parseInt(data[2]);
            for (int i = 0; i < admin_count; i++) {
                line = br.readLine();
                data = line.split(",");
                Admin admin = new Admin(data[0], data[1], data[2], data[3], data[4]);
                admins.add(admin);
            }
            br.readLine();
            for (int i = 0; i < officer_count; i++) {
                line = br.readLine();
                data = line.split(",");
                TrafficOfficer officer = new TrafficOfficer(data[0], data[1], data[2], data[3], data[4], data[5]);
                officers.add(officer);
            }
            br.readLine();
            for (int i = 0; i < owner_count; i++) {
                line = br.readLine();
                data = line.split(",");
                Owner owner = new Owner(data[0], data[1], data[2], data[3], data[4]);
                int vehicle_count = Integer.parseInt(data[5]);
                for (int j = 0; j < vehicle_count; j++) {
                    line = br.readLine();
                    data = line.split(",");
                    Vehicle vehicle = new Vehicle(data[0], data[1], data[2], data[3]);
                    int tv_count = Integer.parseInt(data[4]);
                    for (int k = 0; k < tv_count; k++) {
                        line = br.readLine();
                        data = line.split(",");
                        Traffic_Violation tv = new Traffic_Violation(data[0], data[1], data[2], data[3], data[4], Double.parseDouble(data[5]), data[6], data[7]);
                        vehicle.TV.add(tv);
                    }
                    owner.vehicle.add(vehicle);

                }
                owners.add(owner);
            }
            //load violations to officers
            for (TrafficOfficer officer : officers) {
                for (Owner owner : owners) {
                    for (Vehicle vehicle : owner.vehicle) {
                        for (Traffic_Violation tv : vehicle.TV) {
                            if (tv.getWhoIssued().equals(officer.Name)) {
                                officer.addviolations(tv);
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
