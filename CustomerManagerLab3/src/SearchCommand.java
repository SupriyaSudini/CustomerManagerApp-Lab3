import java.util.List;

public class SearchCommand implements Command {
    private List<Customer> customerData;
    private String searchTerm;

    public SearchCommand(List<Customer> customerData, String searchTerm) {
        this.customerData = customerData;
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute() {
        System.out.println("Search Results:");
        boolean found = false;
        for (Customer customer : customerData) {
            if (customer.matchesSearchCriteria(searchTerm)) {
                System.out.println(customer);
                found = true;
            }
        }
        if (!found) {
            System.err.println("No matching customers found.");
        }
        System.out.println("------------------");
    }
}
