// ListCommand.java
import java.util.List;

public class ListCommand implements Command {
    private List<Customer> customerData;
    private SortStrategy sortStrategy;

    public ListCommand(List<Customer> customerData, SortStrategy sortStrategy) {
        this.customerData = customerData;
        this.sortStrategy = sortStrategy;
    }

    @Override
    public void execute() {
        if (sortStrategy != null) {
            sortStrategy.sort(customerData);

            // Display the sorted list
            for (Customer customer : customerData) {
                System.out.println(customer);
            }
        } else {
            System.out.println("List of Customers (No Sorting):");
            for (Customer customer : customerData) {
                System.out.println(customer);
            }
        }

        System.out.println("------------------");
    }
}


