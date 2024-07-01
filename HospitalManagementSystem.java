package project_2;

import java.io.Serializable;

public class HospitalManagementSystem implements Serializable {
	
	private static final String DATA_FILE = "data.dat";
    
	private static Object loadedData;
	
    public HospitalManagementSystem() {
    	new Login().initializeLogin(); // Showing the login screen before the program starts
    }
    
    public static void main(String[] args) {
    	
    	loadedData = DataLoader.loadData(DATA_FILE);
    	
    	new HospitalManagementSystem();
 
    	
        // Shutdown hook to save the data before the program closes
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Call your method here
            invokeBeforeExit();
        }));
    }
    
    private static void invokeBeforeExit() {
    	DataSaver.saveData(loadedData, DATA_FILE);
        System.out.println("Data saved successfully. Program exited.");
    }
}
