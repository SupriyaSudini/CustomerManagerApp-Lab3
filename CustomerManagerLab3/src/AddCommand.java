import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;


// Concrete Command for adding a customer
class AddCommand implements Command {
    private List<Customer> customerData;
    private Customer newCustomer;
    private String fileName;

    public AddCommand(List<Customer> customerData, Customer newCustomer, String fileName) {
        this.customerData = customerData;
        this.newCustomer = newCustomer;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        customerData.add(newCustomer);
        updateFile();
        System.out.println("Customer added successfully.");
        System.out.println("-------------------");
    }
    
    private void updateFile() {
    	try(PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))){
      	   writer.println(newCustomer.getId()+","+newCustomer.getName()+","+newCustomer.getEmail()+","+newCustomer.getPhone());
//    		 writer.println("ID: " + newCustomer.getId() + " | Name: " + newCustomer.getName() +
//                     " | Email: " + newCustomer.getEmail() + " | Phone: " + newCustomer.getPhone());
//    	   
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
}
