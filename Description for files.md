#ZoneData
**Zone
#1 ZoneID ZoneName ZoneLocation numberOfViolationOccuredAtZone  Traffic_Light_count
**TrafficLightData
#2 TrafficLightID TrafficLightLocation TrafficLightStatus TrafficDuration TrafficLightTime(red|yellow|green)
The traffic light data is loaded sequentially for each zone: (zone-> group of traffic lights)
==============================================================================================================
#AccData
**admin
AccID, Name, Email,Password ,Contact
**officer
AccID, Name, Email, Password ,Contact,AssignedZone
**owner
AccID, Name, Email, Password ,Contact,Vehicle_Size
->Vehicle
getId Type LicensePlate owner TV.size
-->TV
ViolationID, VehicleLicense, ViolationType, Date, zoneName, FineAmount, whoIssued, Status
==============================================================================================================
#NotificationData
Owner_ID
->Notification
NotificationTitle , NotificationDate, NotificationStatus(NotificationRead|NotificationUnread)
NotificationMessage->  license plate , date , who issued , zone name , violation id , fine amount