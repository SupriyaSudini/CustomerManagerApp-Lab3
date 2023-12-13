import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.*;
import java.util.InputMismatchException;

public class CustomerManagerApp {

    private static final String FILE_NAME = "src/customer_data.txt";
    private static CustomerManagerApp instance; // Singleton instance

    private List<Customer> customerData;
    private CommandInvoker commandInvoker;
    private Scanner scanner;

    // Private constructor to prevent 
    //instantiation outside the class
    private CustomerManagerApp() {
        customerData = loadCustomerDataFromFile();
        commandInvoker = new CommandInvoker();
        scanner = new Scanner(System.in);
    }

    // Static method to obtain the Singleton instance
    public static CustomerManagerApp getInstance() {
        if (instance == null) {
            instance = new CustomerManagerApp();
        }
        return instance;
    }

    public void run() {
        while (true) {
            System.out.println("Customer Manager Console Application");
            System.out.println("1. List Customers");
            System.out.println("2. Add Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. Help");
            System.out.println("5. Exit");
            System.out.println("6. Search a Customer");
            System.out.println("7. Sort a Customer");
            System.out.println("------------------");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {

                case 1:
                    // No default sorting strategy, list in the original order
                    Command listCommand = new ListCommand(customerData, null);
                    commandInvoker.executeCommand(listCommand);
                    break;



                    case 2:
                        String name;
                        do {
                            System.out.print("Enter customer name: ");
                            name = scanner.nextLine();
                        } while (!isValidName(name));

                        String email;
                        do {
                            System.out.print("Enter customer email: ");
                            email = scanner.nextLine();
                        } while (!isValidEmail(email));

                        String phone;
                        do {
                            System.out.print("Enter customer phone: ");
                            phone = scanner.nextLine();
                        } while (!isValidPhone(phone));

                        Customer newCustomer = new Customer(name, email, phone);
                        Command addCommand = new AddCommand(customerData, newCustomer, FILE_NAME);
                        commandInvoker.executeCommand(addCommand);
                        break;
                    case 3:
                        System.out.print("Enter customer ID to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        Command deleteCommand = new DeleteCommand(customerData, deleteId, FILE_NAME);
                        commandInvoker.executeCommand(deleteCommand);
                        break;
                    case 4:
                        System.out.print("Enter additional information (or press Enter to skip): ");
                        String additionalInfo = scanner.nextLine();
                        Command helpCommand = new HelpCommand(additionalInfo);
                        commandInvoker.executeCommand(helpCommand);
                        break;
//                    case 5:
//                        Command exitCommand = new ExitCommand();
//                        commandInvoker.executeCommand(exitCommand);
//                        break;
                    case 5:
                        Command exitCommand = new ExitCommand(customerData, FILE_NAME);
                        commandInvoker.executeCommand(exitCommand);
                        break;

                    case 6:
                        System.out.print("Enter search term(name / email / phone ): ");
                        String searchTerm = scanner.nextLine();
                        Command searchCommand = new SearchCommand(customerData, searchTerm);
                        commandInvoker.executeCommand(searchCommand);
                        break;

                    case 7:
                        System.out.println("Sort Options:");
                        System.out.println("1. Sort in Ascending Order");
                        System.out.println("2. Sort in Descending Order");
                        System.out.print("Enter your choice: ");

                        int sortChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        SortStrategy sortStrategy = null;
                        if (sortChoice == 1) {
                            sortStrategy = new AscendingSortStrategy();
                        } else if (sortChoice == 2) {
                            sortStrategy = new DescendingSortStrategy();
                        }

                        if (sortStrategy != null) {
                            Command ListCommand = new ListCommand(customerData, sortStrategy);
                            commandInvoker.executeCommand(ListCommand);
                        } else {
                            System.err.println("Invalid choice for sorting. Please try again.");
                        }
                        break;


                    default:
                        System.err.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
            }
        }
    }

    private List<Customer> loadCustomerDataFromFile() {
        List<Customer> customerData = new ArrayList<>();
        File file = new File(FILE_NAME);

        int lastUsedId = 0; 
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    lastUsedId = Math.max(lastUsedId, id);
                    String name = parts[1];
                    String email = parts[2];
                    String phone = parts[3];
                    customerData.add(new Customer(id, name, email, phone));
                }
            }
            Customer.setLastUsedId(lastUsedId); 
        } catch (IOException e) {
            System.err.println("Error loading customer data from file: " + e.getMessage());
        }
//        Customer.setLastUsedId(lastUsedId); 
        return customerData;
    }


    private boolean isValidName(String name) {
        boolean isValid = name.matches("^[a-zA-Z\\s]+$");

        if (!isValid) {
            System.err.println("Invalid name format. Please enter a name without numbers or special characters.");
        }

        return isValid;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean isValid = email.matches(emailRegex);

        if (!isValid) {
            System.err.println("Invalid email format. Please enter a valid email address.");
        }

        return isValid;
    }

    private boolean isValidPhone(String phone) {
        boolean isValid = phone.matches("\\d{10}");

        if (!isValid) {
            System.err.println("Invalid phone number format. Please enter a 10-digit phone number.");
        }

        return isValid;
    }

    public static void main(String[] args) {
        CustomerManagerApp.getInstance().run();
    }
}
