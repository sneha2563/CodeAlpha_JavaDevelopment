import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class Employee {
    private String name;
    private int leaveBalance;
    private boolean leaveRequested;
    private int requestedDays;

    public Employee(String name, int leaveBalance) {
        this.name = name;
        this.leaveBalance = leaveBalance;
        this.leaveRequested = false;
        this.requestedDays = 0;
    }

    public String getName() {
        return name;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void deductLeave(int days) {
        this.leaveBalance -= days;
    }

    public boolean isLeaveRequested() {
        return leaveRequested;
    }

    public int getRequestedDays() {
        return requestedDays;
    }

    public void requestLeave(int days) {
        this.leaveRequested = true;
        this.requestedDays = days;
    }

    public void clearLeaveRequest() {
        this.leaveRequested = false;
        this.requestedDays = 0;
    }
}

public class EmployeeLeaveManagementSystem {
    private JFrame frame;
    private JTextArea displayArea;
    private Map<String, Employee> employees;

    public EmployeeLeaveManagementSystem() {
        employees = new HashMap<>();
        employees.put("E001", new Employee("John Doe", 20));
        employees.put("E002", new Employee("Jane Smith", 15));
        employees.put("E003", new Employee("Alice Johnson", 25));
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Employee Leave Management System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        JButton requestLeaveButton = new JButton("Request Leave");
        JButton approveLeaveButton = new JButton("Approve Leave");
        JButton viewLeaveBalanceButton = new JButton("View Leave Balances");

        requestLeaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestLeave();
            }
        });

        approveLeaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approveLeave();
            }
        });

        viewLeaveBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewLeaveBalances();
            }
        });

        buttonPanel.add(requestLeaveButton);
        buttonPanel.add(approveLeaveButton);
        buttonPanel.add(viewLeaveBalanceButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void requestLeave() {
        String employeeId = JOptionPane.showInputDialog(frame, "Enter Employee ID:");
        if (employeeId != null && employees.containsKey(employeeId)) {
            String daysStr = JOptionPane.showInputDialog(frame, "Enter number of leave days requested:");
            try {
                int days = Integer.parseInt(daysStr);
                Employee employee = employees.get(employeeId);
                if (days > employee.getLeaveBalance()) {
                    JOptionPane.showMessageDialog(frame, "Insufficient leave balance!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    employee.requestLeave(days);
                    JOptionPane.showMessageDialog(frame, "Leave request submitted for approval.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid number of days!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Employee not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void approveLeave() {
        StringBuilder sb = new StringBuilder("Pending Leave Requests:\n");
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            Employee employee = entry.getValue();
            if (employee.isLeaveRequested()) {
                sb.append("ID: ").append(entry.getKey())
                  .append(", Name: ").append(employee.getName())
                  .append(", Requested Days: ").append(employee.getRequestedDays())
                  .append(", Leave Balance: ").append(employee.getLeaveBalance())
                  .append("\n");
            }
        }

        String employeeId = JOptionPane.showInputDialog(frame, sb.toString() + "\nEnter Employee ID to approve leave:");
        if (employeeId != null && employees.containsKey(employeeId)) {
            Employee employee = employees.get(employeeId);
            if (employee.isLeaveRequested()) {
                int requestedDays = employee.getRequestedDays();
                if (requestedDays > employee.getLeaveBalance()) {
                    JOptionPane.showMessageDialog(frame, "Insufficient leave balance!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    employee.deductLeave(requestedDays);
                    employee.clearLeaveRequest();
                    JOptionPane.showMessageDialog(frame, "Leave approved successfully for " + employee.getName() + ".");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No pending leave request for this employee!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Employee not found or no pending leave requests!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewLeaveBalances() {
        StringBuilder sb = new StringBuilder("Employee Leave Balances:\n");
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            Employee employee = entry.getValue();
            sb.append("ID: ").append(entry.getKey())
              .append(", Name: ").append(employee.getName())
              .append(", Leave Balance: ").append(employee.getLeaveBalance())
              .append("\n");
        }
        displayArea.setText(sb.toString());
        JOptionPane.showMessageDialog(frame, "Leave balances have been updated to reflect approved leaves.");
    }

    public static void main(String[] args) {
        new EmployeeLeaveManagementSystem();
    }
}
