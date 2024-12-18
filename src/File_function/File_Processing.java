package File_function;


import Account.*;
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
                bw.write(zone.getID() + "," + zone.getName() + "," + zone.getLocation() + "," + zone.numViolationOccured + "," + zone.traffic_lights.size());
                bw.newLine();
                //TrafficLightID TrafficLightLocation TrafficLightStatus TrafficDuration TrafficLightTime(red|yellow|green)
                for (Traffic_Lights traffic_light : zone.traffic_lights) {
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
                Zone zone = new Zone(data[0], data[1], data[2], Integer.parseInt(data[3]));
                int traffic_light_count = Integer.parseInt(data[4]);
                for (int i = 0; i < traffic_light_count; i++) {
                    line = br.readLine();
                    data = line.split(",");
                    int[] arr = {Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6])};
                    Traffic_Lights traffic_light = new Traffic_Lights(data[0], data[1], Integer.parseInt(data[3]), data[2], arr);
                    traffic_light.redTime = Integer.parseInt(data[4]);
                    traffic_light.yellowTime = Integer.parseInt(data[5]);
                    traffic_light.greenTime = Integer.parseInt(data[6]);
                    zone.traffic_lights.add(traffic_light);
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
            bw.write("------------------------------------------------------------");
            bw.newLine();
            for (Admin admin : admins) {
                bw.write(admin.getID() + "," + admin.Name + "," + admin.Email + "," + admin.getPassword() + "," + admin.Contact);
                bw.newLine();
            }
            bw.write("------------------------------------------------------------");
            bw.newLine();
            for (TrafficOfficer officer : officers) {
                bw.write(officer.getID() + "," + officer.Name + "," + officer.Email + "," + officer.getPassword() + "," + officer.Contact + "," + officer.getassignedZone());
                bw.newLine();
            }
            bw.write("------------------------------------------------------------");
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
                bw.newLine();
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
            br.readLine();
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
                        Traffic_Violation.tra.add(tv);
                    }
                    owner.vehicle.add(vehicle);

                }
                owners.add(owner);
                br.readLine();
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

    public static void Save_Notifications(ArrayList<Owner> owners) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("File/NotificationData.txt"))) {
            for (Owner OW : owners) {
                if (OW.notifications.isEmpty()) continue;
                bw.write(OW.getID() + " " + OW.notifications.size());//regex by space
                bw.newLine();
                for (Notification n : OW.notifications) {
                    //body of notification in 10 lines
                    // regex مختلف
                    bw.write(n.title + "," + n.date + "," + n.isRead);
                    bw.newLine();
                    /*
                     * str[0] -> license plate , str[1] -> date , str[2] -> who issued , str[3] -> zone name , str[4] -> violation id , str[5] -> fine amount
                     */
                    bw.write(n.MessageData[0] + "," + n.MessageData[1] + "," + n.MessageData[2] + "," + n.MessageData[3] + "," + n.MessageData[4] + "," + n.MessageData[5]);
                    bw.newLine();
                    bw.write("------------------------------------------------------------");
                    bw.newLine();
                }
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load_Notifications(ArrayList<Owner> owners) {
        try (BufferedReader br = new BufferedReader(new FileReader("File/NotificationData.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                for (Owner OW : owners) {
                    if (OW.getID().equals(data[0])) {
                        int notification_count = Integer.parseInt(data[1]);
                        for (int i = 0; i < notification_count; i++) {
                            line = br.readLine();
                            data = line.split(",");
                            line = br.readLine();
                            String[] collectMessage = line.split(",");

                            Notification notification = new Notification(data[0], data[1], collectMessage, Boolean.parseBoolean(data[2]), OW.Name);
                            OW.notifications.add(notification);
                            br.readLine();
                        }
                        br.readLine();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
