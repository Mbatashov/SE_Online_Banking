import org.junit.Test;
import javax.swing.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

// Test cases for each of the functions used, including the BankAutomated functions and setters and getters.
public class TestCase {
    CA dummy;

    /* Test 0
     * Test case for saving and loading from file
     * 
     */
    @Test
    public void saveloadTest(){
        BankAutomated BA = new BankAutomated(false);

        BA.createAccountTest("test", "dummy", "416-792-1234", "test street",
                            "Male", "01/01/1990", "test@gmail.com", "Hello@World1", "01/01/2027", "555");

        // Save to file
        BA.logout();

        // Load from file
        BA = new BankAutomated();

        // Test 1
        // Check if the account is loaded correctly, correct amount is 0
        assertEquals(BA.customerAccounts.size(), 1);

        // Test 2
        // Check if the account is loaded correctly, correct amount is 0
        assertNotEquals(BA.customerAccounts.size(), 2);

        BankAutomated BA_test_two = new BankAutomated(false);

        int batchSize = 1000;

        // Split the workload into 1000 threads, race condition handled by hash map inside createAccount
        IntStream.range(0, 10)

            // Each thread work simultaneously
            .parallel()

            // Each thread do:
            .forEach(batch -> {

                // start and end for each batch
                int start = batch * batchSize;
                int end = start + batchSize;

                // Do work:
                for (int i = start; i < end; i++) {
                    String email = i + "@gmail.com";
                    BA_test_two.createAccountTest("test", "dummy", "416-792-1234", "test street",
                            "Male", "01/01/1990", email, "Hello@World1", "01/01/2027", "555");
                }

            });

        // Save to file
        BA_test_two.logout();

        // Load from file
        BA = new BankAutomated();

        // Test 3
        // Check if the account is loaded correctly, correct amount is 0
        assertEquals(BA.customerAccounts.size(), 10000);

    }
    /* Test 1
     * Stress test for createAccount and login
     */
    @Test
    public void stressTest() {
        BankAutomated BA = new BankAutomated(false);

        long startTime = System.currentTimeMillis();

        // Batch size is the amount of work done by each thread
        int batchSize = 1000;

        // Split the workload into 1000 threads, race condition handled by hash map inside createAccount
        IntStream.range(0, 1000)

            // Each thread work simultaneously
            .parallel()

            // Each thread do:
            .forEach(batch -> {

                // start and end for each batch
                int start = batch * batchSize;
                int end = start + batchSize;

                // Do work:
                for (int i = start; i < end; i++) {
                    String email = i + "@gmail.com";
                    BA.createAccountTest("test", "dummy", "416-792-1234", "test street",
                            "Male", "01/01/1990", email, "Hello@World1", "01/01/2027", "555");
                }

            });
        
        long endTime = System.currentTimeMillis();
        double timePassedSeconds = (endTime - startTime);

        System.out.println("One million accounts created in " + timePassedSeconds + " milliseconds");

        assert timePassedSeconds <= 2000; // Normal loop is 7-9 seconds, this will take 0.5
        
        startTime = System.currentTimeMillis();

        dummy = BA.loginAccount("12312@gmail.com", "Hello@World1");
        assertNotEquals(dummy, null);

        endTime = System.currentTimeMillis();
        //noinspection IntegerDivisionInFloatingPointContext
        timePassedSeconds = (endTime - startTime)/1000;

        System.out.println("Login time: " + timePassedSeconds + " seconds");

        assert timePassedSeconds <= 0.1; // Memory Access should be instant

        JOptionPane.showMessageDialog(null, "Test cases passed", "Stress test", JOptionPane.INFORMATION_MESSAGE);

    }

    /* Test 2
     * Test case for the createAccount method
     * 
     */
    @Test
    public void testRegister() {

        BankAutomated BA = new BankAutomated(false);

        long startTime = System.currentTimeMillis();
    
        // Test case 1: Valid account
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");
        assertNotEquals(dummy, null);

        // Test case 2: Invalid email
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Female", "01/01/1990", "test@.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");
        assertNull(dummy);

        // Test case 3: Invalid password
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Other", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");
        assertNull(dummy);

        // Test case 4: There should be only one account in the system
        assert(BA.customerAccounts.size() == 1);

        long endTime = System.currentTimeMillis();

        long timePassedSeconds = (endTime - startTime)/1000;

        System.out.println("Register time: " + timePassedSeconds + " seconds");

        JOptionPane.showMessageDialog(null, "Test cases passed", "testRegister", JOptionPane.INFORMATION_MESSAGE);
        
    }

    /* Test 3
     * Test case for the loginAccount method
     * 
     */
    @Test
    public void testLogin() {

        BankAutomated BA = new BankAutomated(false);

        long startTime = System.currentTimeMillis();

        // Test case 1: Valid account
        BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Female", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        dummy = BA.loginAccount("test@gmail.com", "Hello@World1");
        assertNotEquals(dummy, null);

        // Test case 2: Invalid email
        dummy = BA.loginAccount("test@gcxvcxvmail.com", "password");
        assertNull(dummy);

        // Test case 3: Invalid password
        dummy = BA.loginAccount("test@gmail.com", "fc");
        assertNull(dummy);

        long endTime = System.currentTimeMillis();
        long timePassedSeconds = (endTime - startTime)/1000;

        System.out.println("Login time: " + timePassedSeconds + " seconds");

        JOptionPane.showMessageDialog(null, "Test cases passed", "testLogin", JOptionPane.INFORMATION_MESSAGE);
        
    }

    /* Test 4
     * Test case for the deposit method
     * 
     */
    @Test
    public void testTransferFunds(){
        
        BankAutomated BA = new BankAutomated(false);

        long startTime = System.currentTimeMillis();

        // Create a customer
        CA cust = BA.createAccount("Jane", "Doe", "647-123-4567", "123 Example St.", "Female",
                "01/01/2000", "janedoe@example.com", "Password123@", "4417123456789113", 
                "01/01/2030", "123");
        
        // Test 1
        cust.setChequing(10000.0);
        cust.setSavings(5000.0);

        // Transfer funds add a Transaction object to both Chequing and Savings
        BA.transferFunds(5000.0, "Chequing", cust);
        assert(cust.getChequing() == 5000.0);
        assert(cust.getSavings() == 10000.0);
        
        // Check if the transaction was added to the history
        assertEquals(cust.getSavingsHist().size(), 1);

        Transaction temp = cust.getSavingsHist().get(0);
        int amount = (int) (temp.getAmount() * 1);

        // Check if the transaction amount is correct
        assertEquals(amount, 5000);
        
        // Check if the transaction was added to the history
        assertEquals(cust.getChequingHist().size(), 1);

        // Test 2
        cust.setChequing(10000.0);
        cust.setSavings(5000.0);

        BA.transferFunds(5000.0, "Savings", cust);

        // Check if the transaction amount is correct
        assert(cust.getChequing() == 15000);
        assert(cust.getSavings() == 0);

        // Check if the transaction was added to the history
        assertEquals(cust.getSavingsHist().size(), 2);

        temp = cust.getChequingHist().get(0);
        amount = (int) (temp.getAmount() * 1);

        // Check if the transaction amount is correct
        assertEquals(amount, 5000);
        assertEquals(cust.getChequingHist().size(), 2);

        // Test 3
        cust.setChequing(10000.0);
        cust.setSavings(5000.0);
        // Ensure correct error value is return if there are insufficient funds
        assert(BA.transferFunds(5001, "Savings", cust) == 1);
        assert(cust.getChequing() == 10000);
        assert(cust.getSavings() == 5000);

        // Check if the transaction was added to the history
        assertEquals(cust.getSavingsHist().size(), 2);
        assertEquals(cust.getChequingHist().size(), 2);

        // Test 4
        cust.setChequing(10000.0);
        cust.setSavings(5000.0);
        // Ensure correct error value is return if there are insufficient funds
        assert(BA.transferFunds(10001, "Chequing", cust) == 1);
        assert(cust.getChequing() == 10000);
        assert(cust.getSavings() == 5000);

        // Check if the transaction was added to the history
        assertEquals(cust.getSavingsHist().size(), 2);
        assertEquals(cust.getChequingHist().size(), 2);

        long endTime = System.currentTimeMillis();
        long timePassedSeconds = (endTime - startTime)/1000;

        System.out.println("Transfer Funds time: " + timePassedSeconds + " seconds");
        
        JOptionPane.showMessageDialog(null, "Test cases passed", "testTransferFunds", JOptionPane.INFORMATION_MESSAGE);

    }

    /* Test 5
     * Test case for the deposit method
     * 
     */
    @Test
    public void testETransfer() {

        BankAutomated BA = new BankAutomated(false);

        long startTime = System.currentTimeMillis();

        // Create a customer
        CA jane = BA.createAccountTest("Jane", "Doe", "647-123-4567", "123 Example St.", "Female",
                "01/01/2000", "janedoe@example.com", "Password123@", 
                "01/01/2030", "123");

        // Create a customer
        CA john = BA.createAccountTest("John", "Doe", "647-987-6543", "321 Example St.", "Male",
                "01/01/2000", "johndoe@example.com", "Password123@",
                "01/01/2030", "123");


        john.setChequing(10000);
        jane.setChequing(10000);

        // John to Jane
        assertEquals(BA.etransfer(999, "janedoe@example.com", john, "Chequing"), 0);

        // Check if the transaction amount is correct
        assert(john.getChequing() == 10000 - 999);
        assert(jane.getChequing() == 10000 + 999);

        // Check if the transaction was added to the history
        assertEquals(john.getChequingHist().size(), 1);
        assertEquals(jane.getChequingHist().size(), 1);

        john.setChequing(10000);
        jane.setChequing(10000);

        // Jane to John
        assertEquals(BA.etransfer(999, "johndoe@example.com", jane, "Chequing"), 0);

        // Check if the transaction amount is correct
        assert(john.getChequing() == 10000 + 999);
        assert(jane.getChequing() == 10000 - 999);

        // Check if the transaction was added to the history
        assertEquals(john.getChequingHist().size(), 2);
        assertEquals(jane.getChequingHist().size(), 2);

        // Test 1
        john.setChequing(10000);
        jane.setChequing(10000);

        // Amount > limit so no Transfer
        assertEquals(BA.etransfer(5000, "janedoe@example.com", john, "Chequing"), 4);

        // Check if the transaction amount is correct
        assert(john.getChequing() == 10000);
        assert(jane.getChequing() == 10000);

        // Test 2
        john.setChequing(10000);
        jane.setChequing(10000);

        // Amount > limit so no Transfer
        assertEquals(BA.etransfer(5000, "johndoe@example.com", jane, "Chequing"), 4);
        assert(jane.getChequing() == 10000);
        assert(john.getChequing() == 10000);

        // Error code tests
        john.setChequing(10000);
        jane.setChequing(10000);

        // Invalid Email address (returns code 1)
        assertEquals(BA.etransfer(500, "johnDoe", jane, "Chequing"), 1);
        assert(john.getChequing() == 10000);
        assert(jane.getChequing() == 10000);


        // Transfer amount above limit, returns error code 4
        assertEquals(BA.etransfer(10001, "johndoe@example.com", jane, "Chequing"), 4);
        assert(john.getChequing() == 10000);
        assert(jane.getChequing() == 10000);

        // Email not found (returns code 3) and does an external transaction
        assertEquals(BA.etransfer(500, "test@example.com", jane, "Chequing"), 3);
        assert(john.getChequing() == 10000);
        assert(jane.getChequing() == 9500);

        long endTime = System.currentTimeMillis();
        long timePassedSeconds = (endTime - startTime)/1000;

        System.out.println("Transfer Funds time: " + timePassedSeconds + " seconds");

        JOptionPane.showMessageDialog(null, "Test cases passed", "testETransferFunds", JOptionPane.INFORMATION_MESSAGE);
    }

    /* Test 6
     * Test case for the deposit method
     * 
     */
    @Test
    public void testBankTransfer()
    {
        BankAutomated BA = new BankAutomated(false);

        long startTime = System.currentTimeMillis();

        // Create a customer
        CA jane = BA.createAccountTest("Jane", "Doe", "647-123-4567", "123 Example St.", "Female",
                "01/01/2000", "janedoe@example.com", "Password123@", 
                "01/01/2030", "123");

        // Create a customer
        CA john = BA.createAccountTest("John", "Doe", "647-987-6543", "321 Example St.", "Male",
                "01/01/2000", "johndoe@example.com", "Password123@", 
                "01/01/2030", "123");
        
        jane.setBankNumber("12345");
        john.setBankNumber("54321");

        // Test 1
        john.setChequing(10000);
        jane.setChequing(10000);

        // Check if transfer is successful
        assertEquals(BA.bankTransfer(5000, "12345", john, "Chequing"), 0);

        // Check if the transaction amount is correct
        assert(john.getChequing() == 5000);
        assert(jane.getChequing() == 15000);

        // Check if the transaction was added to the history
        assertEquals(john.getChequingHist().size(), 1);
        assertEquals(jane.getChequingHist().size(), 1);

        // Test 2
        john.setChequing(10000);
        jane.setChequing(10000);

        // Check if transfer is successful
        assertEquals(BA.bankTransfer(5000, "54321", jane, "Chequing"), 0);

        // Check if the transaction amount is correct
        assert(jane.getChequing() == 5000);
        assert(john.getChequing() == 15000);

        // Check if the transaction was added to the history
        assertEquals(john.getChequingHist().size(), 2);
        assertEquals(jane.getChequingHist().size(), 2);

        // Error code tests
        john.setChequing(10000);
        jane.setChequing(10000);

        // Invalid bank number address (returns code 1)
        assertEquals(BA.bankTransfer(500, "123456789", jane, "Chequing"), 1);
        assert(john.getChequing() == 10000);
        assert(jane.getChequing() == 10000);

        // Insufficient funds (returns code 2)
        assertEquals(BA.bankTransfer(10001, "54321", jane, "Chequing"), 2);
        assert(john.getChequing() == 10000);
        assert(jane.getChequing() == 10000);

        // Email not found (returns code 3), and transfers money to external account
        assertEquals(BA.bankTransfer(500, "98765", jane, "Chequing"), 3);
        assert(john.getChequing() == 10000);
        assert(jane.getChequing() == 9500);

        long endTime = System.currentTimeMillis();
        long timePassedSeconds = (endTime - startTime)/1000;

        System.out.println("Transfer Funds time: " + timePassedSeconds + " seconds");

        JOptionPane.showMessageDialog(null, "Test cases passed", "testBankTransferFunds", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /* Test 7
     * Test case for card expiry
     * 
     */
    @Test
    public void testCardExpiry() {

        BankAutomated BA = new BankAutomated(false);
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        // Test 1
        // Check if the card expiry is valid
        assertTrue(BA.validCardExpiry(dummy.getCardExpiry()));

        dummy.setCardExpiry("01/12/2021");

        // Test 2
        // Check if the card expiry is invalid
        assert(!BA.validCardExpiry(dummy.getCardExpiry()));

        JOptionPane.showMessageDialog(null, "Test cases passed", "testCardExpiry", JOptionPane.INFORMATION_MESSAGE);
    }

    /* Test 8
     * Test case for bank number
     * 
     */
    @Test
    public void testBankNumber() {

        BankAutomated BA = new BankAutomated(false);
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        dummy.setBankNumber("12345");

        // Test 1
        // Check if the bank number is being set
        assert(dummy.getBankNumber().equals("12345"));

        dummy.setBankNumber("98765");

        // Test 2
        // Check if the bank number is being set
        assert(dummy.getBankNumber().equals("98765"));

        // Test 3
        // Check if the bank number is valid
        assertTrue(BA.validBankNumber("12345"));

        // Test 4
        // Check if the bank number is invalid
        assertFalse(BA.validBankNumber("123456"));

        JOptionPane.showMessageDialog(null, "Test cases passed", "testBankNumber", JOptionPane.INFORMATION_MESSAGE);

    }

    /* Test 9
     * Test case for CVV
     * 
     */
    @Test
    public void testCVV() {

        BankAutomated BA = new BankAutomated(false);
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        // Test 1
        // Check if the CVV is being set
        assert(dummy.getCvv().equals("555"));

        dummy.setCvv("787");

        // Test 2
        // Check if the CVV is being set
        assert(dummy.getCvv().equals("787"));

        // Test 3
        // Check if the CVV is invalid
        assertFalse(BA.validCVV("123456"));

        // Test 4
        // Check if the CVV is valid
        assertTrue(BA.validCVV("126"));

        JOptionPane.showMessageDialog(null, "Test cases passed", "testCVV", JOptionPane.INFORMATION_MESSAGE);

    }

    /* Test 10
     * Test case for password
     * 
     */
    @Test
    public void testPassword() {

        BankAutomated BA = new BankAutomated(false);
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        // Test 1
        // Check if the password is being set
        assert(dummy.getPassword().equals("Hello@World1"));

        dummy.setPassword("v3RySaf3paSsW0rd");

        // Test 2
        // Check if the password is being set
        assert(dummy.getPassword().equals("v3RySaf3paSsW0rd"));

        // Test 3
        // Check if the password is valid
        assertTrue(BA.validPassword("Hello123!@#"));

        // Test 4
        // Check if the password is invalid
        assertFalse(BA.validPassword("hello123!@#"));

        JOptionPane.showMessageDialog(null, "Test cases passed", "testPassword", JOptionPane.INFORMATION_MESSAGE);

    }

    /* Test 11
     * Test case for date of birth
     * 
     */
    @Test
    public void testDoB() {

        BankAutomated BA = new BankAutomated(false);
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        // Test 1
        // Check if the DoB is being set
        assert(dummy.getDob().equals("01/01/1990"));

        dummy.setDob("09/12/2003");

        // Test 2
        // Check if the DoB is being set
        assert(dummy.getDob().equals("09/12/2003"));

        // Test 3
        // Check if the DoB is invalid
        assertTrue(BA.validDOB("09", "01", "2003"));

        // Test 4
        // Check if the DoB is valid
        assertFalse(BA.validDOB("09", "01", "2013"));

        JOptionPane.showMessageDialog(null, "Test cases passed", "testDOB", JOptionPane.INFORMATION_MESSAGE);

    }

    /* Test 12
     * Test case for gender
     * 
     */
    @Test
    public void testGender() {

        BankAutomated BA = new BankAutomated(false);
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        // Test 1
        // Test if gender is set
        assert(dummy.getGender().equals("Male"));

        dummy.setGender("Female");

        // Test 2
        // Test if gender is set
        assert(dummy.getGender().equals("Female"));

        JOptionPane.showMessageDialog(null, "Test cases passed", "testGender", JOptionPane.INFORMATION_MESSAGE);


    }

    /* Test 13
     * Test case for address
     * 
     */
    @Test
    public void testAddress() {

        BankAutomated BA = new BankAutomated(false);
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");


        // Test 1
        // Check if the address is being set
        assert(dummy.getAddress().equals("test street"));

        dummy.setAddress("5 CS Road");

        // Test 2
        // Check if the address is being set
        assert(dummy.getAddress().equals("5 CS Road"));

        JOptionPane.showMessageDialog(null, "Test cases passed", "testAddress", JOptionPane.INFORMATION_MESSAGE);

    }

    /* Test 14
     * Test case for card number
     * 
     */
    @Test
    public void testCardNum() {

        BankAutomated BA = new BankAutomated(false);
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        // Test 1
        // Check if the card number is being set
        assert(dummy.getCardNum().equals("4417123456789113"));

        dummy.setCardNum("552823455789113");

        // Test 2
        // Check if the card number is being set
        assert(dummy.getCardNum().equals("552823455789113"));

        // Test 3
        // Check if the card number is valid
        assertTrue(BA.validCard("4417123456789113"));

        // Test 4
        // Check if the card number is invalid
        assertFalse(BA.validCard("552823455789113"));

        JOptionPane.showMessageDialog(null, "Test cases passed", "testCardNum", JOptionPane.INFORMATION_MESSAGE);

    }

    /* Test 15
     * Test case for chequing set
     * 
     */
    @Test
    public void testChequing() {

        BankAutomated BA = new BankAutomated(false);
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        dummy.setChequing(4545.13);

        // Test 1
        // Check if the chequing is being set
        assert(dummy.getChequing() == 4545.13);

        dummy.setChequing(5942.84);

        // Test 2
        // Check if the chequing is being set
        assert(dummy.getChequing() == 5942.84);

        JOptionPane.showMessageDialog(null, "Test cases passed", "testChequing", JOptionPane.INFORMATION_MESSAGE);

    }

    /* Test 16
     * Test case for savings set
     * 
     */
    @Test
    public void testSavings() {

        BankAutomated BA = new BankAutomated(false);
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        dummy.setSavings(4545.13);

        // Test 1
        // Check if the savings is being set
        assert(dummy.getSavings() == 4545.13);

        dummy.setSavings(5942.84);

        // Test 2
        // Check if the savings is being set
        assert(dummy.getSavings() == 5942.84);

        JOptionPane.showMessageDialog(null, "Test cases passed", "testSavings", JOptionPane.INFORMATION_MESSAGE);

    }

    /* Test 17
     * Test case for chequing history
     * 
     */
    @Test
    public void testChequingHistory() {

        BankAutomated BA = new BankAutomated(false);
        ArrayList<Transaction> chequingHist = new ArrayList<>();

        chequingHist.add(new Transaction("Person 1", "test", 500,1));
        chequingHist.add(new Transaction("test", "Person 2", 115,2));
        chequingHist.add(new Transaction("Person 3", "test", 200,3));
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");
        
        dummy.addChequing(new Transaction("Person 1", "test", 500,4));
        dummy.addChequing(new Transaction("test", "Person 2", 115,5));
        dummy.addChequing(new Transaction("Person 3", "test", 200,6));

        // Test 1
        // Check if the chequing history is being set after adding transactions to each customer
        for (int i = 0; i < chequingHist.size(); i++)
        {
            assert(dummy.getChequingHist().get(i).getReceiver().equals(chequingHist.get(i).getReceiver()));
            assert(dummy.getChequingHist().get(i).getSender().equals(chequingHist.get(i).getSender()));
            assert (dummy.getChequingHist().get(i).getAmount() == chequingHist.get(i).getAmount());
        }

        JOptionPane.showMessageDialog(null, "Test cases passed", "testChequingHistory", JOptionPane.INFORMATION_MESSAGE);
        
    }

    /* Test 18
     * Test case for savings history
     * 
     */
    @Test
    public void testSavingsHistory() {

        BankAutomated BA = new BankAutomated(false);
        ArrayList<Transaction> savingsHist = new ArrayList<>();

        savingsHist.add(new Transaction("Person 1", "test", 500,1));
        savingsHist.add(new Transaction("test", "Person 2", 115,2));
        savingsHist.add(new Transaction("Person 3", "test", 200,3));
    
        dummy = BA.createAccount("test", "dummy", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");
        
        dummy.addSaving(new Transaction("Person 1", "test", 500,4));
        dummy.addSaving(new Transaction("test", "Person 2", 115,5));
        dummy.addSaving(new Transaction("Person 3", "test", 200,6));

        // Test 1
        // Check if the savings history is being set after adding transactions to each customer
        for (int i = 0; i < savingsHist.size(); i++)
        {
            assert(dummy.getSavingsHist().get(i).getReceiver().equals(savingsHist.get(i).getReceiver()));
            assert(dummy.getSavingsHist().get(i).getSender().equals(savingsHist.get(i).getSender()));
            assert(dummy.getSavingsHist().get(i).getAmount() == savingsHist.get(i).getAmount());
        }

        JOptionPane.showMessageDialog(null, "Test cases passed", "testCardExpiry", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /* Test 19
     * Test case for card expiry
     * 
     */
    @Test
    public void testSetterGetter() {

        BankAutomated BA = new BankAutomated(false);

        // Test setters and getters for People objects
        People test = new People("John", "Doe", "johndoe@example.com", "416-123-4567");

        // Test setter and getters for People objects
        assertEquals("John", test.getFirstName());
        assertEquals("Doe", test.getLastName());
        assertEquals("johndoe@example.com", test.getEmail());
        assertEquals("416-123-4567", test.getPhoneNum());

        test.setFirstName("Jane");
        test.setLastName("Smith");
        test.setEmail("janesmith@example.ca");
        test.setPhoneNum("647-987-6543");

        // Test setters and getters for People objects
        assertEquals("Jane", test.getFirstName());
        assertEquals("Smith", test.getLastName());
        assertEquals("janesmith@example.ca", test.getEmail());
        assertEquals("647-987-6543", test.getPhoneNum());

        // Test setter and getters for CA objects
        dummy = BA.createAccount("John", "Doe", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        dummy.setChequing(500);
        dummy.setSavings(1000);
        dummy.setBankNumber("12345");

        // Test getters for constructor attributes
        assertEquals("John", dummy.getFirstName());
        assertEquals("Doe", dummy.getLastName());
        assertEquals("416-792-1234", dummy.getPhoneNum());
        assertEquals("test street", dummy.getAddress());
        assertEquals("Male", dummy.getGender());
        assertEquals("01/01/1990", dummy.getDob());
        assertEquals("test@gmail.com", dummy.getEmail());
        assertEquals("Hello@World1", dummy.getPassword());
        assertEquals("4417123456789113", dummy.getCardNum());
        assertEquals("01/01/2027", dummy.getCardExpiry());
        assertEquals("555", dummy.getCvv());
        assert(500 == dummy.getChequing());
        assert(1000 == dummy.getSavings());
        assertEquals("12345", dummy.getBankNumber());

        // Test setters by setting a new value, and using getter to check if value is changed
        dummy.setFirstName("Jane");
        dummy.setLastName("Smith");
        dummy.setPhoneNum("416-987-6543");
        dummy.setAddress("321 Bakers St.");
        dummy.setGender("Female");
        dummy.setDob("02/02/2001");
        dummy.setEmail("jane@example.ca");
        dummy.setPassword("123Password@");
        dummy.setCardNum("4417987654321098");
        dummy.setCardExpiry("02/02/2029");
        dummy.setCvv("789");
        dummy.setChequing(1200);
        dummy.setSavings(900);
        dummy.setBankNumber("98765");

        // Use getters to see if values were changed
        assertEquals("Jane", dummy.getFirstName());
        assertEquals("Smith", dummy.getLastName());
        assertEquals("416-987-6543", dummy.getPhoneNum());
        assertEquals("321 Bakers St.", dummy.getAddress());
        assertEquals("Female", dummy.getGender());
        assertEquals("02/02/2001", dummy.getDob());
        assertEquals("jane@example.ca", dummy.getEmail());
        assertEquals("123Password@", dummy.getPassword());
        assertEquals("4417987654321098", dummy.getCardNum());
        assertEquals("02/02/2029", dummy.getCardExpiry());
        assertEquals("789", dummy.getCvv());
        assert(1200 == dummy.getChequing());
        assert(900 == dummy.getSavings());
        assertEquals("98765", dummy.getBankNumber());

        // Add transactions, reports, and requests to respective array lists
        // Then use getters to check if value changed to check that both
        // setters and getters are working
        AD admin = new AD("Mike", "Smith", "mike@bcs.ca", "647-123-4568", 105);

        Transaction chequing = new Transaction("Chequing", "Savings", 500, 201);
        Transaction savings = new Transaction("Savings", "Chequing", 600, 205);
        Report report = new Report(dummy, admin, "test");
        Request request = new Request("RM", admin, "10/10/2022");

        dummy.addChequing(chequing);
        dummy.addSaving(savings);
        dummy.addReport(report);
        dummy.addRequests(request);

        // Use getters to test if setters correctly added values (also
        // tests if getters work correctly)
        assertEquals(chequing, dummy.getChequingHist().get(0));
        assertEquals(savings, dummy.getSavingsHist().get(0));
        assertEquals(report, dummy.getReportSus().get(0));
        assertEquals(request, dummy.getRequests().get(0));

        // Test setters and getters for transactions
        Transaction transactionTest = new Transaction("Chequing", "Savings", 500, 12345);

        assertEquals("Chequing", transactionTest.getSender());
        assertEquals("Savings", transactionTest.getReceiver());
        assert(500 == transactionTest.getAmount());
        assert(12345 == transactionTest.getId());

        transactionTest.setSender("Current");
        transactionTest.setReceiver("johndoe@example.com");
        transactionTest.setAmount(1000);
        transactionTest.setId(98765);

        assertEquals("Current", transactionTest.getSender());
        assertEquals("johndoe@example.com", transactionTest.getReceiver());
        assert(1000 == transactionTest.getAmount());
        assert(98765 == transactionTest.getId());

        // Test setter and getters for reports
        Report reportTest = new Report(dummy, admin, "test");
        assertEquals(reportTest.getAdmin(), admin);
        
        AD a = new AD("Mojo", "Jojo", "abc@tmu.ca", "6471112222", 0);
        
        reportTest.setAdmin(a);
        assertEquals(reportTest.getAdmin(), a);
        

        assertEquals("Jane", reportTest.getCustomer().getFirstName());
        assertEquals("Smith", reportTest.getCustomer().getLastName());
        assertEquals("jane@example.ca", reportTest.getCustomer().getEmail());
        assertEquals("4417987654321098", reportTest.getCustomer().getCardNum());
        assertEquals("test", reportTest.getDescription());

        reportTest.getCustomer().setFirstName("John");
        reportTest.getCustomer().setLastName("Doe");
        reportTest.getCustomer().setEmail("janesmith@example.ca");
        reportTest.getCustomer().setCardNum("4417210987654321");
        reportTest.setDescription("Test 2");

        assertEquals("John", reportTest.getCustomer().getFirstName());
        assertEquals("Doe", reportTest.getCustomer().getLastName());
        assertEquals("janesmith@example.ca", reportTest.getCustomer().getEmail());
        assertEquals("4417210987654321", reportTest.getCustomer().getCardNum());
        assertEquals("Test 2", reportTest.getDescription());

        // Test setters and getters for requests
        MT mtTest = new MT("Mike", "Smith", "mike@bcs.ca", "647-123-4568", 105);
        CSR csrTest = new CSR("Mike", "Smith", "mike@bcs.ca", "647-123-4568", 105);
        Request requestTest = new Request("RSC", new MT("1", "2", "3", "4", 5), "test");

        requestTest.setMT(mtTest);

        assertEquals("RSC", requestTest.getType());
        assertEquals(mtTest, requestTest.getMT());

        requestTest.setType("RM");
        requestTest.setAdmin(admin);

        assertEquals("RM", requestTest.getType());
        assertEquals(admin, requestTest.getAdmin());

        requestTest.setCSR(csrTest);
        assertEquals(csrTest, requestTest.getCSR());

        // Test setter and getter for Admins (AD)
        admin = new AD("Mike", "Smith", "mike@bcs.ca", "647-123-4568", 105);


        assertEquals(105, admin.getId());

        // Use reportTest and requestTest objects from respective getter/setter tests
        admin.setId(205);
        admin.addCustomerReports(reportTest);
        admin.addMeetingRequests(requestTest);

        assertEquals(205, admin.getId());
        assertEquals(reportTest, admin.getCustomerReports().get(0));
        assertEquals(requestTest, admin.getMeetingRequests().get(0));

        // Test setter and getters for Maintenance Teams (MT)
        MT mt = new MT("Mike", "Smith", "mike@bcs.ca", "647-123-4568", 105);

        assertEquals(105, mt.getId());

        // Use requestTest object from request getter/setter test
        mt.addSysRequest(requestTest);
        mt.setId(205);

        assertEquals(205, mt.getId());
        assertEquals(requestTest, mt.getSysChangeRequests().get(0));

        // Test setter and getters for CSRs
        CSR csr = new CSR("Mike", "Smith", "mike@bcs.ca", "647-123-4568", 105);

        assertEquals(105, csr.getId());

        // Use requestTest object from request getter/setter test
        csr.addRequest(requestTest);
        csr.setId(205);

        assertEquals(205, csr.getId());
        assertEquals(requestTest, csr.getAssistanceRequests().get(0));

        JOptionPane.showMessageDialog(null, "Test cases passed", "testSetterGetter", JOptionPane.INFORMATION_MESSAGE);

        //Test setters and getters for notification settings
        CA tcus = new CA("John", "Doe", "416-792-1234", "test street","Male", "01/01/1990", "test@gmail.com", "Hello@World1","4417123456789113", "01/01/2027", "555");

        tcus.setReportReplies(2);
        tcus.setRequestReplies(4);
        tcus.setNewsletterSubscription(6);
        tcus.setBigPayment(8);
        tcus.setRequireDataCollection(true);
        tcus.setSensitiveDataCollection(true);
        tcus.setKeyLogger(true);
        assertEquals(tcus.getReportReplies(), 2);
        assertEquals(tcus.getRequestReplies(), 4);
        assertEquals(tcus.getNewsletterSubscription(), 6);
        assertEquals(tcus.getBigPayment(), 8);
        assertTrue(tcus.getRequireDataCollection());
        assertTrue(tcus.getSensitiveDataCollection());
        assertTrue(tcus.getKeyLogger());
    }

    /* Test 20
     * Test the request method in BankAutomated
     * 
     */
    @Test
    public void testRequest(){

        BankAutomated BA = new BankAutomated(false);

        dummy = BA.createAccount("John", "Doe", "416-792-1234", "test street",
                "Male", "01/01/1990", "test@gmail.com", "Hello@World1",
                "4417123456789113", "01/01/2027", "555");

        Report report = BA.makeReport(dummy, "test");

        // Test request method
        assertEquals(dummy.getReportSus().size(), 1);

        // See if total reports are equal to 1
        assertEquals(report.getAdmin().getCustomerReports().size(), 1);

        // See if total reports are not equal to 2
        assertNotEquals(dummy.getReportSus().size(), 2);

        JOptionPane.showMessageDialog(null, "Test cases passed", "testRequest", JOptionPane.INFORMATION_MESSAGE);

    }
}
