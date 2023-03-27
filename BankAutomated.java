import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class BankAutomated
{
    List<CA> customerAccounts = Collections.synchronizedList(new ArrayList<CA>());
    private final ConcurrentHashMap<String, CA> customerHash = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    ArrayList<AD> admins = new ArrayList<>();
    ArrayList<MT> maintenanceTeam = new ArrayList<>();
    ArrayList<CSR> customerService = new ArrayList<>();

    @SuppressWarnings("SpellCheckingInspection")
    public enum State {HOME, ACCOUNT, ETRANS, BANKTRANS, FUNDTRANS, MEETREQ, MAKEREP, LOCATE,
                        NOTIF, NOTIFSET, PRIVSET, EDITPROF, SETTINGS}

    /*
     * Constructor for BankAutomated to be used for the TestCase (for the JUnit test cases)
     * This prevents the people.ser file from interfering with the test case results
     */
    public BankAutomated(boolean Test){
        AD admin = new AD("Mister", "Admin", "admin@BCS.ca", "1234567890", 0);
        AD admin2 = new AD("Mister", "Admin2", "admin2@BCS.ca", "1112223333", 1);
        AD admin3 = new AD("Mister", "Admin3", "admin3@BCS.ca", "2223334444", 2);
        AD admin4 = new AD("Mister", "Admin4", "admin4@BCS.ca", "3334445555", 3);
        AD admin5 = new AD("Mister", "Admin5", "admin5@BCS.ca", "4445556666", 4);

        MT maintenance = new MT ("Mister", "Maintenance", "maintenance@BCS.ca", "1234567890", 0);
        MT maintenance2 = new MT ("Mister", "Maintenance2", "maintenance2@BCS.ca", "1123456789", 1);
        MT maintenance3 = new MT ("Mister", "Maintenance3", "maintenance3@BCS.ca", "1234567800", 2);
        MT maintenance4 = new MT ("Mister", "Maintenance4", "maintenance4@BCS.ca", "1234567901", 3);
        MT maintenance5 = new MT ("Mister", "Maintenance5", "maintenance5@BCS.ca", "1224567890", 4);

        CSR customerService1 = new CSR("Mister", "CustomerService2", "customerservice@BCS.ca", "123456789", 0);
        CSR customerService2 = new CSR("Mister", "CustomerService2", "customerservice2@BCS.ca", "121456789", 1);
        CSR customerService3 = new CSR("Mister", "CustomerService3", "customerservice3@BCS.ca", "112456789", 2);
        CSR customerService4 = new CSR("Mister", "CustomerService4", "customerservice4@BCS.ca", "123456889", 3);
        CSR customerService5 = new CSR("Mister", "CustomerService5", "customerservice5@BCS.ca", "123567899", 4);

        admins.add(admin); admins.add(admin2); admins.add(admin3); admins.add(admin4); admins.add(admin5);
        maintenanceTeam.add(maintenance); maintenanceTeam.add(maintenance2); maintenanceTeam.add(maintenance3);
        maintenanceTeam.add(maintenance4); maintenanceTeam.add(maintenance5);
        customerService.add(customerService1); customerService.add(customerService2); customerService.add(customerService3);
        customerService.add(customerService4); customerService.add(customerService5);
    }
    
    /*
     * Constructor for the BankAutomated class
     * NOTE: DO NOT TOUCH
     */
    public BankAutomated()
    {
        AD admin = new AD("Mister", "Admin", "admin@BCS.ca", "1234567890", 0);
        AD admin2 = new AD("Mister", "Admin2", "admin2@BCS.ca", "1112223333", 1);
        AD admin3 = new AD("Mister", "Admin3", "admin3@BCS.ca", "2223334444", 2);
        AD admin4 = new AD("Mister", "Admin4", "admin4@BCS.ca", "3334445555", 3);
        AD admin5 = new AD("Mister", "Admin5", "admin5@BCS.ca", "4445556666", 4);

        MT maintenance = new MT ("Mister", "Maintenance", "maintenance@BCS.ca", "1234567890", 0);
        MT maintenance2 = new MT ("Mister", "Maintenance2", "maintenance2@BCS.ca", "1123456789", 1);
        MT maintenance3 = new MT ("Mister", "Maintenance3", "maintenance3@BCS.ca", "1234567800", 2);
        MT maintenance4 = new MT ("Mister", "Maintenance4", "maintenance4@BCS.ca", "1234567901", 3);
        MT maintenance5 = new MT ("Mister", "Maintenance5", "maintenance5@BCS.ca", "1224567890", 4);

        CSR customerService1 = new CSR("Mister", "CustomerService2", "customerservice@BCS.ca", "123456789", 0);
        CSR customerService2 = new CSR("Mister", "CustomerService2", "customerservice2@BCS.ca", "121456789", 1);
        CSR customerService3 = new CSR("Mister", "CustomerService3", "customerservice3@BCS.ca", "112456789", 2);
        CSR customerService4 = new CSR("Mister", "CustomerService4", "customerservice4@BCS.ca", "123456889", 3);
        CSR customerService5 = new CSR("Mister", "CustomerService5", "customerservice5@BCS.ca", "123567899", 4);

        admins.add(admin); admins.add(admin2); admins.add(admin3); admins.add(admin4); admins.add(admin5);
        maintenanceTeam.add(maintenance); maintenanceTeam.add(maintenance2); maintenanceTeam.add(maintenance3);
        maintenanceTeam.add(maintenance4); maintenanceTeam.add(maintenance5);
        customerService.add(customerService1); customerService.add(customerService2); customerService.add(customerService3);
        customerService.add(customerService4); customerService.add(customerService5);

        System.out.println("Loading customer objects...");

        long startTime = System.currentTimeMillis();

        // Load customer account data from the "People.ser" serialized file
        try (FileInputStream accountsInput = new FileInputStream("People.ser");
             BufferedInputStream bufferedIn = new BufferedInputStream(accountsInput);
             ObjectInputStream accountObject = new ObjectInputStream(bufferedIn)) {

            // Read all the CA objects from the serialized file into a list
            List<CA> accounts = new ArrayList<>();
            while (true) {
                try {
                    CA account = (CA) accountObject.readObject();
                    accounts.add(account);
                } catch (EOFException ex) {
                    break;
                }
            }

            // Submit each account to the executor for processing
            List<Future<Void>> futures = new ArrayList<>();
            for (CA account : accounts) {
                futures.add(executor.submit(() -> {
                    customerAccounts.add(account);
                    customerHash.put(account.email, account);
                    return null;
                }));
            }

            // Wait for all tasks to complete and handle any exceptions
            futures.forEach(future -> {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            });

        } catch (FileNotFoundException ex) {
            // usually means nothing is inside
            System.out.println("File is empty");
        } catch (IOException ex) {
            // System.out.println("nothing inside");
            // usually means the file is corrupted or nothing inside
            // ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            // the CA object that was serialized was changed after it had been serialized
            clearPeopleFile();
        }

        long endTime = System.currentTimeMillis();
        double timePassedSeconds = (endTime - startTime) / 1000.0;

        System.out.println("Loaded " + customerAccounts.size() + " customer objects. In: " + timePassedSeconds + "s");
    }

    /* 
     * Clears the People.ser file
     * NOTE: This will delete all customer accounts DO NOT TOUCH
     * 
     */
    public void clearPeopleFile() {
        try {

            // Delete the file
            Files.deleteIfExists(Paths.get("People.ser"));
    
            // Create a new empty file
            Files.createFile(Paths.get("People.ser"));
            
            // Print a message
            System.out.println("Cleared People.ser file");

        // File doesn't exist
        } catch (NoSuchFileException ex) {
            System.out.println("People.ser file doesn't exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Checks if the email is valid
     * @param String email The email to check
     * @return boolean True if the email is valid, false otherwise
     * 
     */
    public boolean validEmail(String email)
    {
        if (email.contains("@"))
        {
            String username = email.substring(0, email.indexOf('@'));
            if (username.equals(""))
            {
                return false;
            }
        }
        return email.contains("@") && (email.contains(".com") || email.contains(".ca")) &&
                ( (email.indexOf(".com") - email.indexOf("@") >= 2) || (email.indexOf(".ca") - email.indexOf("@") >= 2) );
    }

    /*
     * Checks if the email is already in use
     * @param String email The email to check
     * @return boolean True if the email is already in use, false otherwise
     * 
     */
    public boolean existingEmail(String email)
    {
        return customerHash.containsKey(email);
    }

    /*
     * Checks if the password is valid
     * @param String password The password to check
     * @return boolean True if the password is valid, false otherwise
     * 
     */
    public boolean validPassword(String password)
    {
        if (password.length() < 8)
        {
            return false;
        }

        ArrayList<Character> specialChar = new ArrayList<>(
                Arrays.asList('!' , '"' , '#' , '$' , '%' , '&' , '\'' , '(' , ')' , '*' , '+' , '-' , '.' , '/' , ':' ,
                        ';' , '<' , '=' , '>' , '?' , '@' , '[' , '\\' , ']' , '^' , '_' , '`' , '{' , '|' , '}' , '~')
        );

        int lowerCharCount = 0; int upperCharCount = 0; int numCount = 0; int specialCount = 0;

        // Check if the password contains at least one of each type of character
        for (int i = 0; i < password.length(); i++)
        {
            char current = password.charAt(i);
            if (current >= 'A' && current <= 'Z')
            {
                upperCharCount++;
            }
            else if (current >= 'a' && current <= 'z')
            {
                lowerCharCount ++;
            }
            else if (current >= '0' && current <= '9')
            {
                numCount++;
            }
            else if (specialChar.contains(current))
            {
                specialCount++;
            }
        }
        return lowerCharCount > 0 && upperCharCount > 0 && numCount > 0 && specialCount > 0;
    }

    /*
     * Creates a new customer account
     * @param String email The email of the customer
     * @param String password The password of the customer
     * @return CA The customer account if it was created successfully, null otherwise
     * NOTE: DO NOT TOUCH THIS METHOD
     *
     */
    public CA loginAccount(String email, String password)
    {
        System.out.println("Logging in customer with email: " + email);

        // Multithreaded Stream
        CA customer = (CA) customerHash.get(email);
        if (customer != null && password.equals(customer.password)) {
            return customer;
        }

        System.out.println("Customer with email: " + email + " not found.");
        return null;
    }

    /*
     * Checks if the string contains numbers only
     * @param string str The string to check
     * @return boolean True if the string contains numbers only, false otherwise
     * 
     */
    public boolean onlyNumeric(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    /*
     * Checks if the string contains numbers only (allows up to one decimal point for doubles)
     * @param String str The string to check
     * @return boolean True if the string contains numbers only, false otherwise
     * 
     */
    public boolean onlyNumericDouble(String str) {
        int dotCount = 0;
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c == '.') {
                dotCount++;
                if (dotCount > 1) {
                    return false;
                }
            } else if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /*
     * Checks if the credit card number is valid
     * @param String ccv The credit card number
     * @return boolean True if the credit card number is valid, false otherwise
     *
     */
    public boolean validCVV(String cvv)
    {
        if (onlyNumeric(cvv))
        {
            return cvv.length() == 3 || cvv.length() == 4;
        }

        return false;
    }

    /*
     * Checks if the date of birth is valid
     * @param String month The month of the date of birth
     * @param String day The day of the date of birth
     * @param String year The year of the date of birth
     * @return boolean True if the date of birth is valid, false otherwise
     * 
     */
    public boolean validDOB(String month, String day, String year)
    {
        int numYear = Integer.parseInt(year);
        int numDay = Integer.parseInt(day);

        switch(month) {
            case "02":
                if (numYear % 4 == 0 && (numYear % 100 != 0 || numYear % 400 == 0)) {
                    return numDay <= 29;
                } else {
                    return numDay <= 28;
                }
            case "04":
            case "06":
            case "09":
            case "11":
                return numDay <= 30;
            default:
                return numDay <= 31;
        }
    }

    /*
     * Checks if the credit card number is valid
     * @param String cardNum The credit card number
     * @return boolean True if the card number is valid, false otherwise
     * 
     */
    public boolean validCard(String cardNum) {
        boolean valid = true;
    
        // Check length, starting digit, and only numeric
        if (!(cardNum.length() >= 13 && cardNum.length() <= 19 && onlyNumeric(cardNum) &&
            (cardNum.charAt(0) == '4' || cardNum.charAt(0) == '3' || cardNum.charAt(0) == '2' || cardNum.charAt(0) == '5'))) {
            valid = false;
        } else {
            int sum = 0;
            boolean alternate = false;
    
            // Luhn's algorithm
            for (int i = cardNum.length() - 1; i >= 0; i--) {
                int n = cardNum.charAt(i) - '0';
                if (alternate) {
                    n *= 2;
                    if (n > 9) {
                        n -= 9;
                    }
                }
                sum += n;
                alternate = !alternate;
            }
            valid = sum % 10 == 0;
        }
    
        return valid;
    }

    /*
     * This function creates a new customer account, and returns the new account if it was created successfully.
     * If the account was not created successfully, then it returns null.
     * The account is not created successfully if the email is already in use, if the email is not valid, if the password is not valid,
     * or if the card number is not valid.
     * @param String firstName The first name of the customer
     * @param String lastName The last name of the customer
     * @param String phoneNum The phone number of the customer
     * @param String address The address of the customer
     * @param String gender Gender of the customer
     * @param String dob Date of birth of the customer
     * @param String email Email of the customer
     * @param String password Password of the customer
     * @param String cardNum Card number of the customer
     * @param String cardExpiry Expiry date of the card
     * @param String cvv CVV of the card
     * @return CA The new customer account if it was created successfully, null otherwise
     * 
     */
    public CA createAccount(String firstName, String lastName, String phoneNum, String address, String gender, String dob,
                            String email, String password, String cardNum, String cardExpiry, String cvv)
    {

        // Check if the email is already in use, if the email is valid, if the password is valid, and if the card number is valid
        if (existingEmail(email) || !validEmail(email) || !validPassword(password) || !validCard(cardNum)) {
            return null;
        }

        // Create and return new CA object
        CA customer = new CA(firstName, lastName, phoneNum, address, gender, dob, email, password, cardNum, cardExpiry, cvv);

        // Add the new account to the customerAccounts list and customerHash map
        customerAccounts.add(customer);
        customerHash.put(email, customer);

        return customer;
    }

    /*
     * Adds a report to the customer's report list and the admin's report list
     * @param CA customer The customer who made the report
     * Returns the report created
     */
    public Report makeReport(CA customer)
    {
        // Get random admin that receives the report
        Random rand = new Random();
        int randIndex = rand.nextInt(admins.size()-1);
        AD admin = admins.get(randIndex);

        // Create a new report object
        Report report = new Report(customer, admin);

        // Add the report to the list of reports
        customer.addReport(report);
        admin.addCustomerReports(report);

        return report;
    }

    /*
     * This function allows a customer to make a request. It returns true if the request was made successfully, and false otherwise.
     * Allowing the customer to make a request about a technical issue, a maintenance issue, or a customer service issue.
     * @param CA customer The customer who made the request
     * @param String type The type of request
     * @return boolean True if the request was made successfully, false otherwise
     * 
     */
    public boolean makeRequest(CA customer, String type) {

        Request request;

        Random rand = new Random();
        int randIndex;

        switch(type){
            case "1": // Maintenance
                randIndex = rand.nextInt(maintenanceTeam.size()-1);
                MT maintenance = maintenanceTeam.get(randIndex);
                request = new Request(type, maintenance);
                maintenance.addSysRequest(request);
                break;
            case "2": // Technical
                randIndex = rand.nextInt(admins.size()-1);
                AD admin = admins.get(randIndex);
                request = new Request(type, admin);
                admin.addMeetingRequests(request);
                break;
            case "3": // Customer Service
                randIndex = rand.nextInt(customerService.size()-1);
                CSR csr = customerService.get(randIndex);
                request = new Request(type, csr);
                csr.addRequest(request);
                break;
            default:
                return false;
        }

        customer.addRequests(request);

        return true;

    }

    /*
     * Allows users to transfer money between their chequing and savings accounts
     * @param double transferAmount: amount of money to transfer
     * @param String fromAccount: account to transfer money from
     * @param CA customer: customer object
     * @return int 0 if transfer is successful, 1 if transfer is unsuccessful
     * 
     */
    public int transferFunds(double transferAmount, String fromAccount, CA customer) {

        // If the transfer amount is negative, then return 1
        if (fromAccount.equals("Chequing")) {

            if (transferAmount > customer.getChequing()) {

                return 1;

            }

            customer.setChequing(customer.getChequing() - transferAmount);
            customer.setSavings(customer.getSavings() + transferAmount);
            customer.addSaving(new Transaction("Chequing", "Savings", transferAmount,1));
            return 0;

        } else {

            if (transferAmount > customer.getSavings()) {

                return 1;
                
            }

            customer.setChequing(customer.getChequing() + transferAmount);
            customer.setSavings(customer.getSavings() - transferAmount);
            customer.addChequing(new Transaction("Chequing", "Savings", transferAmount,1));
            return 0;

        }

    }

    /*
     * Allows users to transfer money to other users of the bank (if they have an account)
     * @param double amount: amount of money to transfer
     * @param String receiverEmail: email of the receiver
     * @param CA customer: the customer who is sending the money
     * @param String accountFrom: the account the money is being sent from
     * @return int 0 if successful, 1 if receiver does not have an account, 2 if insufficient funds, 3 if amount is negative,
     * @return int 4 if amount is greater than 1000
     * 
     */
    public int etransfer(double amount, String receiverEmail, CA customer, String accountFrom) {

        CA receiverAccount = customerHash.get(receiverEmail);
        
        // Check if receiver has an account
        if (receiverAccount == null){

            return 1;

        }

        // Check if the sender has enough money to send
        if (accountFrom.equals("Chequing") && amount > customer.getChequing()) {

            return 2;

        // Check if the sender has enough money to send
        } else if (accountFrom.equals("Savings") && amount > customer.getSavings()) {

            return 2;

        // Check if the amount is negative
        } else if (amount <= 0) {

            return 3;

        // Check if the amount is greater than 1000
        } else if (amount > 1000) {

            return 4;

        // If all checks pass, transfer the money and receiver's account exists
        } else if (receiverAccount != null) {

            if (accountFrom.equals("Chequing")) {

                customer.setChequing(customer.getChequing() - amount);
                customer.addChequing(new Transaction(customer.email, receiverEmail, amount,1));
                receiverAccount.setChequing(receiverAccount.getChequing() + amount);
                receiverAccount.addChequing(new Transaction(customer.email, receiverEmail, amount,1));

            } else {

                customer.setSavings(customer.getSavings() - amount);
                customer.addSaving(new Transaction(customer.email, receiverEmail, amount,1));
                receiverAccount.setSavings(receiverAccount.getSavings() + amount);
                receiverAccount.addSaving(new Transaction(customer.email, receiverEmail, amount,1));

            }

        }

        return 0;
    }


    /*
     * This function allows users to transfer money to another user's account using their bank number
     * @param double amount: amount of money to transfer
     * @param String receiverAcc: bank number of the receiver
     * @param CA customer: the customer who is sending the money
     * @param String accountFrom: the account the money is being sent from
     * @return int 0 if successful, 1 if receiver does not have an account, 2 if insufficient funds, 3 if amount is negative,
     * @return int 4 if amount is greater than 1000
     * 
     */
    public int bankTransfer(double amount, String receiverAcc, CA customer, String accountFrom) {
        
        // Check if receiver account is valid
        if (receiverAcc.length() != 5) {

            return 1;

        // Check if customer has sufficient funds in chequing
        } else if (accountFrom.equals("Chequing") && amount > customer.getChequing()) {

            return 2;

        // Check if customer has sufficient funds in savings
        } else if (accountFrom.equals("Savings") && amount > customer.getSavings()) {

            return 2;

        }
    
        CA receiver = null;
    
        // Find the receiver account
        for (CA cust : customerAccounts) {
            //cust.print();
            System.out.println(cust.getBankNumber());

            if (cust.getBankNumber().equals(receiverAcc)) {

                receiver = cust;

            }
        }
    
        // Subtract the amount from the customer's account and add it to the receiver's account (chequing / savings)
        if (receiver != null){

            // Subtract the amount from the customer's account (chequing)
            if (accountFrom.equals("Chequing")) {

                customer.setChequing(customer.getChequing() - amount);
                customer.addChequing(new Transaction(customer.firstName + " " + customer.lastName, receiverAcc, amount,3));

            // Add the amount to the receiver's account (savings)
            } else if (accountFrom.equals("Savings")) {

                customer.setSavings(customer.getSavings() - amount);
                customer.addSaving(new Transaction(customer.firstName + " " + customer.lastName, receiverAcc, amount,4));

            }

            receiver.setChequing(receiver.getChequing() + amount);
            receiver.addChequing(new Transaction(customer.firstName + " " + customer.lastName, receiverAcc, amount,3));

            return 0;

        }

        // If the receiver account is not found, return 3
        return 3;

    }

    // Change the customer's details in the settings tab. Customers can change their address, email,
    // and phone number
    public void changeSettings(CA customer) {

    }

    /*
     * This method is used to return a list of addresses for the customer to 
     * choose from when visiting a branch.
     * 
     */
    public ArrayList<String> addresses() {
        ArrayList<String> locationList = new ArrayList<String>();

        locationList.add("10153 King George Blvd, Vancouver, BC");
        locationList.add("255 Yonge Street, Toronto, ON");
        locationList.add("2210 Bank Street, Ottawa, ON");
        locationList.add("1955 Chandler Road, New York City, NY");
        locationList.add("21 Lovecraft Lane, Montreal, QC");
        locationList.add("1965 Herbert Blvd, Halifax, NS");

        return locationList; 
    }

    /*
     * This method is used for logout. It is used to save the customer accounts to a file.
     * It will save the customer accounts to a file called People.ser, DO NOT TOUCH THIS METHOD.
     * 
     */
    public void logout() {

        long startTime = System.currentTimeMillis();

        System.out.println("Uploading customer objects");

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // Create a Callable that writes each customer account to the file
        Callable<Void> task = () -> {

            // Try-with-resources to automatically close the streams
            try (FileOutputStream accountsFile = new FileOutputStream("People.ser");
                
                // BufferedOutputStream is used to improve performance 
                BufferedOutputStream bufferedOut = new BufferedOutputStream(accountsFile);

                // ObjectOutputStream is used to write objects to the file
                ObjectOutputStream accountObject = new ObjectOutputStream(bufferedOut)) {

                // Write each customer account to the file
                customerAccounts.forEach(account -> {
                    try {
                        accountObject.writeObject(account);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                return null;

            // Catch any exceptions that may occur
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        };

        // Submit the task to the executor for each available processor
        List<Future<Void>> futures = new ArrayList<>();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            futures.add(executor.submit(task));
        }

        // Wait for all tasks to complete and shutdown the executor
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        });

        long endTime = System.currentTimeMillis();
        double timePassedSeconds = (endTime - startTime) / 1000.0;

        System.out.println("Uploaded " + customerAccounts.size() + " customer objects. In: " + timePassedSeconds + "s");

        // IMPORTANT: REMOVE THIS LATER THIS IS FOR RUNNING PURPOSES
        clearPeopleFile();

        executor.shutdown();

    }
}
