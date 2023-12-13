
// AscendingSortStrategy.java
import java.util.List;

public class AscendingSortStrategy implements SortStrategy {
    @Override
    public void sort(List<Customer> customerData) {
        customerData.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    }
}

