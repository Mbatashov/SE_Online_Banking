import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.time.LocalDate;

/**
 * BankAutomated class handles all the logic and authentication in the system using various methods.
 * These methods are called in the graphical implementations of different pages when certain conditions are met, e.g.
 * a button is clicked. All logic and authentication are carried out through this BA, therefore all graphical pages
 * have a BankAutomated object passed into them.
 */
public class BankAutomated
{
    List<CA> customerAccounts = Collections.synchronizedList(new ArrayList<>());
    private final ConcurrentHashMap<String, CA> customerHash = new ConcurrentHashMap<>();
    ArrayList<AD> admins = new ArrayList<>();
    ArrayList<MT> maintenanceTeam = new ArrayList<>();
    ArrayList<CSR> customerService = new ArrayList<>();
    
    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Constructor for BankAutomated to be used for the TestCase (for the JUnit test cases)
     * This prevents the people.ser file from interfering with the test case results
     * @param ignoredTest a boolean to allow the overloading of the constructor
     */
    public BankAutomated(boolean ignoredTest){
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

        CSR customerService1 = new CSR("Mister", "CustomerService1", "customerservice@BCS.ca", "123456789", 0);
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
    
    /**
     * Constructor for the BankAutomated class
     * Creates a few admin AD, MT, and CSR objects and adds them to their respective arraylists.
     * people.ser file is opened and any objects stored in it are loaded as well.
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
            BufferedInputStream bufferedIn = new BufferedInputStream(accountsInput, 8192);
            ObjectInputStream accountObject = new ObjectInputStream(bufferedIn)) {

            List<CA> accounts = new ArrayList<>();

            // Read all accounts from the file
            while (true) {
                try {
                    CA account = (CA) accountObject.readObject();
                    accounts.add(account);
                } catch (EOFException ex) {
                    break;
                }
            }

            // Process each account
            for (CA account : accounts) {
                customerAccounts.add(account);
                customerHash.put(account.getEmail().toLowerCase(), account);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File is empty");
        } catch (IOException ex) {
            // System.out.println("nothing inside");
            // usually means the file is corrupted or nothing inside
            // ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            clearPeopleFile();
        }

        long endTime = System.currentTimeMillis();
        double timePassedSeconds = (endTime - startTime) / 1000.0;

        System.out.println("Loaded " + customerAccounts.size() + " customer objects. In: " + timePassedSeconds + "s");
    }

    /**
     * Clears the People.ser file
     * NOTE: This will delete all customer accounts that are saved in the system (i.e., any registered customer)
     */
    public void clearPeopleFile() {
        try {

            // Delete the file
            Path of = Path.of("People.ser");
            Files.deleteIfExists(of);
    
            // Create a new empty file
            Files.createFile(of);
            
            // Print a message
            System.out.println("Cleared People.ser file");

        // File doesn't exist
        } catch (NoSuchFileException ex) {
            System.out.println("People.ser file doesn't exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the card is expired and valid
     * @param expiryDate The expiry date of the card
     * @return boolean True if the card is expired, false otherwise
     * 
     */
    public boolean validCardExpiry(String expiryDate) {
        // Check if the expiry date is in the correct format
        if (!expiryDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }
        
        // Parse the expiry date into a LocalDate object
        LocalDate expiry = LocalDate.parse(expiryDate, java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        
        // Check if the expiry date is in the future
        return !LocalDate.now().isAfter(expiry);
        
        // Return true if the expiry date is valid and not expired
    }

    /**
     * Checks if the bank number is valid
     * @param bankNumber The bank account number of the customer
     * @return True if the bank account number is valid
     * 
     */
    public boolean validBankNumber(String bankNumber) {
        // Check if the expiry date is in the correct format
        if (bankNumber.length() != 5) {
            return false;
        }

        return onlyNumeric(bankNumber);
    }

    /**
     * Changes the password of a user
     * @param user The user to change the password of
     * @param newPassword The new password of the user
     * 
     */
    public void changePassword(CA user, String newPassword){

        user.setPassword(newPassword);

    }

    /**
     * Checks if the email is valid
     * @param email The email to check
     * @return true if the email is valid, false otherwise
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

    /**
     * Checks if the email is already in use
     * @param email The email to check
     * @return true if the email is already in use, false otherwise
     * 
     */
    public boolean existingEmail(String email)
    {
        return customerHash.containsKey(email.toLowerCase());
    }

    /**
     * Checks if the password is valid
     * @param password The password to check
     * @return true if the password is valid, false otherwise
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

    /**
     * Creates a new customer account
     * @param email The email of the customer
     * @param password The password of the customer
     * @return CA The customer account if it was created successfully, null otherwise
     *
     */
    public CA loginAccount(String email, String password)
    {
        System.out.println("Logging in customer with email: " + email);

        // Multithreaded Stream
        CA customer = customerHash.get(email.toLowerCase());
        if (customer != null && password.equals(customer.getPassword())) {
            return customer;
        }

        System.out.println("Customer with email: " + email + " not found.");
        return null;
    }

    /**
     * Checks if the string contains numbers only
     * @param str The string to check
     * @return true if the string contains numbers only, false otherwise
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

    /**
     * Checks if the string contains numbers only (allows up to one decimal point for doubles)
     * @param str The string to check
     * @return true if the string contains numbers only, false otherwise
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

        if (str.charAt(str.length()-1) == '.')
        {
            return false;
        }
        else return !str.equals(".");
    }

    /**
     * Checks if the ccv is valid
     * @param cvv The ccv to check
     * @return true if the ccv is valid, false otherwise
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

    /**
     * Checks if the date of birth is valid
     * @param month The month of the date of birth
     * @param day The day of the date of birth
     * @param year The year of the date of birth
     * @return true if the date of birth is valid, false otherwise
     * 
     */
    public boolean validDOB(String month, String day, String year)
    {
        int numYear = Integer.parseInt(year);
        int numDay = Integer.parseInt(day);

        if (2023 - numYear < 18) {
            return false;
        }

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

    /**
     * Checks if the credit card number is valid
     * @param cardNum The credit card number
     * @return true if the card number is valid, false otherwise
     * 
     */
    public boolean validCard(String cardNum) {
        boolean valid;

        // If card number already exists, return false
        for (CA cust: customerAccounts)
        {
            if (cust.getCardNum().equals(cardNum))
            {
                return false;
            }
        }

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

    /**
     * This function creates a new customer account, and returns the new account if it was created successfully.
     * If the account was not created successfully, then it returns null.
     * The account is not created successfully if the email is already in use, if the email is not valid, if the password is not valid,
     * or if the card number is not valid.
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param phoneNum The phone number of the customer
     * @param address The address of the customer
     * @param gender Gender of the customer
     * @param dob Date of birth of the customer
     * @param email Email of the customer
     * @param password Password of the customer
     * @param cardNum Card number of the customer
     * @param cardExpiry Expiry date of the card
     * @param cvv CVV of the card
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
        customerHash.put(email.toLowerCase(), customer);

        return customer;
    }

    /**
     * This function does the same as the previous one; however, it is used for the stressTest testing in order to
     * create a million customer objects at once. The function takes in the same parameters except card number, which
     * it sets to the empty string in order to create a million customer objects without having to assign a new card
     * number to all 1,000,000 objects in the stressTest.
     * It is also used in the e-transfer and bank transfer testing to prevent the same issue
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param phoneNum The phone number of the customer
     * @param address The address of the customer
     * @param gender Gender of the customer
     * @param dob Date of birth of the customer
     * @param email Email of the customer
     * @param password Password of the customer
     * @param cardExpiry Expiry date of the card
     * @param cvv CVV of the card
     * @return CA The new customer account if it was created successfully, null otherwise
     */
    public CA createAccountTest(String firstName, String lastName, String phoneNum, String address, String gender, String dob,
                                String email, String password, String cardExpiry, String cvv)
    {
        // Check if the email is already in use, if the email is valid, if the password is valid, and if the card number is valid
        if (existingEmail(email) || !validEmail(email) || !validPassword(password)) {
            return null;
        }

        // Create and return new CA object
        CA customer = new CA(firstName, lastName, phoneNum, address, gender, dob, email, password, "", cardExpiry, cvv);

        // Add the new account to the customerAccounts list and customerHash map
        customerAccounts.add(customer);
        customerHash.put(email.toLowerCase(), customer);

        return customer;
    }

    /**
     * Adds a report to the customer's report list and the admin's report list
     * @param customer The customer who made the report
     * @param description The description of the report
     * @return the report created
     */
    public Report makeReport(CA customer, String description)
    {
        // Get random admin that receives the report
        Random rand = new Random();
        int randIndex = rand.nextInt(admins.size()-1);
        AD admin = admins.get(randIndex);

        // Create a new report object
        Report report = new Report(customer, admin, description);

        // Add the report to the list of reports
        customer.addReport(report);
        admin.addCustomerReports(report);

        return report;
    }

    /**
     * This function allows a customer to make a request. It returns true if the request was made successfully, and false otherwise.
     * Allowing the customer to make a request about a technical issue, a maintenance issue, or a customer service issue.
     * @param customer The customer who made the request
     * @param type The type of request
     * @param str The details of the request
     */
    public void makeRequest(CA customer, String type, String str) {

        Request request;

        Random rand = new Random();
        int randIndex;

        switch(type){
            case "1": // Maintenance
                randIndex = rand.nextInt(maintenanceTeam.size()-1);
                MT maintenance = maintenanceTeam.get(randIndex);
                request = new Request(type, maintenance, str);
                maintenance.addSysRequest(request);
                break;
            case "2": // Technical
                randIndex = rand.nextInt(admins.size()-1);
                AD admin = admins.get(randIndex);
                request = new Request(type, admin, str);
                admin.addMeetingRequests(request);
                break;
            case "3": // Customer Service
                randIndex = rand.nextInt(customerService.size()-1);
                CSR csr = customerService.get(randIndex);
                request = new Request(type, csr, str);
                csr.addRequest(request);
                break;
            default:
                return;
        }

        customer.addRequests(request);

    }

    /**
     * Allows users to transfer money between their chequing and savings accounts
     * @param transferAmount amount of money to transfer
     * @param fromAccount account to transfer money from
     * @param customer customer object
     * @return 0 if transfer is successful, 1 if transfer is unsuccessful, 2 if amount is too small
     * 
     */
    public int transferFunds(double transferAmount, String fromAccount, CA customer) {

        //if transfer amount is less than 0.5, then return 2 to make an error
        if (transferAmount < 0.5)
        {
            return 2;
        }

        Random rand = new Random();
        int idSender = rand.nextInt(500);
        int idReceiver = rand.nextInt(500);
        if (fromAccount.equals("Chequing")) {

            if (transferAmount > customer.getChequing()) {

                return 1;

            }
            Transaction transaction = new Transaction("Chequing", "Savings", transferAmount,idSender);
            transaction.setAccountFrom("Chequing");
            transaction.setSenderRemaining(customer.getChequing() - transferAmount);
            transaction.setReceiverRemaining(customer.getSavings() + transferAmount);

            customer.setChequing(customer.getChequing() - transferAmount);
            customer.setSavings(customer.getSavings() + transferAmount);
            customer.addChequing(transaction);
            customer.addSaving(transaction);

        } else {

            if (transferAmount > customer.getSavings()) {

                return 1;
                
            }

            Transaction transaction = new Transaction("Savings", "Chequing", transferAmount, idReceiver);
            transaction.setAccountFrom("Savings");
            transaction.setSenderRemaining(customer.getSavings() - transferAmount);
            transaction.setReceiverRemaining(customer.getChequing() + transferAmount);

            customer.setChequing(customer.getChequing() + transferAmount);
            customer.setSavings(customer.getSavings() - transferAmount);
            customer.addChequing(transaction);
            customer.addSaving(transaction);

        }
        return 0;

    }

    /**
     * Allows users to transfer money to other users of the bank (if they have an account)
     * @param amount amount of money to transfer
     * @param receiverEmail email of the receiver
     * @param customer the customer who is sending the money
     * @param accountFrom the account the money is being sent from
     * @return 0 if successful, 1 if receiver does not have an account, 2 if insufficient funds, 3 if receiver is not in BCS, 4 if amount is greater than 1000, return 5 if amount is too small (less than 0.5), return 6 if etransfer is to the customers account (etransfer to themselves)
     * 
     */
    public int etransfer(double amount, String receiverEmail, CA customer, String accountFrom) {
        if (amount < 0.5)
        {
            return 5;
        }

        if (receiverEmail.equalsIgnoreCase(customer.getEmail()))
        {
            return 6;
        }

        CA receiverAccount = customerHash.get(receiverEmail);

        // Check if receiver email is valid
        if (!validEmail(receiverEmail)){
            return 1;
        }

        // Check if the amount is greater than 1000
        if (amount > 1000)
        {
            return 4;
        }

        // Check if the sender has enough money to send
        if (accountFrom.equals("Chequing") && amount > customer.getChequing()) {

            return 2;

        // Check if the sender has enough money to send
        } else if (accountFrom.equals("Savings") && amount > customer.getSavings()) {

            return 2;
        }
        // If all checks pass, transfer the money and receiver's account is valid
        else
        {
            Random rand = new Random();
            int idSender = rand.nextInt(500);
            int idReceiver = rand.nextInt(500);
            if (receiverAccount == null) {
                Transaction transaction = new Transaction(customer.getEmail(), receiverEmail, amount, idSender);
                if (accountFrom.equals("Chequing"))
                {
                    transaction.setAccountFrom("Chequing");
                    transaction.setSenderRemaining(customer.getChequing() - amount);

                    customer.setChequing(customer.getChequing() - amount);
                    customer.addChequing(transaction);
                }
                else
                {
                    transaction.setAccountFrom("Savings");
                    transaction.setSenderRemaining(customer.getSavings() - amount);

                    customer.setSavings(customer.getSavings() - amount);
                    customer.addSaving(transaction);
                }
                return 3;
            }
            else
            {
                Transaction senderTransaction = new Transaction(customer.getEmail(), receiverEmail, amount, idSender);
                if (accountFrom.equals("Chequing"))
                {
                    senderTransaction.setAccountFrom("Chequing");

                    Transaction receiverTransaction = new Transaction(customer.getEmail(), receiverEmail, amount, idReceiver);
                    senderTransaction.setSenderRemaining(customer.getChequing() - amount);
                    receiverTransaction.setReceiverRemaining(customer.getChequing() + amount);

                    customer.setChequing(customer.getChequing() - amount);
                    customer.addChequing(senderTransaction);
                    receiverAccount.setChequing(receiverAccount.getChequing() + amount);
                    receiverAccount.addChequing(receiverTransaction);
                }
                else
                {
                    senderTransaction.setAccountFrom("Savings");

                    Transaction receiverTransaction = new Transaction(customer.getEmail(), receiverEmail, amount, idReceiver);
                    senderTransaction.setSenderRemaining(customer.getSavings() - amount);
                    receiverTransaction.setReceiverRemaining(customer.getChequing() + amount);

                    customer.setSavings(customer.getSavings() - amount);
                    customer.addSaving(senderTransaction);
                    receiverAccount.setChequing(receiverAccount.getChequing() + amount);
                    receiverAccount.addChequing(receiverTransaction);

                }
            }
        }

        return 0;
    }


    /**
     * This function allows users to transfer money to another user's account using their bank number
     * @param amount amount of money to transfer
     * @param receiverAcc bank number of the receiver
     * @param customer the customer who is sending the money
     * @param accountFrom the account the money is being sent from
     * @return int 0 if successful, 1 if receiver account is invalid, 2 if insufficient funds, 3 if receiver is not in BCS, 5 if amount is less than 0.5, 6 if they try to make a bank transfer to themselves
     * 
     */
    public int bankTransfer(double amount, String receiverAcc, CA customer, String accountFrom)
    {
        if (amount < 0.5)
        {
            return 5;
        }

        if (receiverAcc.equals(customer.getBankNumber()))
        {
            return 6;
        }


        // Check if receiver account is valid
        if (receiverAcc.length() != 5 || !onlyNumeric(receiverAcc)) {
            return 1;
        }
        // Check if customer has sufficient funds in chequing
        else if (accountFrom.equals("Chequing") && amount > customer.getChequing()) {
            return 2;
        }
        // Check if customer has sufficient funds in savings
        else if (accountFrom.equals("Savings") && amount > customer.getSavings()) {
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

        Random rand = new Random();
        int idSender = rand.nextInt(500);
        int idReceiver = rand.nextInt(500);

        // Subtract the amount from the customer's account and add it to the receiver's account (chequing / savings)
        if (accountFrom.equals("Chequing")) {
            Transaction transaction = new Transaction(customer.getFirstName() + " " + customer.getLastName(), receiverAcc, amount,idSender);
            transaction.setAccountFrom("Chequing");
            transaction.setSenderRemaining(customer.getChequing() - amount);

            customer.setChequing(customer.getChequing() - amount);
            customer.addChequing(transaction);
        } else if (accountFrom.equals("Savings")) {
            Transaction transaction = new Transaction(customer.getFirstName() + " " + customer.getLastName(), receiverAcc, amount,idSender);
            transaction.setAccountFrom("Savings");
            transaction.setSenderRemaining(customer.getSavings() - amount);

            customer.setSavings(customer.getSavings() - amount);
            customer.addSaving(transaction);
        }// Add the amount to the receiver's account (chequing auto-deposit)
        if (receiver != null){
            Transaction receiverTrans = new Transaction(customer.getFirstName() + " " + customer.getLastName(), receiverAcc, amount,idReceiver);
            receiverTrans.setReceiverRemaining(customer.getChequing() + amount);

            receiver.setChequing(receiver.getChequing() + amount);
            receiver.addChequing(receiverTrans);

            return 0;
        }
        else
        {
            // Signify external transaction
            return 3;
        }
    }

    /**
     * This method is used to return a list of addresses for the customer to 
     * choose from when visiting a branch.
     * @return an array list of strings of the locations of branches
     */
    public ArrayList<String> addresses() {
        ArrayList<String> locationList = new ArrayList<>();

        locationList.add("10153 King George Blvd, Vancouver, BC");
        locationList.add("255 Yonge Street, Toronto, ON");
        locationList.add("2210 Bank Street, Ottawa, ON");
        locationList.add("1955 Chandler Road, New York City, NY");
        locationList.add("21 Lovecraft Lane, Montreal, QC");
        locationList.add("1965 Herbert Blvd, Halifax, NS");

        return locationList; 
    }

    /**
     * This method is used for logout. It is used to save the customer accounts to a file.
     * It will save the customer accounts to a file called People.ser, DO NOT TOUCH THIS METHOD.
     * 
     */
    public void logout() {

        long startTime = System.currentTimeMillis();

        System.out.println("Logging customer out...\nUploading customer objects...");

        // Submit a task to the executor to write the customer accounts to the file
        List<CA> accountsToWrite = new ArrayList<>(customerAccounts);
        Future<Void> future = executor.submit(() -> {
            try (FileOutputStream accountsFile = new FileOutputStream("People.ser");
                BufferedOutputStream bufferedOut = new BufferedOutputStream(accountsFile);
                ObjectOutputStream accountObject = new ObjectOutputStream(bufferedOut)) {
                for (CA account : accountsToWrite) {
                    accountObject.writeObject(account);
                }
                return null;
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        });

        // Wait for the task to complete and shutdown the executor
        try {
            future.get();
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        executor.shutdown();

        long endTime = System.currentTimeMillis();
        double timePassedSeconds = (endTime - startTime) / 1000.0;

        System.out.println("Uploaded " + customerAccounts.size() + " customer objects. In: " + timePassedSeconds + "s");
    }
}
