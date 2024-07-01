package project_2;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.*;

public class HospitalRoom extends HospitalFloor {
	
	// Data fields
	private String id;
	private String name;
	private String roomType;
    private boolean isOccupied = false;
    private Patient patient1;
    private Patient patient2;
    
    // Constructors
    public HospitalRoom() {
        
    }
    
    public HospitalRoom(String id) {
 		this.id = id;
 	}
 	
 	public HospitalRoom(String id, String name, String roomType) {
 		this.id = id;
 		this.name = name;
 		this.roomType = roomType;
 	}
 	
 	public HospitalRoom(String id, String name, String roomType, Patient patient1, Patient patient2) {
 		this.id = id;
 		this.name = name;
 		this.roomType = roomType;
 		this.setPatient1(patient1);
 		this.patient2 = patient2;
 		isOccupied = true;
 	}
 	
 	// Set methods
 	public void setID(String id) {
 		this.id = id;
 	}
 	
 	public void setName(String name) {
 		this.name = name;
 	}
 	
 	public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
 	
 	public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
 	
 	public void setPatient1(Patient patient1) {
		this.patient1 = patient1;
		setOccupied(true);
	}
 	
 	public void setPatient2(Patient patient2) {
		this.patient2 = patient2;
		setOccupied(true);
	}
 	
 	// Get methods
 	public String getID() {
 	    return id;
 	}

 	public String getName() {
 	    return name;
 	}

 	public String getRoomType() {
        return this.roomType;
    }

 	public boolean isOccupied() {
        return this.isOccupied;
    }
 	
	public Patient getPatient1() {
		return patient1;
	}
	
	public Patient getPatient2() {
		return patient2;
	}
	
    // GUI Screen
    private JFrame mainFrame;
    private JTextField idField;
    private JTextField nameField;
    private JTextField typeField;
    private JButton submitButton;
    private JButton backButton;
    private JList<HospitalFloor> floorsList;
    
    final void initializeRoomsPage() {
        mainFrame = new JFrame(Hospital.hospitalName + " Management System");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with null layout
        JPanel mainPanel = new JPanel(null);

        // Room Section
        JLabel hospitalLabel = new JLabel("Room Information");
        hospitalLabel.setBounds(200, 10, 200, 25);
        mainPanel.add(hospitalLabel);

        // Room ID label and text field
        JLabel idLabel = new JLabel("Room ID:");
        idLabel.setBounds(50, 50, 120, 25);
        mainPanel.add(idLabel);
        idField = new JTextField();
        idField.setBounds(170, 50, 200, 25);
        mainPanel.add(idField);

        // Room Name label and text field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 120, 25);
        mainPanel.add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(170, 80, 200, 25);
        mainPanel.add(nameField);

        // Room Type label and text field
        JLabel typeLabel = new JLabel("Room type:");
        typeLabel.setBounds(50, 110, 120, 25);
        mainPanel.add(typeLabel);
        typeField = new JTextField();
        typeField.setBounds(170, 110, 200, 25);
        mainPanel.add(typeField);

        // Current Floors label
        JLabel label = new JLabel("Current Floors:");
        label.setBounds(50, 140, 120, 25);
        mainPanel.add(label);

        // Current Floors list
        DefaultListModel<HospitalFloor> floorListModel = new DefaultListModel<>();
        for (HospitalFloor floor : Hospital.getCurrentHospital().getFloors()) {
            floorListModel.addElement(floor);
        }
        floorsList = new JList<>(floorListModel);
        floorsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(floorsList);
        // scrollPane.setBounds(150, 170, 200, 100);
        scrollPane.setBounds(170, 145, 200, 100);
        mainPanel.add(scrollPane);

        // Submit button
        submitButton = new JButton("Add new room to selected floor");
        submitButton.setBounds(20, 300, 250, 25);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRoom();
            }
        });
        mainPanel.add(submitButton);
        
        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(280, 300, 120, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new LandingPage().initializeLandingPage();
            }
        });
        mainPanel.add(backButton);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    // Submit button method
    private void saveRoom() {
        HospitalRoom room = new HospitalRoom();
        room.setID(idField.getText());
        room.setName(nameField.getText());
        room.setRoomType(typeField.getText());

        // Initialising the hospital if its not initialised
        Hospital hospital = Hospital.getCurrentHospital();
        if (hospital == null) {
            JOptionPane.showMessageDialog(null, "Error: Hospital is not initialized.");
            return;
        }

        hospital.addRoom(room);

        // Check if a floor is selected
        int selectedIndex = floorsList.getSelectedIndex();

        if (selectedIndex != -1) {
            HospitalFloor selectedFloor = floorsList.getModel().getElementAt(selectedIndex);
            selectedFloor.addRoom(room);
     
            // Update the list model after changes
            DefaultListModel<HospitalFloor> floorListModel = (DefaultListModel<HospitalFloor>) floorsList.getModel();
            floorListModel.removeElement(selectedFloor);
            floorListModel.addElement(selectedFloor);

            // Clear text fields after saving
            idField.setText("");
            nameField.setText("");
            typeField.setText("");
            JOptionPane.showMessageDialog(null, "New room added.");
            mainFrame.dispose();
            new LandingPage().initializeLandingPage();
        } 
        else {
        	JOptionPane.showMessageDialog(null, "No floor selected. Please select a floor to add the room to.");
        }
    }
}
