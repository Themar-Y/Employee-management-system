/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package datastructproject;
import datastructproject.FinalProjectDS.NODE;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

//Themar yassir 2210456
//Hadeel Al-mokhtar  2210414
public class EmployeeManagementGUI extends JFrame implements ActionListener {

  
  private JLabel idLabel, nameLabel, dayLabel, phoneLabel, addressLabel, hrLabel, salaryLabel;
  private JTextField idField, nameField, dayField, phoneField, addressField, hrField, salaryField;
  private JButton insertBtn, deleteBtn, showBtn, searchBtn, updateSalaryBtn, updateInfoBtn;
  private JTextArea employeeDetailsArea;

 
  private FinalProjectDS emp = new FinalProjectDS();

  public EmployeeManagementGUI() {
    super("Employee Management System"); // Set window title

  
    idLabel = new JLabel("Employee ID [ 5 numbers ] :");
    nameLabel = new JLabel("Name:");
    dayLabel = new JLabel("First Day of Work:");
    phoneLabel = new JLabel("Phone Number [ length 10 numbers ] :");
    addressLabel = new JLabel("Address:");
    hrLabel = new JLabel("Work Hours [ at least 32 hr ] : ");
    salaryLabel = new JLabel("Salary:");

    idField = new JTextField(10);
    nameField = new JTextField(20);
    dayField = new JTextField(15);
    phoneField = new JTextField(10);
    addressField = new JTextField(25);
    hrField = new JTextField(5);
    salaryField = new JTextField(10);

    insertBtn = new JButton("Insert Employee");
    deleteBtn = new JButton("Delete Employee");
    showBtn = new JButton("Show Employees");
    searchBtn = new JButton("Search Employee");
    updateSalaryBtn = new JButton("Update Salary");
    updateInfoBtn = new JButton("Update Info");

    employeeDetailsArea = new JTextArea(10, 30);
    employeeDetailsArea.setEditable(false);

    
    JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 5));
    inputPanel.add(idLabel);
    inputPanel.add(idField);
    inputPanel.add(nameLabel);
    inputPanel.add(nameField);
    inputPanel.add(dayLabel);
    inputPanel.add(dayField);
    inputPanel.add(phoneLabel);
    inputPanel.add(phoneField);
    inputPanel.add(addressLabel);
    inputPanel.add(addressField);
    inputPanel.add(hrLabel);
    inputPanel.add(hrField);
    inputPanel.add(salaryLabel);
    inputPanel.add(salaryField);

    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(insertBtn);
    buttonPanel.add(deleteBtn);
    buttonPanel.add(showBtn);
    buttonPanel.add(searchBtn);
    buttonPanel.add(updateSalaryBtn);
   buttonPanel.add(updateInfoBtn);

   
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(inputPanel, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    getContentPane().add(new JScrollPane(employeeDetailsArea), BorderLayout.EAST);

   
    insertBtn.addActionListener(this);
    deleteBtn.addActionListener(this);
    showBtn.addActionListener(this);
    searchBtn.addActionListener(this);
    updateSalaryBtn.addActionListener(this);
    updateInfoBtn.addActionListener(this);

  
    pack();
    setLocationRelativeTo(null); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == insertBtn) {
      try {
        long id = Long.parseLong(idField.getText());
        String name = nameField.getText();
        String day = dayField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        double hr = Double.parseDouble(hrField.getText());
        double salary = Double.parseDouble(salaryField.getText());
   

       
        if (String.valueOf(id).length() != 5) {
          throw new NumberFormatException("Employee ID must be 5 digits long.");
        } else if (hr < 32) {

  throw new NumberFormatException("Work hours must be at least 32.");
}else if (phone.length() !=10) {

  throw new NumberFormatException("phone number must be 10 numbers.");}

        if (!emp.checkID(id)) {
  emp.insertemp(id, day, address, name, hr, phone, salary);
  clearInputFields(); 
  employeeDetailsArea.setText("Employee inserted successfully!");
} else {
  employeeDetailsArea.setText("Error: ID already exists. Please enter a unique ID.");
   clearInputFields();
}
     
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    } else if (e.getSource() == deleteBtn) {
      try {
        long id = Long.parseLong(idField.getText());
        if (emp.deletemp(id) == 0) {
          employeeDetailsArea.setText("Employee deleted successfully!");
          clearInputFields();
        } else {
          employeeDetailsArea.setText("Employee with ID " + id + " doesn't exist.");
        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid input: please Enter Employee ID " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    } else if (e.getSource() == showBtn) {
      employeeDetailsArea.setText(emp.showemployees());
    } else if (e.getSource() == searchBtn) {
      try {
        long id = Long.parseLong(idField.getText());
        NODE temp = emp.searching(id);
        if (temp != null) {
          employeeDetailsArea.setText("Employee Details:\n" +
              "ID: " + temp.ID + "\n" +
              "Name: " + temp.em_name + "\n" +
              "Phone Number: " + temp.phone_num + "\n" +
              "Address: " + temp.add + "\n" +
              "Salary: " + temp.sal + "\n" +
              "First Day of Work: " + temp.Day + "\n" +
              "Work Hours: " + temp.hr);
        } else {
          employeeDetailsArea.setText("Employee with ID " + id + " doesn't exist.");
        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid input: please Enter Employee ID " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    } else if (e.getSource() == updateSalaryBtn) {
  try {
    long id = Long.parseLong(idField.getText());
    if (emp.checkID(id)) {
      emp.updateSal(id);
      employeeDetailsArea.setText("Salary updated for employee " + id);
      clearInputFields();
    } else {
      employeeDetailsArea.setText("Error: Employee with ID " + id + " not found.");
    }
  }catch (NumberFormatException ex) {
    employeeDetailsArea.setText("Error: Invalid ID format. Please enter a valid number.");
      } 
 
  }else if (e.getSource() == updateInfoBtn) {
  try {
    long id = Long.parseLong(idField.getText());

    
    if (!emp.checkID(id)) {
      employeeDetailsArea.setText("Error: Employee with ID " + id + " not found.");
      return; // Exit if ID doesn't exist
    }

    
    Map<String, Object> updates = new HashMap<>();
    if (!nameField.getText().isEmpty()) {
      updates.put("name", nameField.getText());
    }
    if (!dayField.getText().isEmpty()) {
      updates.put("day", dayField.getText());
    }
    if (!phoneField.getText().isEmpty()) {
      updates.put("phone", phoneField.getText());
    }
    if (!addressField.getText().isEmpty()) {
      updates.put("address", addressField.getText());
    }
    if (!hrField.getText().isEmpty()) {
      double hr = Double.parseDouble(hrField.getText());
      updates.put("hr", hr);
    }
    if (!salaryField.getText().isEmpty()) {
      double salary = Double.parseDouble(salaryField.getText());
      updates.put("salary", salary);
    }

  
    emp.updateEmployee(id, updates);

    employeeDetailsArea.setText("Employee information updated successfully!");
    clearInputFields();

  } catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  }
}
    
    
    
  }
  

 
  private void clearInputFields() {
    idField.setText("");
    nameField.setText("");
    dayField.setText("");
    phoneField.setText("");
    addressField.setText("");
    hrField.setText("");
    salaryField.setText("");
  }

  public static void main(String[] args) {
    EmployeeManagementGUI app = new EmployeeManagementGUI();
    app.setVisible(true);
  }
}