package Account;

public abstract class Account {
    public static int Acc_counter = 0;
    private String ID;
    public String Email;
    protected String Password;
    public String Name;

    public Account(String ID, String Email, String Password, String Name) {
        this.ID = ID;
        this.Email = Email;
        this.Password = Password;
        this.Name = Name;
        Acc_counter++;
    }

    public String getID() {
        return ID;
    }
}
