package project_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Patient extends HospitalRoom {
	
	// Data fields
	private String id;
	private String name;
	private int age;
	private String medicalCondition;
	protected static int count;

	// Constructors
	public Patient() {
		
	}
	
	public Patient(String id) {
		this.id = id;
	}
	
	public Patient(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Patient(String id, String name, int age, String medicalCondition) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.medicalCondition = medicalCondition;
	}
	
	// Set methods
	public void setID(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
	}
	
	// Get methods
	public String getID() {
	    return id;
	}

	public String getName() {
	    return name;
	}

	public int getAge() {
	    return this.age;
	}

	public String getMedicalCondition() {
	    return this.medicalCondition;
	}
	
	// GUI Screen
	private JFrame patientFrame;
	private JTextField patientIDField;
    private JTextField patientNameField;
    private JTextField patientAgeField;
    private JTextField patientConditionField;
    private JTextField searchPatientIDField;
    private JButton addPatientButton;
    private JButton backButton;
    private JButton findPatientButton;
    private JList<HospitalRoom> roomsList;
    
    final void displayPatientPane() {
        patientFrame = new JFrame("Hospital Management System");
        patientFrame.setSize(1000, 800);
        patientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // Title label
        JLabel patientLabel = new JLabel("New patient info:");
        patientLabel.setBounds(20, 20, 200, 25);
        mainPanel.add(patientLabel);
        
        // ID label and text field
        JLabel patientIDLabel = new JLabel("ID:");
        patientIDLabel.setBounds(20, 50, 200, 25);
        patientFrame.add(patientIDLabel);

        patientIDField = new JTextField();
        patientIDField.setBounds(150, 50, 200, 25);
        mainPanel.add(patientIDField);
        
        // Name label and text field
        JLabel patientNameLabel = new JLabel("Name:");
        patientNameLabel.setBounds(20, 80, 120, 25);
        patientFrame.add(patientNameLabel);

        patientNameField = new JTextField();
        patientNameField.setBounds(150, 80, 200, 25);
        mainPanel.add(patientNameField);

        // Age label and text field
        JLabel patientAgeLabel = new JLabel("Age:");
        patientAgeLabel.setBounds(20, 110, 120, 25);
        mainPanel.add(patientAgeLabel);

        patientAgeField = new JTextField();
        patientAgeField.setBounds(150, 110, 200, 25);
        mainPanel.add(patientAgeField);

        // Medical condition label and text field
        JLabel patientConditionLabel = new JLabel("Medical Condition:");
        patientConditionLabel.setBounds(20, 140, 120, 25);
        mainPanel.add(patientConditionLabel);

        patientConditionField = new JTextField();
        patientConditionField.setBounds(150, 140, 200, 25);
        mainPanel.add(patientConditionField);

        // JList for selecting a room
        JLabel roomsLabel = new JLabel("Select Room:");
        roomsLabel.setBounds(20, 170, 120, 25);
        mainPanel.add(roomsLabel);
        
        // Populating the list with rooms from the rooms list of Hospital class
        DefaultListModel<HospitalRoom> roomsListModel = new DefaultListModel<>();
        for (HospitalRoom room : getCurrentHospital().rooms) {
        	roomsListModel.addElement(room);
        }
        
        // Populating the Jlist with the elements of the roomsListModel
        roomsList = new JList<>(roomsListModel);
        roomsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(roomsList);
        scrollPane.setBounds(150, 170, 200, 100);
        mainPanel.add(scrollPane);

        // Add patient button
        addPatientButton = new JButton("Add Patient");
        addPatientButton.setBounds(20, 330, 180, 25);
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });
        mainPanel.add(addPatientButton);
        
        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(200, 330, 120, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                patientFrame.dispose();
                new LandingPage().initializeLandingPage();
            }
        });
        mainPanel.add(backButton);
        
        // Find patient button and label
        findPatientButton = new JButton("Find patient by ID");
        findPatientButton.setBounds(20, 600, 200, 25);
        findPatientButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		Hospital.getCurrentHospital().findPatient(Hospital.getCurrentHospital().getRooms(), searchPatientIDField.getText());
        	}
        });
        mainPanel.add(findPatientButton);
        
        searchPatientIDField = new JTextField();
        searchPatientIDField.setBounds(230, 600, 120, 25);
        mainPanel.add(searchPatientIDField);

        patientFrame.add(mainPanel);
        patientFrame.setSize(1000, 800);
        patientFrame.setVisible(true);
    }
    
    // Add patient button method
    private void addPatient() {
    	if (patientNameField.getText().equals("") || patientAgeField.getText().equals("") || patientConditionField.getText().equals("")) {
    		JOptionPane.showMessageDialog(null, "Please fill all the fields");
    	}
    	else {
        	Patient patient = new Patient();
            patient.setID(patientIDField.getText());
            patient.setName(patientNameField.getText());
            patient.setAge(Integer.parseInt(patientAgeField.getText()));
            patient.setMedicalCondition(patientConditionField.getText());
            
            int selectedIndex = roomsList.getSelectedIndex();
            
            if (selectedIndex != -1) {
            	HospitalRoom selectedRoom = roomsList.getModel().getElementAt(selectedIndex);
            	if (selectedRoom.getPatient1() == null) {
            		selectedRoom.setPatient1(patient);
            		JOptionPane.showMessageDialog(null, "New patient added to selected room");
            		count++;
            		patientFrame.dispose();
            		new LandingPage().initializeLandingPage();
            	}
            	else if (selectedRoom.getPatient2() == null) {
            		selectedRoom.setPatient2(patient);
            		JOptionPane.showMessageDialog(null, "New patient added to selected room");
            		count++;
            		patientFrame.dispose();
            		new LandingPage().initializeLandingPage();
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "Selected room is fully occupied");
            	}
            }
            else {
            	JOptionPane.showMessageDialog(null, "Please select a room from the list. If the list is empty, it means that no room has been created yet");
            }
    	}
    }
}
