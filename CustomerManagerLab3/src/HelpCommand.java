class HelpCommand implements Command {
	
    private String additionalInfo;
	
    public HelpCommand(String additionalInfo) {
    	this.additionalInfo = additionalInfo;
    }
    
    @Override
    public void execute() {
        displayHelp();
    }

    private void displayHelp() {
        System.out.println("Help Menu");
        System.out.println("1. List Customers: Display all customers.");
        System.out.println("2. Add Customer: Add a new customer.");
        System.out.println("3. Delete Customer: Delete a customer by name.");
        System.out.println("4. Help: Display this help menu.");
        System.out.println("5. Exit: Exit the application.");
        System.out.println("-------------------------");
    }
}
