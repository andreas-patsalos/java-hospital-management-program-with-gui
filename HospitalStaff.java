package project_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HospitalStaff extends Hospital {
	
	// Data fields
	private String id;
	private String name;
	private String role;
	private int yearsOfExperience;
	private int count;
	
	// Constructors
	public HospitalStaff() {
		
	}
	
	public HospitalStaff(String id) {
		this.id = id;
	}
	
	public HospitalStaff(String id, String name) {
		this.id = id;
		this.name = name;
	}
		
	public HospitalStaff(String id, String name, String role, int yearsOfExperience) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.yearsOfExperience = yearsOfExperience;
	}
	
	// Set methods
	public void setID(String id) {
		this.id = id;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	
	// Get methods
	public String getID() {
	    return id;
	}

	public String getName() {
	    return name;
	}
	
	public String getRole() {
		return role;
	}
	
	public int getExperience() {
		return yearsOfExperience;
	}
	
	// GUI Screen
	private JFrame staffFrame;
    private JTextField nameField;
    private JTextField roleField;
    private JTextField experienceField;
    private JButton addStaffButton;
    private JButton backButton;
    
    final void displayStaffPane() {
    	staffFrame = new JFrame(Hospital.hospitalName + " Management System");
    	// staffFrame.setResizable(false);
    	staffFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        JLabel staffLabel = new JLabel("New staff info:");
        staffLabel.setBounds(20, 20, 200, 25);
        mainPanel.add(staffLabel);
        
        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 50, 120, 25);
        mainPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 25);
        mainPanel.add(nameField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(20, 80, 120, 25);
        mainPanel.add(roleLabel);

        roleField = new JTextField();
        roleField.setBounds(150, 80, 200, 25);
        mainPanel.add(roleField);

        JLabel experienceLabel = new JLabel("Experience:");
        experienceLabel.setBounds(20, 110, 200, 25);
        mainPanel.add(experienceLabel);

        experienceField = new JTextField();
        experienceField.setBounds(150, 110, 200, 25);
        mainPanel.add(experienceField);
        
        // Add staff button
        addStaffButton = new JButton("Add new staff");
        addStaffButton.setBounds(20, 140, 180, 25);
        addStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	count++;
                addStaff();
            }
        });
        mainPanel.add(addStaffButton);
        
        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(200, 140, 120, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffFrame.dispose();
                new LandingPage().initializeLandingPage();
            }
        });
        mainPanel.add(backButton);
        
        staffFrame.add(mainPanel);
        staffFrame.setSize(1000, 800);
        staffFrame.setVisible(true);
    }
    
    // Add new staff button method
    private void addStaff() {
    	if (nameField.getText().equals("") || roleField.getText().equals("") || experienceField.getText().equals("")) {
    		JOptionPane.showMessageDialog(null, "Please fill all the fields.");
    	}
    	else {
        	HospitalStaff staff = new HospitalStaff();
        	staff.setID("S" + Integer.toString(count));
        	staff.setName(nameField.getText());
        	staff.setRole(roleField.getText());
        	staff.setExperience(Integer.parseInt(experienceField.getText()));
        	
        	// Add new staff to Hospital class staff list
        	Hospital.getCurrentHospital().addStaff(staff);
        	JOptionPane.showMessageDialog(null, "New staff added.");
        	staffFrame.dispose();
            new LandingPage().initializeLandingPage();
    	}
    }
}


