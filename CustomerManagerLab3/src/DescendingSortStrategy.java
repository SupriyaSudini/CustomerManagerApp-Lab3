// DescendingSortStrategy.java
import java.util.List;

public class DescendingSortStrategy implements SortStrategy {
    @Override
    public void sort(List<Customer> customerData) {
        customerData.sort((c1, c2) -> c2.getName().compareToIgnoreCase(c1.getName()));
    }
}

