package Traffic;




    public  class Traffic_Violation{

        private String  TracksViolationID ;
        private  String Vehicle_ID;
        private String Violation_type;
        private String date;
        private    double fine_amount;
        public Traffic_Violation(String TracksViolationID,String Vehicle_ID,
                                String Violation_type,String date,double fine_amount ){
            this.TracksViolationID=TracksViolationID;
            this.Vehicle_ID=Vehicle_ID;
            this.Violation_type=Violation_type;
            this.date=date;
            this.fine_amount=fine_amount;

        }

        public Traffic_Violation(java.lang.String number, java.lang.String number1, java.lang.String overspeed, java.lang.String number2, double fineAmount) {
        }

        public String getTracksViolationID() {
            return TracksViolationID;
        }

        public void setTracksViolationID(String tracksViolationID) {
            TracksViolationID = tracksViolationID;
        }

        public String getVehicle_ID() {
            return Vehicle_ID;
        }

        public void setVehicle_ID(String vehicle_ID) {
            Vehicle_ID = vehicle_ID;
        }

        public String getViolation_type() {
            return Violation_type;
        }

        public void setViolation_type(String violation_type) {
            Violation_type = violation_type;
        }


        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getFine_amount() {
            return fine_amount;
        }

        public void setFine_amount(double fine_amount) {
            this.fine_amount = fine_amount;
        }
    }


