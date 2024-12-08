package ePrescriptionApp;


	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;


	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	public class EPrescriptionApp extends JFrame {

	    // Panels
	    private JPanel panelDoctorInfo;
	    private JPanel panelMedicalHistory;
	    private JPanel panelGeneralNotes;
	    private JPanel panelPrescribedMedicines;
	    private JPanel panelLabExaminations;
	    private JPanel panelButtons;

	    // Text fields and areas for user input
	    private JTextArea medicalHistoryArea;
	    private JTextArea generalNotesArea;
	    private JTextArea prescribedMedicinesArea;

	    private JComboBox<String> labExamsCombo;
	    private JComboBox<String> medicinesCombo;

	    private JTextField doctorNameField;
	    private JTextField dateField;

	    // Buttons
	    private JButton saveButton, clearButton, submitButton;

	    public EPrescriptionApp() {
	        setTitle("E-Prescription System");
	        setSize(900, 800);  // Increased size for better visibility
	        setLayout(new BorderLayout());
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Initialize Panels
	        panelDoctorInfo = createDoctorInfoPanel();
	        panelMedicalHistory = createMedicalHistoryPanel();
	        panelGeneralNotes = createGeneralNotesPanel();
	        panelPrescribedMedicines = createPrescribedMedicinesPanel();
	        panelLabExaminations = createLabExaminationsPanel();
	        panelButtons = createButtonPanel();

	        // Main Panel using GridBagLayout
	        JPanel panelMain = new JPanel();
	        panelMain.setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();

	        // Set constraints and add the panels in a grid-like fashion
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        gbc.insets = new Insets(10, 10, 10, 10);
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.gridwidth = 1;
	        gbc.gridheight = 1;
	        panelMain.add(panelDoctorInfo, gbc);

	        gbc.gridy = 1;
	        panelMain.add(panelMedicalHistory, gbc);

	        gbc.gridy = 2;
	        panelMain.add(panelGeneralNotes, gbc);

	        gbc.gridy = 3;
	        panelMain.add(panelPrescribedMedicines, gbc);

	        gbc.gridy = 4;
	        panelMain.add(panelLabExaminations, gbc);

	        gbc.gridy = 5;
	        gbc.gridwidth = 2;
	        panelMain.add(panelButtons, gbc);

	        // Add the main panel to the frame
	        add(panelMain, BorderLayout.CENTER);

	        // Set visible
	        setVisible(true);
	    }

	    // Panel for Doctor's Name and Date
	    private JPanel createDoctorInfoPanel() {
	        JPanel panel = new JPanel();
	        panel.setLayout(new FlowLayout());

	        JLabel doctorLabel = new JLabel("Doctor's Name:");
	        doctorNameField = new JTextField(20);
	        doctorNameField.setText("Dr. John Doe");  // Default value for the doctor name

	        JLabel dateLabel = new JLabel("Date:");
	        dateField = new JTextField(15);
	        dateField.setText(getCurrentDate());  // Set current date by default
	        dateField.setEditable(false);  // Make the date field non-editable

	        panel.add(doctorLabel);
	        panel.add(doctorNameField);
	        panel.add(dateLabel);
	        panel.add(dateField);

	        return panel;
	    }

	    // Panel for Medical History
	    private JPanel createMedicalHistoryPanel() {
	        JPanel panel = new JPanel();
	        panel.setBorder(BorderFactory.createTitledBorder("Patient Medical History"));
	        panel.setLayout(new BorderLayout());

	        medicalHistoryArea = new JTextArea(5, 30);
	        JScrollPane scroll = new JScrollPane(medicalHistoryArea);
	        panel.add(scroll, BorderLayout.CENTER);

	        return panel;
	    }

	    // Panel for General Notes for the Doctor
	    private JPanel createGeneralNotesPanel() {
	        JPanel panel = new JPanel();
	        panel.setBorder(BorderFactory.createTitledBorder("General Notes for Doctor"));
	        panel.setLayout(new BorderLayout());

	        generalNotesArea = new JTextArea(5, 30);
	        JScrollPane scroll = new JScrollPane(generalNotesArea);
	        panel.add(scroll, BorderLayout.CENTER);

	        return panel;
	    }

	    // Panel for Prescribing Medicines
	    private JPanel createPrescribedMedicinesPanel() {
	        JPanel panel = new JPanel();
	        panel.setBorder(BorderFactory.createTitledBorder("Prescribed Medicines"));
	        panel.setLayout(new BorderLayout());

	        // JComboBox for selecting medicines
	        String[] medicines = {"Aspirin", "Ibuprofen", "Paracetamol", "Amoxicillin", "Ciprofloxacin"};
	        medicinesCombo = new JComboBox<>(medicines);

	        // JTextArea to display the selected medicine
	        prescribedMedicinesArea = new JTextArea(5, 30);
	        prescribedMedicinesArea.setEditable(false);  // Make text area non-editable for displaying medicines
	        JScrollPane scroll = new JScrollPane(prescribedMedicinesArea);

	        // Add action listener to update the JTextArea when a medicine is selected
	        medicinesCombo.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String selectedMedicine = (String) medicinesCombo.getSelectedItem();
	                prescribedMedicinesArea.append(selectedMedicine + "\n"); // Append selected medicine
	            }
	        });

	        // Add the combo box and text area to the panel
	        panel.add(medicinesCombo, BorderLayout.NORTH);
	        panel.add(scroll, BorderLayout.CENTER);

	        return panel;
	    }

	    // Panel for Selecting Lab Examinations
	    private JPanel createLabExaminationsPanel() {
	        JPanel panel = new JPanel();
	        panel.setBorder(BorderFactory.createTitledBorder("Select Lab Examinations"));
	        panel.setLayout(new BorderLayout());

	        String[] labExams = { "Blood Test", "X-Ray", "Ultrasound", "CT Scan", "MRI" };
	        labExamsCombo = new JComboBox<>(labExams);
	        panel.add(labExamsCombo, BorderLayout.CENTER);

	        return panel;
	    }

	    // Panel for Buttons (Save, Clear, Submit)
	    private JPanel createButtonPanel() {
	        JPanel panel = new JPanel();
	        panel.setLayout(new FlowLayout());

	        saveButton = new JButton("Save");
	        clearButton = new JButton("Clear");
	        submitButton = new JButton("Submit");

	        panel.add(saveButton);
	        panel.add(clearButton);
	        panel.add(submitButton);

	        // Action listeners for buttons
	        saveButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                savePrescription();
	            }
	        });

	        clearButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                clearFields();
	            }
	        });

	        submitButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                submitPrescription();
	            }
	        });

	        return panel;
	    }

	    // Method to Save the Prescription
	    private void savePrescription() {
	        String doctorName = doctorNameField.getText();
	        String date = dateField.getText();
	        String medicalHistory = medicalHistoryArea.getText();
	        String generalNotes = generalNotesArea.getText();
	        String prescribedMedicines = prescribedMedicinesArea.getText();
	        String labExamination = (String) labExamsCombo.getSelectedItem();

	        // Here, you would save the data to a database or file
	        JOptionPane.showMessageDialog(this, "Prescription Saved Successfully!");
	    }

	    // Method to Clear all fields
	    private void clearFields() {
	        doctorNameField.setText("");
	        medicalHistoryArea.setText("");
	        generalNotesArea.setText("");
	        prescribedMedicinesArea.setText("");
	        labExamsCombo.setSelectedIndex(0);
	        medicinesCombo.setSelectedIndex(0);
	    }

	    // Method to Submit the Prescription
	    private void submitPrescription() {
	        JOptionPane.showMessageDialog(this, "Prescription Submitted Successfully!");
	    }

	    // Method to get the current date
	    private String getCurrentDate() {
	        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date date = new Date();
	        return formatter.format(date);
	    }

	    public static void main(String[] args) {
	        new EPrescriptionApp();
	    }
	}

