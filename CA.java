import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.lang.Math;  

// customer account
public class CA extends People
{
    String address;
    String cardNum;
    String password;
    String gender;
    String dob;
    String cardExpiry;
    String cvv;
    String bankNumber; // For bank transfers
    double chequing = 0.0;
    double savings = 0.0;
    ArrayList<Transaction> chequingHist;
    ArrayList<Transaction> savingsHist;
    ArrayList<Report> reportSus;
    ArrayList<Request> requests;

 
    /*
     * Constructor for the Customer Account
     * @param firstName the first name of the Customer Account
     * @param lastName the last name of the Customer Account
     * @param email the email of the Customer Account
     * @param phoneNum the phone number of the Customer Account
     * @param address the address of the Customer Account
     * @param cardNum the card number of the Customer Account
     * @param password the password of the Customer Account
     * @param chequing the chequing balance of the Customer Account
     * @param savings the savings balance of the Customer Account
     * @param chequingHist the chequing history of the Customer Account
     * @param savingsHist the savings history of the Customer Account
     * @param reportSus the report suspicious of the Customer Account
     * @param requests the requests of the Customer Account
     * 
     */
    public CA(String firstName, String lastName, String phoneNum, String address, String gender, String dob,
              String email, String password, String cardNum, String cardExpiry, String cvv) {
        super(firstName, lastName, email, phoneNum);
        this.address = address;
        this.password = password;
        this.cardNum = cardNum;
        this.cardExpiry = cardExpiry;
        this.cvv = cvv;
        this.gender = gender;
        this.dob = dob;

        Random rand = new Random();

        this.chequing = rand.nextDouble()*10000;
        this.savings = rand.nextDouble()*10000;
        this.bankNumber = String.valueOf(rand.nextInt(99999));

        chequingHist = new ArrayList<Transaction>();
        savingsHist = new ArrayList<Transaction>();
        reportSus = new ArrayList<Report>();
        requests = new ArrayList<Request>();
    }

    /*
     * Sets the bank num of the Customer Account
     * @param num the bank number of the Customer Account
     * 
     */
    public void setBankNumber(String num) {

        this.bankNumber = num;

    }

    /*
     * Gets the bank number of the Customer Account
     * @return the bank number of the Customer Account
     * 
     */
    public String getBankNumber() {

        return bankNumber;

    }

    /*
     * Sets the card Expiry of the Customer Account
     * @param cardExpiry the card expiry of the Customer Account
     * 
     */
    public void setCardExpiry(String cardExpiry) {

        this.cardExpiry = cardExpiry;

    }

    /*
     * Gets the card expiry of the Customer Account
     * @return the card expiry of the Customer Account
     * 
     */
    public String getCardExpiry() {

        return cardExpiry;

    }
    
    /*
     * Sets the Credit Card CVV of the Customer Account
     * @param cvv the cvv of the Customer Account
     * 
     */
    public void setCvv(String cvv) {

        this.cvv = cvv;

    }

    /*
     * Gets the Credit Card CVV of the Customer Account
     * @return the cvv of the Customer Account
     * 
     */
    public String getCvv() {

        return cvv;

    }
    
    /*
     * Sets the password of the Customer Account
     * @param password the password of the Customer Account
     * 
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /*
     * Gets the password of the Customer Account
     * @return the password of the Customer Account
     * 
     */
    public String getPassword() {

        return password;

    }
    
    /*
     * Sets the date of bith of the Customer Account
     * @param dob the date of birth of the Customer Account
     * 
     */
    public void setDob(String dob) {
        
        this.dob = dob;

    }


    /*
     * Gets the date of birth of the Customer Account
     * @return the date of birth of the Customer Account
     * 
     */
    public String getDob() {

        return dob;

    }
    

    /*
     * Sets the gender of the customer
     * @param gender the gender of the customer
     */
    public void setGender(String gender) {

        this.gender = gender;

    }

    /*
     * Gets the gender of the customer
     * @return gender the gender of the customer
     */
    public String getGender() {

        return gender;

    }
    
    /*
     * Gets the address of the Customer Account
     * @return the address of the Customer Account
     * 
     */
    public String getAddress() {

        return this.address;

    }

    /*
     * Set the address of the Customer Account
     * @param Address the address of the Customer Account
     * 
     */
    public void setAddress(String Address) {
        this.address = Address;
    }

    /*
     * Gets the card number of the Customer Account
     * @return the card number of the Customer Account
     * 
     */
    public void setCardNum(String cardNum) {

        this.cardNum = cardNum;

    }

    /*
     * Gets the card number of the Customer Account
     * @return the card number of the Customer Account
     * 
     */
    public String getCardNum() {

        return cardNum;

    }

    /*
     * Sets the chequing balance of the Customer Account
     * @param amount the chequing balance of the Customer Account
     * 
     */
    public void setChequing(double amount) {

        this.chequing = amount;

    }

    /*
     * Gets the chequing balance of the Customer Account
     * @return the chequing balance of the Customer Account
     * 
     */
    public double getChequing() {

        return Math.round(chequing*100.0)/100.0;

    }

    /*
     * Sets the savings balance of the Customer Account
     * @param amount the savings balance of the Customer Account
     * 
     */
    public void setSavings(double amount) {

        this.savings = amount;

    }

    /*
     * Gets the savings balance of the Customer Account
     * @return the savings balance of the Customer Account
     * 
     */
    public double getSavings() {

        return Math.round(savings*100.0)/100.0;

    }

    /*
     * Gets the chequing transaction history of the Customer Account
     * @return the chequing transaction history of the Customer Account
     * 
     */
    public ArrayList<Transaction> getChequingHist() {

        return chequingHist;

    }

    /*
     * Adds a transaction to the chequing transaction history of the Customer Account
     * @param chequingTransaction the transaction to be added to the chequing transaction history of the Customer Account
     * 
     */
    public void addChequing(Transaction chequingTransaction)
    {
        chequingHist.add(chequingTransaction);
    }

    /*
     * Gets the savings transaction history of the Customer Account
     * @return the savings transaction history of the Customer Account
     * 
     */
    public ArrayList<Transaction> getSavingsHist() {

        return savingsHist;
    }

    /*
     * Adds a transaction to the savings transaction history of the Customer Account
     * @param savingTransaction the transaction to be added to the savings transaction history of the Customer Account
     * 
     */
    public void addSaving(Transaction savingTransaction) {

        savingsHist.add(savingTransaction);

    }

    /*
     * Gets the suspicious report history of the Customer Account
     * @return the suspicious report history of the Customer Account
     * 
     */
    public ArrayList<Report> getReportSus() {

        return reportSus;

    }

    /*
     * Adds a suspicious report to the suspicious report history of the Customer Account
     * @param suspiciousReport the suspicious report to be added to the suspicious report history of the Customer Account
     * 
     */
    public void addReport(Report suspiciousReport) {

        reportSus.add(suspiciousReport);

    }

    /*
     * Gets the request history of the Customer Account
     * @return the request history of the Customer Account
     * 
     */
    public ArrayList<Request> getRequests() {

        return requests;

    }

    /*
     * Adds a request to the request history of the Customer Account
     * @param request the request to be added to the request history of the Customer Account
     * 
     */
    public void addRequests(Request request) {

        requests.add(request);

    }

    /*
     * prints the Customer Account details
     * 
     */
    public void print() {
        System.out.printf("First Name: %-10s Last Name: %-10s | Email: %-25s | Password: %-20s " +
                " | Address: %-20s\n", firstName, lastName, email, password, address);
    }

    /*
     * prints the Customer Account history details
     * 
     */
    public void printHistory() {
        System.out.printf("%s %s -> ", firstName, lastName);
        System.out.printf("Chequing Transaction History: %-57s | Savings Transaction History: %-65s %n",
                chequingHist == null ? "No chequing transactions" : chequingHist.stream().map(Transaction::toString).collect(Collectors.joining(", ")),
                savingsHist == null ? "No savings transactions" : savingsHist.stream().map(Transaction::toString).collect(Collectors.joining(", "))
        );
    }
    
}