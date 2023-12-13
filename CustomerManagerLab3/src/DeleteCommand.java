import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;

//Concrete Command for deleting a customer
class DeleteCommand implements Command {
 private List<Customer> customerData;
 private int customerId;
 private String fileName;

 public DeleteCommand(List<Customer> customerData, int customerId, String fileName) {
     this.customerData = customerData;
     this.customerId = customerId;
     this.fileName = fileName;
 }

 public void execute() {
     Iterator<Customer> iterator = customerData.iterator();
     while (iterator.hasNext()) {
         Customer customer = iterator.next();
         if (customer.getId() == customerId) {
             iterator.remove();
             updateFile();
             System.out.println("Customer deleted successfully.");
             System.out.println("------------------------");
             return;
         }
     }
     System.out.println("Customer not found.");
 }

 private void updateFile() {
     try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
         for (Customer customer : customerData) {
             writer.println(customer.getId()+ ","+customer.getName()+","+customer.getEmail()+","+customer.getPhone());
//        	 writer.println("ID: " + customer.getId() + " | Name: " + customer.getName()
//             + " | Email: " + customer.getEmail() + " | Phone: " + customer.getPhone());
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
