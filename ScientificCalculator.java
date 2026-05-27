import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ScientificCalculator extends JFrame implements ActionListener {

// Create frame
private JFrame frame;
// Text field for displaying the result
private JTextField display;
// Create buttons for numbers, operators, and scientific functions
private JButton[] numberButtons;
private JButton[] operatorButtons;
private JButton[] scientificButtons;
// Button for clear (C) and equals (=)
private JButton clearButton, equalsButton;
// Store current input and result
private String currentInput = "";
private String lastOperator = "=";
private double result = 0.0;


public ScientificCalculator() {
// Frame settings
frame = new JFrame("Scientific Calculator by Ananya(2328069)");
frame.setSize(400, 500);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// Display settings
display = new JTextField();
display.setEditable(false);
display.setFont(new Font("Arial", Font.BOLD, 24));
display.setHorizontalAlignment(JTextField.RIGHT);
// Layout for the frame
frame.setLayout(new BorderLayout());
// Add display to the top
frame.add(display, BorderLayout.NORTH);
// Panel for buttons (adjusted layout)
JPanel panel = new JPanel();
panel.setLayout(new GridLayout(7, 5, 10, 10));
// Number buttons 0-9
numberButtons = new JButton[10];
for (int i = 0; i < 10; i++) {
numberButtons[i] = new JButton(String.valueOf(i));
numberButtons[i].addActionListener(this);
panel.add(numberButtons[i]);
}
// Operator buttons (+, -, *, /, %, etc.)
String[] operators = {"+", "-", "*", "/", "%", ".", "(", ")"};
operatorButtons = new JButton[operators.length];
for (int i = 0; i < operators.length; i++) {
operatorButtons[i] = new JButton(operators[i]);
operatorButtons[i].addActionListener(this);
panel.add(operatorButtons[i]);
}
// Scientific function buttons (sin, cos, tan, log, sqrt, exp)
String[] scientificFuncs = {"sin", "cos", "tan", "log", "sqrt", "exp"};
scientificButtons = new JButton[scientificFuncs.length];
for (int i = 0; i < scientificFuncs.length; i++) {
scientificButtons[i] = new JButton(scientificFuncs[i]);
scientificButtons[i].addActionListener(this);
panel.add(scientificButtons[i]);
}
// Clear button (C)
clearButton = new JButton("C");
clearButton.addActionListener(this);
panel.add(clearButton);
// Equals button (=)
equalsButton = new JButton("=");
equalsButton.addActionListener(this);
panel.add(equalsButton);
// Add the button panel to the frame
frame.add(panel, BorderLayout.CENTER);
// Make the frame visible
frame.setVisible(true);
}
@Override
public void actionPerformed(ActionEvent e) {
String command = e.getActionCommand();
// Handle button clicks
if (command.charAt(0) >= '0' && command.charAt(0) <= '9' ||
command.equals(".")) {
// If it's a number or decimal point, append to current input
currentInput += command;
display.setText(currentInput);
}
else if (command.equals("C")) {
// Clear current input and result
currentInput = "";
result = 0.0;
display.setText("");
}
else if (command.equals("=")) {
// Calculate result when '=' is pressed
calculateResult();
lastOperator = "=";
currentInput = String.valueOf(result);
display.setText(currentInput);
}
else {
// Handle scientific functions (sin, cos, tan, log, sqrt, exp)
if (command.equals("sin") || command.equals("cos") || command.equals("tan")
||
command.equals("log") || command.equals("sqrt") ||
command.equals("exp")) {
handleScientificFunction(command);
}
else {
// For operators like +, -, *, /
if (!currentInput.isEmpty()) {
calculateResult();
}
lastOperator = command;
currentInput = "";
}
}
}
private void calculateResult() {
try {
double input = Double.parseDouble(currentInput);
switch (lastOperator) {
case "+":
result += input;
break;
case "-":
result -= input;
break;
case "*":
result *= input;
break;
case "/":
if (input != 0) {
result /= input;
} else {
display.setText("Error");
return;
}
break;
case "%":
result %= input;
break;
case "=":
result = input;
break;
}
display.setText(String.valueOf(result));
} catch (Exception ex) {
display.setText("Error");
}
}
private void handleScientificFunction(String function) {
try {
double input = Double.parseDouble(currentInput);
switch (function) {
case "sin":
result = Math.sin(Math.toRadians(input));
break;
case "cos":
result = Math.cos(Math.toRadians(input));
break;
case "tan":
result = Math.tan(Math.toRadians(input));
break;
case "log":
result = Math.log10(input);
break;
case "sqrt":
result = Math.sqrt(input);
break;
case "exp":
result = Math.exp(input);
break;
default:
display.setText("Error");
return;
}
display.setText(String.valueOf(result));
currentInput = "";
} catch (Exception ex) {
display.setText("Error");
}
}

public static void main(String[] args) {
// Create and run the calculator
new ScientificCalculator();
}
}