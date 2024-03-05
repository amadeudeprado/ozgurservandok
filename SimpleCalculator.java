import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        // Create Scanner object to read input
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Simple Calculator!");

        // Ask the user to enter the first number
        System.out.print("Enter the first number: ");
        double firstNumber = input.nextDouble();

        // Ask the user to enter the second number
        System.out.print("Enter the second number: ");
        double secondNumber = input.nextDouble();

        // Ask the user to enter an operator (+, -, *, /)
        System.out.print("Enter an operator (+, -, *, /): ");
        char operator = input.next().charAt(0);

        // Close the scanner
        input.close();

        // Perform the operation and display the result
        switch (operator) {
            case '+':
                System.out.printf("Result: %.2f", (firstNumber + secondNumber));
                break;
            case '-':
                System.out.printf("Result: %.2f", (firstNumber - secondNumber));
                break;
            case '*':
                System.out.printf("Result: %.2f", (firstNumber * secondNumber));
                break;
            case '/':
                if (secondNumber != 0) {
                    System.out.printf("Result: %.2f", (firstNumber / secondNumber));
                } else {
                    System.out.println("Division by zero is not allowed.");
                }
                break;
            default:
                System.out.println("Invalid operator! Please use +, -, *, or /.");
                break;
        }
    }
}
