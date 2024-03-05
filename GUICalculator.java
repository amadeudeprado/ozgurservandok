import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICalculator extends JFrame implements ActionListener {
    private final JTextField textField;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private char operator;

    // Constructor to setup GUI components and event handlers
    public GUICalculator() {
        // Set the frame properties
        setTitle("GUI Calculator");
        setSize(400, 400);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a display field
        textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField, BorderLayout.NORTH);

        // Create a panel with a grid layout for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5)); // 4x4 grid with 5px margins

        // Array of button labels
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        // Create and add buttons
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            panel.add(button);
        }

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);
    }

    // The main method to run the calculator
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUICalculator calculator = new GUICalculator();
            calculator.setVisible(true);
        });
    }

    // Handling button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            textField.setText(textField.getText() + command);
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            firstNumber = Double.parseDouble(textField.getText());
            operator = command.charAt(0);
            textField.setText("");
        } else if (command.equals("=")) {
            secondNumber = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    textField.setText((firstNumber + secondNumber) + "");
                    break;
                case '-':
                    textField.setText((firstNumber - secondNumber) + "");
                    break;
                case '*':
                    textField.setText((firstNumber * secondNumber) + "");
                    break;
                case '/':
                    textField.setText(secondNumber != 0 ? (firstNumber / secondNumber) + "" : "Error");
                    break;
            }
        } else if (command.equals("C")) {
            textField.setText("");
        }
    }
}
