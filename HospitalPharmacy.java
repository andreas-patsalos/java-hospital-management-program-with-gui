package project_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class HospitalPharmacy extends Hospital {
	
	// Data fields
	private String medicineID;
	private String medicineName;
	private List<String> availableMedications;
	private int count;
	
	// Constructors
	public HospitalPharmacy() {
		this.availableMedications = new ArrayList<>();
		super.setPharmacy(this);
	}
	
	public HospitalPharmacy(String id) {
		this.medicineID = id;
	}
	
	public HospitalPharmacy(String id, String name) {
		this.medicineID = id;
 		this.medicineName = name;
 	}
	
	public HospitalPharmacy(String id, String name, List<String> availableMedications) {
		this.medicineID = id;
		this.medicineName = name;
        this.availableMedications = availableMedications;
    }
	
	// Set methods
	public void setMedicineID(String id) {
		this.medicineID = id;
	 }
	 	
	 public void setMedicineName(String name) {
		 this.medicineName = name;
	} 
	 
	// Get methods
	public String getMedicineID() {
	    return medicineID;
	}

	public String getMedicineName() {
	    return medicineName;
	}
	
	public List<String> getAvailableMedications() {
        return availableMedications;
    }
	
	// Add medicine to the list method
    public void addMedicine(String medicine) {
        this.availableMedications.add(medicine);
    }
    
 	// GUI Screen
 	private JFrame mainFrame;
 	private JTextField nameField;
 	private JButton submitButton;
 	private JButton backButton;
 	
 	final void displayPharmacyPane() {
    	mainFrame = new JFrame(Hospital.hospitalName + " Management System");
    	// staffFrame.setResizable(false);
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        JLabel pharmacyLabel = new JLabel("New medicine info:");
        pharmacyLabel.setBounds(20, 20, 200, 25);
        mainPanel.add(pharmacyLabel);
        
        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 50, 120, 25);
        mainPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 25);
        mainPanel.add(nameField);

        submitButton = new JButton("Add new medicine");
        submitButton.setBounds(20, 80, 180, 25);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	count++;
                addMedicine();
            }
        });
        mainPanel.add(submitButton);
        
        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(200, 80, 120, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new LandingPage().initializeLandingPage();
            }
        });
        mainPanel.add(backButton);
        
        mainFrame.add(mainPanel);
        mainFrame.setSize(1000, 800);
        mainFrame.setVisible(true);
 	}
 	
    private void addMedicine() {
    	if (nameField.getText().equals("")) {
    		JOptionPane.showMessageDialog(null, "Please enter a value");
    	}
    	else {
        	setMedicineID("M" + Integer.toString(count));
        	setMedicineName(nameField.getText());
        	availableMedications.add(medicineName);
        	
        	JOptionPane.showMessageDialog(null, "New medicine added to the pharmacy");
        	mainFrame.dispose();
            new LandingPage().initializeLandingPage();
    	}
    }
}
