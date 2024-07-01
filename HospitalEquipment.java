package project_2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HospitalEquipment extends Hospital {
	
	// Data fields
	private String id;
	private String name;
	private String manufacturer;
    private boolean isFunctional;
    private int count;
    
    // Constructors
    public HospitalEquipment() {
    	
    }
    
 	public HospitalEquipment(String id) {
 		this.id = id;
 	}
 	
 	public HospitalEquipment(String id, String name) {
 		this.id = id;
 		this.name = name;
 	}
 	
 	public HospitalEquipment(String id, String name, String manufacturer, boolean isFunctional) {
 		this.id = id;
 		this.name = name;
 		this.manufacturer = manufacturer;
 		this.isFunctional = isFunctional;
 	}
 	
 	// Set methods
 	public void setID(String id) {
 		this.id = id;
 	}
 	
 	public void setName(String name) {
 		this.name = name;
 	}
 	
 	public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
 	
 	public void setFunctional(boolean isFunctional) {
        this.isFunctional = isFunctional;
    }
 	
 	// Get methods
 	public String getID() {
 	    return id;
 	}

 	public String getName() {
 	    return name;
 	}

 	public String getManufacturer() {
        return this.manufacturer;
    }

 	public boolean isFunctional() {
        return this.isFunctional;
    }
 	
 	// GUI Screen
 	private JFrame mainFrame;
 	private JTextField nameField;
 	private JTextField manufacturerField;
 	private JTextField isFunctionalField;
 	private JButton submitButton;
 	private JButton backButton;
 	
 	final void displayEquipmentPane() {
    	mainFrame = new JFrame(Hospital.hospitalName + " Management System");
    	// staffFrame.setResizable(false);
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        JLabel eqLabel = new JLabel("New equipment info:");
        eqLabel.setBounds(20, 20, 200, 25);
        mainPanel.add(eqLabel);
        
        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 50, 120, 25);
        mainPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(280, 50, 200, 25);
        mainPanel.add(nameField);

        JLabel manufacturerLabel = new JLabel("Manufacturer:");
        manufacturerLabel.setBounds(20, 80, 120, 25);
        mainPanel.add(manufacturerLabel);

        manufacturerField = new JTextField();
        manufacturerField.setBounds(280, 80, 200, 25);
        mainPanel.add(manufacturerField);

        JLabel isFunctionalLabel = new JLabel("Is this equipment functional?(true/false):");
        isFunctionalLabel.setBounds(20, 110, 300, 25);
        mainPanel.add(isFunctionalLabel);

        isFunctionalField = new JTextField();
        isFunctionalField.setBounds(280, 110, 200, 25);
        mainPanel.add(isFunctionalField);

        submitButton = new JButton("Add new equipment");
        submitButton.setBounds(20, 140, 180, 25);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	count++;
                addEquipment();
            }
        });
        mainPanel.add(submitButton);
        
        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(200, 140, 120, 25);
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
 	
    private void addEquipment() {
    	if (nameField.getText().equals("") || manufacturerField.getText().equals("") || isFunctionalField.getText().equals("")) {
    		JOptionPane.showMessageDialog(null, "Please fill all the fields");
    	}
    	else {
        	HospitalEquipment equipment = new HospitalEquipment();
        	equipment.setID("E" + Integer.toString(count));
        	equipment.setName(nameField.getText());
        	equipment.setManufacturer(manufacturerField.getText());
        	if (!isFunctionalField.getText().equals("true")) {
        		if (!isFunctionalField.getText().equals("false")) {
        			JOptionPane.showMessageDialog(null, "Please enter a correct value for the functional field (write true or false)");
        		}
        		else if (isFunctionalField.getText().equals("false")) {
        			equipment.setFunctional(false);
        			// Add new equipment to Hospital class staff list
        	        Hospital.getCurrentHospital().addEquipment(equipment);
        	        JOptionPane.showMessageDialog(null, "New equipment added");
        	        mainFrame.dispose();
        	        new LandingPage().initializeLandingPage();
        		}
        	}
        	else if (isFunctionalField.getText().equals("true")) {
        		equipment.setFunctional(true);
        		// Add new equipment to Hospital class staff list
                Hospital.getCurrentHospital().addEquipment(equipment);
                JOptionPane.showMessageDialog(null, "New equipment added");
                mainFrame.dispose();
                new LandingPage().initializeLandingPage();
        	}
        }
    }
}

