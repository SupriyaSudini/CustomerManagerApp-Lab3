
// ExitCommand class
//class ExitCommand implements Command {
//    @Override
//    public void execute() {
//        System.out.println("Exiting the application.");
//        System.exit(0);
//    }
//}
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

// ExitCommand class
class ExitCommand implements Command {
    private List<Customer> customerData;
    private String fileName;

    public ExitCommand(List<Customer> customerData, String fileName) {
        this.customerData = customerData;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        if (hasUnsavedChanges()) {
            System.out.println("You have unsaved changes. Do you really want to exit? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("yes")) {
                saveChanges();
                System.out.println("Exiting the application.");
                System.exit(0);
            } else {
                System.out.println("Exiting canceled. Your changes have not been saved.");
            }
        } else {
            System.out.println("No changes. Exiting the application.");
            System.exit(0);
        }
    }

    private boolean hasUnsavedChanges() {
        List<Customer> fileData = loadCustomerDataFromFile();
        return !customerData.equals(fileData);
    }

    private void saveChanges() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Customer customer : customerData) {
                writer.println(customer.getId() + "," + customer.getName() + "," + customer.getEmail() + "," + customer.getPhone());
            }
            System.out.println("Changes saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving changes to the file.");
        }
    }

    private List<Customer> loadCustomerDataFromFile() {
        List<Customer> fileData = new ArrayList<>();
        File file = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String email = parts[2];
                    String phone = parts[3];
                    fileData.add(new Customer(id, name, email, phone));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading customer data from file.");
        }

        return fileData;
    }
}
