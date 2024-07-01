package project_2;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.*;

public class LandingPage extends Hospital {
	
    // GUI Screen
    private JFrame mainFrame;
    private JButton roomsButton;
    private JButton staffButton;
    private JButton equipmentButton;
    private JButton pharmacyButton;
    private JButton patientsButton;
    private JButton roomCountButton;
    private JButton staffCountButton;
    private JButton equipmentCountButton;
    private JButton patientCountButton;
    private JButton exitButton;
    
    final void initializeLandingPage() {
    	
    	mainFrame = new JFrame(Hospital.hospitalName + " Management System");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        // Rooms button
        roomsButton = new JButton("Rooms");
        roomsButton.setBounds(300, 50, 180, 25);
        roomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainFrame.dispose();
                new HospitalRoom().initializeRoomsPage(); // Rooms page
            }
        });
        mainPanel.add(roomsButton); 
        
        // Staff button
        staffButton = new JButton("Staff");
        staffButton.setBounds(300, 100, 180, 25);
        staffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainFrame.dispose();
                new HospitalStaff().displayStaffPane(); // Staff page
            }
        });
        mainPanel.add(staffButton);
        
        // Equipment button
        equipmentButton = new JButton("Equipment");
        equipmentButton.setBounds(300, 150, 180, 25);
        equipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainFrame.dispose();
                new HospitalEquipment().displayEquipmentPane(); // Equipment page
            }
        });
        mainPanel.add(equipmentButton);
        
        // Pharmacy button
        pharmacyButton = new JButton("Pharmacy");
        pharmacyButton.setBounds(300, 200, 180, 25);
        pharmacyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainFrame.dispose();
            	new HospitalPharmacy().displayPharmacyPane(); // Pharmacy page
            }
        });
        mainPanel.add(pharmacyButton);
        
        // Patients button
        patientsButton = new JButton("Patients");
        patientsButton.setBounds(300, 250, 180, 25);
        patientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mainFrame.dispose();
            	new Patient().displayPatientPane(); // Patients page
            }
        });
        mainPanel.add(patientsButton);
        
        // Room count button
        roomCountButton = new JButton("Rooms Count");
        roomCountButton.setBounds(20, 500, 120, 50);
        roomCountButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "Current number of rooms: " + Hospital.getCurrentHospital().rooms.size());	
        	}
        });
        mainPanel.add(roomCountButton);
        
        // Staff count button
        staffCountButton = new JButton("Staff Count");
        staffCountButton.setBounds(150, 500, 120, 50);
        staffCountButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "Current number of staff: " + Hospital.getCurrentHospital().staff.size());
        	}
        });
        mainPanel.add(staffCountButton);
        
        // Staff count button
        equipmentCountButton = new JButton("Equipment Count");
        equipmentCountButton.setBounds(280, 500, 150, 50);
        equipmentCountButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "Current number of equipment: " + Hospital.getCurrentHospital().equipment.size());
        	}
        });
        mainPanel.add(equipmentCountButton);
        
        // Patient count button
        patientCountButton = new JButton("Patient Count");
        patientCountButton.setBounds(440, 500, 150, 50);
        patientCountButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "Current number of patients: " + Patient.count);
        	}
        });
        mainPanel.add(patientCountButton);
        
        // Exit button
        exitButton = new JButton("Exit");
        exitButton.setBounds(670, 500, 120, 50);
        exitButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        mainPanel.add(exitButton);
        
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
}
