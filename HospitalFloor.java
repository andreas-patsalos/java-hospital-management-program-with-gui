package project_2;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class HospitalFloor extends Hospital {
	
	// Data fields
	private String id;
	private String name;
	private String type;
	protected List<HospitalRoom> rooms = new ArrayList<>();
	
	// Constructors
	public HospitalFloor() {
		this.rooms = new ArrayList<>();
	}
	
	public HospitalFloor(String id) {
		this.id = id;
	}
	
	public HospitalFloor(String id, String name) {
 		this.id = id;
 		this.name = name;
 	}
	
	public HospitalFloor(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rooms = new ArrayList<>();
    }
	
	// Set methods
	public void setID(String id) {
		this.id = id;
	 }
	 	
	public void setName(String name) {
		this.name = name;
	} 
	 
	public void setType(String type) {
		this.type = type;
	}
	 
	// Get methods
	public String getID() {
	    return id;
	}

	public String getName() {
	   return name;
	}
	
	public String getType() {
		return type;
	}
	
    public List<HospitalRoom> getRooms() {
        // if the rooms list is empty, return an empty array
        return rooms != null ? rooms : List.of();
    }
	
	@Override
	public String toString() {
	    return getName();
	}
	
    // Initialise the rooms list if the list is not initialised
    public void initializeRoomsList() {
        if (rooms == null) {
            rooms = new ArrayList<>();
        }
    }
    
    // Add room to the room list
    public void addRoom(HospitalRoom room) {
        initializeRoomsList();
        rooms.add(room);
    }
	
	// GUI Screen
	private JFrame mainFrame;
	private JTextField countField;
	private JTextField typeField;
	private JTextField nameField;
    private JButton addFloorsButton;
    private int count = 0;
    int size;
    
    final void displayFloorPane() {
    	mainFrame = new JFrame(Hospital.hospitalName + " Management System");
    	// staffFrame.setResizable(false);
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Count panel
    	JPanel countPanel = new JPanel();
    	countPanel.setLayout(null);
    	countPanel.setSize(500, 250);
    	
    	// Frame title
    	JLabel label1 = new JLabel("How many floors does the hospital building have?:");
    	label1.setBounds(20, 20, 350, 25);
    	countPanel.add(label1);
    	
    	// Floors label and text field
    	JLabel countLabel = new JLabel("Floors:");
    	countLabel.setBounds(20, 50, 120, 25);
    	countPanel.add(countLabel);
    	
    	countField = new JTextField();
    	countField.setBounds(70, 50, 50, 25);
    	countPanel.add(countField);
    	
    	// Submit floor count button
    	addFloorsButton = new JButton("Submit");
    	addFloorsButton.setBounds(20, 80, 180, 25);
    	addFloorsButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent e) {
                addFloors();
            }
        });
    	countPanel.add(addFloorsButton);
    	
    	mainFrame.add(countPanel);
    	mainFrame.setSize(600, 300);
    	mainFrame.setVisible(true);
    }
    
    // Submit floor count button method
    private void addFloors() {
    	if (countField.getText().equals("")) {
    		JOptionPane.showMessageDialog(null, "Please enter a value before clicking submit.");
    	}
        size = Integer.parseInt(countField.getText());
        if (count <= size) {
        	mainFrame.dispose();
        	displayFloorInfoPane(count);
        } 
    }
    
    // Submit floor info button method
    private void displayFloorInfoPane(int floorNum) {
    	count++;
    	mainFrame = new JFrame(Hospital.hospitalName + " Management System");
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Floor panel
    	JPanel mainPanel = new JPanel();
    	mainPanel.setLayout(null);
    	mainPanel.setSize(1000, 800);
    	
    	// Panel title
    	JLabel titleLabel = new JLabel("Enter info for floor " + count);
    	titleLabel.setBounds(20, 20, 350, 25);
    	mainPanel.add(titleLabel);
    	
    	// Name label and text field
    	JLabel nameLabel = new JLabel("Floor name:");
    	nameLabel.setBounds(20, 50, 120, 25);
    	mainPanel.add(nameLabel);
    	
    	nameField = new JTextField();
    	nameField.setBounds(150, 50, 200, 25);
    	mainPanel.add(nameField);
    	
    	// Type label and text field
    	JLabel typeLabel = new JLabel("Floor section type:");
    	typeLabel.setBounds(20, 80, 120, 25);
    	mainPanel.add(typeLabel);
    	
    	typeField = new JTextField();
    	typeField.setBounds(150, 80, 200, 25);
    	mainPanel.add(typeField);
    	
    	// Submit floor info button
    	addFloorsButton = new JButton("Submit");
    	addFloorsButton.setBounds(20, 140, 180, 25);
    	addFloorsButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent e) {
    			if (nameField.getText().equals("") || typeField.getText().equals("")) {
    				JOptionPane.showMessageDialog(null, "Please fill all the fields.");
    			}
    			else {
        			if (count <= size ) {
        	            saveFloorInfo();
        	        }
        	        if (count < size) {
        	        	mainFrame.dispose();
        	            displayFloorInfoPane(count);
        	        }
        	        else {
        	        	mainFrame.dispose();
        	        	new LandingPage().initializeLandingPage();
        	        }
    			}
    	    }
        });
    	mainPanel.add(addFloorsButton);
    	
    	mainFrame.add(mainPanel);
    	mainFrame.setSize(600, 300);
    	mainFrame.setVisible(true);
    }
    
    private void saveFloorInfo() {
    	HospitalFloor floor = new HospitalFloor();
    	floor.setID("F" + Integer.toString(count));
    	floor.setName(nameField.getText());
    	floor.setType(typeField.getText());
    	Hospital.getCurrentHospital().addFloor(floor);
    }
}
    	
    	
    	
    	
    	
  
