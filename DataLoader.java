package project_2;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.IOException;

public class DataLoader implements Serializable {

	// Data loading method
    public static Object loadData(String fileName) {
    	
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object data = ois.readObject();
            System.out.println("Data loaded from " + fileName);
            return data;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
