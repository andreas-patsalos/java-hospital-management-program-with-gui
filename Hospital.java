package project_2;

import java.io.Serializable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Hospital implements Serializable {

	// Data fields
	private String id;
	static String hospitalName;
	private int yearBuilt;
	private String address;
    protected List<HospitalFloor> floors;
    protected List<HospitalStaff> staff;
    protected List<HospitalRoom> rooms;
    private HospitalPharmacy pharmacy;
    protected List<HospitalEquipment> equipment;
    private static Hospital currentHospital;
	
	// Constructors
    public Hospital() {
    	
    }
    
    public Hospital(String id, String name) {
    	this.id = id;
    	hospitalName = name;
    }
    
    public Hospital(String id, String name, String address, int yearBuilt) {
    	this.id = id;
        hospitalName = name;
        this.address = address;
        this.floors = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.equipment = new ArrayList<>();
    }

    // Set methods
    public void setYearBuilt(int yearBuilt) {
    	this.yearBuilt = yearBuilt;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public void setPharmacy(HospitalPharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
    
    // Get methods
    public String getHospitalName() {
    	return hospitalName;
    }
    public int getYearBuilt() {
    	return yearBuilt;
    }
    
    public String getAddress() {
        return address;
    }
    
    public static Hospital getCurrentHospital() {
    	return currentHospital;
    }
    
    public List<HospitalFloor> getFloors() {
        return floors;
    }
    
    public List<HospitalStaff> getStaff() {
        return staff;
    }
    
    public List<HospitalRoom> getRooms() {
        return rooms;
    }
    
    public HospitalPharmacy getPharmacy() {
        return pharmacy;
    }
    
    public List<HospitalEquipment> getEquipment() {
        return equipment;
    }
    
    // Add to lists methods
    public void addFloor(HospitalFloor floor) {
    	initializeFloorsList();
        floors.add(floor);
    }

    public void addStaff(HospitalStaff staffMember) {
    	initializeStaffList();
        this.staff.add(staffMember);
    }

    public void addRoom(HospitalRoom room) {
        this.rooms.add(room);
    }

    public void addEquipment(HospitalEquipment equipmentItem) {
        this.equipment.add(equipmentItem);
    }
    
    // Initialise the floor list if the list is not initialised
    public void initializeFloorsList() {
        if (floors == null) {
            floors = new ArrayList<>();
        }
    }
    
    // Initialise the staff list if the list is not initialised
    public void initializeStaffList() {
    	if (staff == null) {
    		staff = new ArrayList<>();
    	}
    }
    
    // GUI Screen
    private JFrame mainFrame;
    private JTextField idField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField yearBuiltField;
    private JButton submitButton;
    
    final void initialize() {
    	
        mainFrame = new JFrame("Hospital Management System");
        mainFrame.setSize(1000, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // Hospital Section
        JLabel hospitalLabel = new JLabel("Hospital Information");
        hospitalLabel.setBounds(20, 20, 200, 25);
        mainPanel.add(hospitalLabel);
        
        // ID label and text field
        JLabel idLabel = new JLabel("National ID:");
        idLabel.setBounds(20, 50, 120, 25);
        mainPanel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(150, 50, 200, 25);
        mainPanel.add(idField);
        
        // Name label and text field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 80, 120, 25);
        mainPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 80, 200, 25);
        mainPanel.add(nameField);
        
        // Address label and text field
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 110, 120, 25);
        mainPanel.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(150, 110, 200, 25);
        mainPanel.add(addressField);
        
        // Year built label and text field
        JLabel yearBuiltLabel = new JLabel("Year built:");
        yearBuiltLabel.setBounds(20, 140, 200, 25);
        mainPanel.add(yearBuiltLabel);
        
        yearBuiltField = new JTextField();
        yearBuiltField.setBounds(150, 140, 200, 25);
        mainPanel.add(yearBuiltField);
        
        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setBounds(20, 170, 180, 25);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveHospital();
            }
        });
        mainPanel.add(submitButton);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
    
    // Submit button method
    private void saveHospital() {
    	if (idField.getText().equals("") || nameField.getText().equals("") || addressField.getText().equals("") || yearBuiltField.getText().equals("")) {
    		JOptionPane.showMessageDialog(null, "Please fill all the fields.");
    	}
    	else {
        	String id = idField.getText();
            String name = nameField.getText();
            String address = addressField.getText();
            int yearBuilt = Integer.parseInt(yearBuiltField.getText());

            currentHospital = new Hospital(id, name, address, yearBuilt);
            
            mainFrame.dispose();
            new HospitalFloor().displayFloorPane();
    	}
    }
    
    // findPatient method used in Patient class Find Patient button
    protected void findPatient(List<HospitalRoom> rooms, String targetID) {
        boolean patientFound = false;
       
        for (HospitalRoom room : getCurrentHospital().rooms) {
            if (room.getPatient1() != null && room.getPatient1().getID().equals(targetID)) {
                JOptionPane.showMessageDialog(null, "Patient with ID " + targetID + " found. Info:\nPatient name: " + room.getPatient1().getName() + "\nPatient age: " + room.getPatient1().getAge() + "\nReason for being hospitalised: " + room.getPatient1().getMedicalCondition() + "\nRoom: " + room.getName());
                patientFound = true;
                break;
            } else if (room.getPatient2() != null && room.getPatient2().getID().equals(targetID)) {
                JOptionPane.showMessageDialog(null, "Patient with ID " + targetID + " found. Info:\nPatient name: " + room.getPatient2().getName() + "\nPatient age: " + room.getPatient2().getAge() + "\nReason for being hospitalised: " + room.getPatient2().getMedicalCondition() + "\nRoom: " + room.getName());
                patientFound = true;
                break;
            }
        }
        if (!patientFound) {
            JOptionPane.showMessageDialog(null, "Patient with ID " + targetID + " does not exist in the records.");
        }
    }
}
