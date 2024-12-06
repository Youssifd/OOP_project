package File_function;



import Area.*;
import java.io.*;
import java.util.ArrayList;

public class File_Processing {
  public static void Load_Data(ArrayList<Zone> zones) {
    try {
      FileInputStream file = new FileInputStream("data.txt");
      ObjectInputStream in = new ObjectInputStream(file);
      zones = (ArrayList<Zone>) in.readObject();
      in.close();
      file.close();
    } catch (IOException e) {
      System.out.println("IOException is caught");
    } catch (ClassNotFoundException e) {
      System.out.println("ClassNotFoundException is caught");
    }
  }
}