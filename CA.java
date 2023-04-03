package bank.core;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.lang.Math;  

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
    double chequing;
    double savings;
    ArrayList<Transaction> chequingHist;
    ArrayList<Transaction> savingsHist;
    ArrayList<Report> reportSus;
    ArrayList<Request> requests;

    //Notification settings: 0 for don't notify, 1 for Email, 2 for SMS
    private int requestReplies = 1;
    private int reportReplies = 1;
    private int newsletterSubscription = 1;
    private int bigPayment = 1;

    //Privacy Settings: true for allow, false for don't allow
    private boolean locationServices = true;
    private boolean requireDataCollection = true;
    private boolean sensitiveDataCollection = true;
    private boolean keyLogger = true;

    /*
     * Constructor for the Customer Account
     * @param String firstName the first name of the Customer Account
     * @param String lastName the last name of the Customer Account
     * @param String phoneNum the phone number of the Customer Account
     * @param String address the address of the Customer Account
     * @param String gender the gender of the customer
     * @param String dob the date of birth of the customer
     * @param String email the email of the Customer Account
     * @param String password the password of the Customer Account
     * @param String cardNum the card number of the Customer Account
     * @param String cardExpiry the card expiry of the Customer Account
     * @param String cvv the cvv of the Customer Account
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

        chequingHist = new ArrayList<>();
        savingsHist = new ArrayList<>();
        reportSus = new ArrayList<>();
        requests = new ArrayList<>();
    }

    /*
     * Sets the address of the Customer Account
     * @param String address the address of the Customer Account
     * 
     */
    public void setLocationServices(boolean locationServices) {
        this.locationServices = locationServices;
    }

    /*
     * Gets the location services boolean of the Customer Account
     * @return boolean the location services of the Customer Account
     * 
     */
    public boolean getLocationServices() {
        return locationServices;
    }

    /*
     * Sets the notification settings boolean of the Customer Account
     * @param boolean: the notification settings of the Customer Account
     * 
     */
    public void setRequestReplies(int requestReplies) {
        this.requestReplies = requestReplies;
    }
    public void setReportReplies(int reportReplies) {
        this.reportReplies = reportReplies;
    }
    public void setNewsletterSubscription(int subscription)
    {
        this.newsletterSubscription = subscription;
    }
    public void setBigPayment(int bigPayment)
    {
        this.bigPayment = bigPayment;
    }

    /*
     * Gets the notification settings boolean of the Customer Account
     * @return boolean: the notification settings of the Customer Account
     * 
     */
    public int getRequestReplies() {
        return requestReplies;
    }
    public int getReportReplies(){return reportReplies;}
    public int getNewsletterSubscription(){return newsletterSubscription;}
    public int getBigPayment(){return bigPayment;}

    /*
     * Sets the 'require data collection' boolean of the Customer Account
     * @param boolean requireDataCollection the 'require data collection' of the Customer Account
     * 
     */
    public void setRequireDataCollection(boolean requireDataCollection) {
        this.requireDataCollection = requireDataCollection;
    }

    /*
     * Gets the 'require data collection' boolean of the Customer Account
     * @return boolean the 'require data collection' of the Customer Account
     * 
     */
    public boolean getRequireDataCollection() {
        return requireDataCollection;
    }

    /*
     * Sets the sensitive data collection boolean of the Customer Account
     * @param boolean sensitiveDataCollection the sensitive data collection of the Customer Account
     * 
     */
    public void setSensitiveDataCollection(boolean sensitiveDataCollection) {
        this.sensitiveDataCollection = sensitiveDataCollection;
    }


    /*
     * Gets the sensitive data collection boolean of the Customer Account
     * @return boolean the sensitive data collection of the Customer Account
     * 
     */
    public boolean getSensitiveDataCollection() {
        return sensitiveDataCollection;
    }

    /*
     * Sets the keylogger boolean of the Customer Account
     * @param boolean keyLogger the keylogger of the Customer Account
     * 
     */
    public void setKeyLogger(boolean keyLogger) {
        this.keyLogger = keyLogger;
    }

    /*
     * Gets the keylogger boolean of the Customer Account
     * @return boolean the keylogger of the Customer Account
     * 
     */
    public boolean getKeyLogger() {
        return keyLogger;
    }

    /*
     * Sets the bank num of the Customer Account
     * @param String num the bank number of the Customer Account
     * 
     */
    public void setBankNumber(String num) {

        this.bankNumber = num;

    }

    /*
     * Gets the bank number of the Customer Account
     * @return String the bank number of the Customer Account
     * 
     */
    public String getBankNumber() {

        return bankNumber;

    }

    /*
     * Sets the card Expiry of the Customer Account
     * @param String cardExpiry the card expiry of the Customer Account
     * 
     */
    public void setCardExpiry(String cardExpiry) {

        this.cardExpiry = cardExpiry;

    }

    /*
     * Gets the card expiry of the Customer Account
     * @return String the card expiry of the Customer Account
     * 
     */
    public String getCardExpiry() {

        return cardExpiry;

    }
    
    /*
     * Sets the Credit Card CVV of the Customer Account
     * @param String cvv the cvv of the Customer Account
     * 
     */
    public void setCvv(String cvv) {

        this.cvv = cvv;

    }

    /*
     * Gets the Credit Card CVV of the Customer Account
     * @return String the cvv of the Customer Account
     * 
     */
    public String getCvv() {

        return cvv;

    }
    
    /*
     * Sets the password of the Customer Account
     * @param String password the password of the Customer Account
     * 
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /*
     * Gets the password of the Customer Account
     * @return String the password of the Customer Account
     * 
     */
    public String getPassword() {

        return password;

    }
    
    /*
     * Sets the date of birth of the Customer Account
     * @param String dob the date of birth of the Customer Account
     * 
     */
    public void setDob(String dob) {
        
        this.dob = dob;

    }


    /*
     * Gets the date of birth of the Customer Account
     * @return String the date of birth of the Customer Account
     * 
     */
    public String getDob() {

        return dob;

    }
    

    /*
     * Sets the gender of the customer
     * @param String gender the gender of the customer
     */
    public void setGender(String gender) {

        this.gender = gender;

    }

    /*
     * Gets the gender of the customer
     * @return String gender the gender of the customer
     */
    public String getGender() {

        return gender;

    }
    
    /*
     * Gets the address of the Customer Account
     * @return String the address of the Customer Account
     * 
     */
    public String getAddress() {

        return this.address;

    }

    /*
     * Set the address of the Customer Account
     * @param String Address the address of the Customer Account
     * 
     */
    public void setAddress(String Address) {
        this.address = Address;
    }

    /*
     * Gets the card number of the Customer Account
     * @return String the card number of the Customer Account
     * 
     */
    public void setCardNum(String cardNum) {

        this.cardNum = cardNum;

    }

    /*
     * Gets the card number of the Customer Account
     * @return String the card number of the Customer Account
     * 
     */
    public String getCardNum() {

        return cardNum;

    }

    /*
     * Sets the chequing balance of the Customer Account
     * @param double amount the chequing balance of the Customer Account
     * 
     */
    public void setChequing(double amount) {

        this.chequing = amount;

    }

    /*
     * Gets the chequing balance of the Customer Account
     * @return double the chequing balance of the Customer Account
     * 
     */
    public double getChequing() {

        return Math.round(chequing*100.0)/100.0;

    }

    /*
     * Sets the savings balance of the Customer Account
     * @param double amount the savings balance of the Customer Account
     * 
     */
    public void setSavings(double amount) {

        this.savings = amount;

    }

    /*
     * Gets the savings balance of the Customer Account
     * @return double the savings balance of the Customer Account
     * 
     */
    public double getSavings() {

        return Math.round(savings*100.0)/100.0;

    }

    /*
     * Gets the chequing transaction history of the Customer Account
     * @return double the chequing transaction history of the Customer Account
     * 
     */
    public ArrayList<Transaction> getChequingHist() {

        return chequingHist;

    }

    /*
     * Adds a transaction to the chequing transaction history of the Customer Account
     * @param Transaction chequingTransaction the transaction to be added to the chequing transaction history of the Customer Account
     * 
     */
    public void addChequing(Transaction chequingTransaction)
    {
        chequingHist.add(chequingTransaction);
    }

    /*
     * Gets the savings transaction history of the Customer Account
     * @return ArrayList<Transaction> the savings transaction history of the Customer Account
     * 
     */
    public ArrayList<Transaction> getSavingsHist() {

        return savingsHist;
    }

    /*
     * Adds a transaction to the savings transaction history of the Customer Account
     * @param Transaction savingTransaction the transaction to be added to the savings transaction history of the Customer Account
     * 
     */
    public void addSaving(Transaction savingTransaction) {

        savingsHist.add(savingTransaction);

    }

    /*
     * Gets the suspicious report history of the Customer Account
     * @return ArrayList<Report> the suspicious report history of the Customer Account
     * 
     */
    public ArrayList<Report> getReportSus() {

        return reportSus;

    }

    /*
     * Adds a suspicious report to the suspicious report history of the Customer Account
     * @param Report suspiciousReport the suspicious report to be added to the suspicious report history of the Customer Account
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
     * @param request: the request to be added to the request history of the Customer Account
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