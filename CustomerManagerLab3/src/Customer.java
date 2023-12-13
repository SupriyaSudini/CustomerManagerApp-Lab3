public class Customer {
	 private static int idCounter = 1;  // Static counter for generating sequential IDs
	    private int id;
	    private String name;
	    private String email;
	    private String phone;

	    public Customer(String name, String email, String phone) {
	        this.id = idCounter++;  // Assign the current counter value as the ID and then increment the counter
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
	    }
	    
	    // Additional constructor with an explicit ID
	    public Customer(int id, String name, String email, String phone) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public boolean matchesSearchCriteria(String searchTerm) {
	        // Search criteria could be name, email, or phone number
	        return name.toLowerCase().contains(searchTerm.toLowerCase()) ||
	                email.toLowerCase().contains(searchTerm.toLowerCase()) ||
	                phone.contains(searchTerm);
	    }


    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Email: " + email + " | Phone: " + phone;
    }
    
 // Method to set the last used ID from the file
    public static void setLastUsedId(int lastUsedId) {
        idCounter = lastUsedId + 1;
    }
}
