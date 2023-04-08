import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.lang.Math;

/**
 * Class for the customer object. It extends people and contains all extra attributes the customer has, such as card
 * number and different notification preferences.
 */
public class CA extends People
{
    private String address;
    private String cardNum;
    private String password;
    private String gender;
    private String dob;
    private String cardExpiry;
    private String cvv;
    private String bankNumber; // For bank transfers
    private double chequing;
    private double savings;
    private final ArrayList<Transaction> chequingHist;
    private final ArrayList<Transaction> savingsHist;
    private final ArrayList<Report> reportSus;
    private final ArrayList<Request> requests;

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

    /**
     * Constructor for the Customer Object
     * @param firstName the first name of the Customer Account
     * @param lastName the last name of the Customer Account
     * @param phoneNum the phone number of the Customer Account
     * @param address the address of the Customer Account
     * @param gender the gender of the customer
     * @param dob the date of birth of the customer
     * @param email the email of the Customer Account
     * @param password the password of the Customer Account
     * @param cardNum the card number of the Customer Account
     * @param cardExpiry the card expiry of the Customer Account
     * @param cvv the cvv of the Customer Account
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

    /**
     * Sets the location tracking preferences of the Customer
     * @param locationServices boolean, true if location tracking is allowed, false otherwise
     * 
     */
    public void setLocationServices(boolean locationServices) {
        this.locationServices = locationServices;
    }

    /**
     * Gets the location services boolean of the Customer Account
     * @return boolean, the location services preferences of the Customer
     * 
     */
    public boolean getLocationServices() {
        return locationServices;
    }

    /**
     * Sets the request reply notification settings of the Customer Account.
     * 0 for don't notify, 1 for email notifications, and 2 for SMS notification
     * @param requestReplies an integer signifying the notification preferences of request replies of the Customer
     * 
     */
    public void setRequestReplies(int requestReplies) {
        this.requestReplies = requestReplies;
    }

    /**
     * Sets the report reply notification settings of the Customer Account.
     * 0 for don't notify, 1 for email notifications, and 2 for SMS notification
     * @param reportReplies an integer signifying the notification preferences of report replies of the Customer
     *
     */
    public void setReportReplies(int reportReplies) {
        this.reportReplies = reportReplies;
    }

    /**
     * Sets the newsletter subscription notification settings of the Customer Account.
     * 0 for don't notify, 1 for email notifications, and 2 for SMS notification
     * @param subscription an integer signifying whether the Customer wants to receive newsletter notifications
     *
     */
    public void setNewsletterSubscription(int subscription)
    {
        this.newsletterSubscription = subscription;
    }

    /**
     * Sets the 'payment of amount above threshold' notification settings of the Customer Account.
     * 0 for don't notify, 1 for email notifications, and 2 for SMS notification
     * @param bigPayment an integer signifying the notification preferences when it comes to large payments
     *
     */
    public void setBigPayment(int bigPayment)
    {
        this.bigPayment = bigPayment;
    }

    /**
     * Gets the request reply notification preference of the Customer
     * 0 for don't notify, 1 for email notifications, and 2 for SMS notifications
     * @return int: the request reply notification setting of the Customer
     * 
     */
    public int getRequestReplies() {
        return requestReplies;
    }

    /**
     * Gets the report reply notification preference of the Customer
     * 0 for don't notify, 1 for email notifications, and 2 for SMS notifications
     * @return int: the report reply notification setting of the Customer
     *
     */
    public int getReportReplies(){return reportReplies;}

    /**
     * Gets the newsletter subscription notification preference of the Customer
     * 0 for don't notify, 1 for email notifications, and 2 for SMS notifications
     * @return int: the newsletter subscription notification setting of the Customer
     *
     */
    public int getNewsletterSubscription(){return newsletterSubscription;}

    /**
     * Gets the 'payment above threshold' notification preference of the Customer
     * 0 for don't notify, 1 for email notifications, and 2 for SMS notifications
     * @return int: the large payment notification setting of the Customer
     *
     */
    public int getBigPayment(){return bigPayment;}

    /**
     * Sets the 'require data collection' boolean of the Customer for privacy settings.
     * true for allow required data collection, false for don't allow
     * @param requireDataCollection the 'require data collection' permission of the Customer Account
     * 
     */
    public void setRequireDataCollection(boolean requireDataCollection) {
        this.requireDataCollection = requireDataCollection;
    }

    /**
     * Gets the 'require data collection' boolean of the Customer Account.
     * true for allow required data collection, false for don't allow
     * @return boolean the 'require data collection' permission of the Customer
     * 
     */
    public boolean getRequireDataCollection() {
        return requireDataCollection;
    }

    /**
     * Sets the sensitive data collection boolean of the Customer for privacy settings.
     * true for allow sensitive data to be collected, false otherwise
     * @param sensitiveDataCollection the boolean to allow/not allow sensitive data to be collected
     *
     */
    public void setSensitiveDataCollection(boolean sensitiveDataCollection) {
        this.sensitiveDataCollection = sensitiveDataCollection;
    }


    /**
     * Gets the sensitive data collection boolean of the Customer
     * @return boolean the sensitive data collection of the Customer
     * 
     */
    public boolean getSensitiveDataCollection() {
        return sensitiveDataCollection;
    }

    /**
     * Sets the keylogger boolean of the Customer Account
     * true for allow keylogger tracking, false otherwise
     * @param keyLogger boolean to allow/not allow keylogger tracking
     * 
     */
    public void setKeyLogger(boolean keyLogger) {
        this.keyLogger = keyLogger;
    }

    /**
     * Gets the keylogger boolean of the Customer
     * true for allow keylogger tracking, false otherwise
     * @return boolean the keylogger preference of the Customer Account
     * 
     */
    public boolean getKeyLogger() {
        return keyLogger;
    }

    /**
     * Sets the bank number of the Customer
     * @param num the bank number of the Customer that is to be set
     * 
     */
    public void setBankNumber(String num) {

        this.bankNumber = num;

    }

    /**
     * Gets the bank number of the Customer
     * @return String the bank number of the Customer
     * 
     */
    public String getBankNumber() {

        return this.bankNumber;

    }

    /**
     * Sets the card Expiry of the Customer card
     * @param cardExpiry the card expiry of the Customer card
     * 
     */
    public void setCardExpiry(String cardExpiry) {

        this.cardExpiry = cardExpiry;

    }

    /**
     * Gets the card expiry of the Customer card
     * @return String the card expiry of the Customer card
     * 
     */
    public String getCardExpiry() {

        return cardExpiry;

    }
    
    /**
     * Sets the Card CVV of the Customer card
     * @param cvv the cvv of the Customer card
     * 
     */
    public void setCvv(String cvv) {

        this.cvv = cvv;

    }

    /**
     * Gets the Card CVV of the Customer
     * @return String the cvv of the Customer Account
     * 
     */
    public String getCvv() {

        return cvv;

    }
    
    /**
     * Sets the password of the Customer
     * @param password the password of the Customer
     * 
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * Gets the password of the Customer
     * @return String the password of the Customer
     * 
     */
    public String getPassword() {

        return password;

    }
    
    /**
     * Sets the date of birth of the Customer
     * @param dob the date of birth of the Customer
     * 
     */
    public void setDob(String dob) {
        
        this.dob = dob;

    }


    /**
     * Gets the date of birth of the Customer
     * @return String the date of birth of the Customer
     * 
     */
    public String getDob() {

        return dob;

    }
    

    /**
     * Sets the gender of the customer
     * @param gender the gender of the customer
     */
    public void setGender(String gender) {

        this.gender = gender;

    }

    /**
     * Gets the gender of the customer
     * @return String gender the gender of the customer
     */
    public String getGender() {

        return gender;

    }
    
    /**
     * Gets the address of the Customer
     * @return String the address of the Customer
     * 
     */
    public String getAddress() {

        return this.address;

    }

    /**
     * Set the address of the Customer
     * @param Address the address of the Customer
     * 
     */
    public void setAddress(String Address) {
        this.address = Address;
    }

    /**
     * Gets the card number of the Customer
     * @param cardNum the card number of the Customer
     * 
     */
    public void setCardNum(String cardNum) {

        this.cardNum = cardNum;

    }

    /**
     * Gets the card number of the Customer
     * @return String the card number of the Customer
     * 
     */
    public String getCardNum() {

        return cardNum;

    }

    /**
     * Sets the chequing account balance of the Customer
     * @param amount the chequing account balance of the Customer
     * 
     */
    public void setChequing(double amount) {

        this.chequing = amount;

    }

    /**
     * Gets the chequing account balance of the Customer
     * @return double the chequing account balance of the Customer
     * 
     */
    public double getChequing() {

        return Math.round(chequing*100.0)/100.0;

    }

    /**
     * Sets the savings account balance of the Customer
     * @param amount the savings account balance of the Customer
     * 
     */
    public void setSavings(double amount) {

        this.savings = amount;

    }

    /**
     * Gets the savings balance of the Customer
     * @return double the savings balance of the Customer
     * 
     */
    public double getSavings() {

        return Math.round(savings*100.0)/100.0;

    }

    /**
     * Gets the chequing transaction history of the Customer
     * @return Arraylist of Transaction objects which hold the chequing account transaction history of the Customer
     * 
     */
    public ArrayList<Transaction> getChequingHist() {

        return chequingHist;

    }

    /**
     * Adds a transaction to the chequing transaction history of the Customer
     * @param chequingTransaction the transaction to be added to the chequing transaction history of the Customer
     * 
     */
    public void addChequing(Transaction chequingTransaction)
    {
        chequingHist.add(0, chequingTransaction);
    }

    /**
     * Gets the savings account transaction history of the Customer
     * @return ArrayList of Transaction objects which hold the savings account transaction history of the Customer
     * 
     */
    public ArrayList<Transaction> getSavingsHist() {

        return savingsHist;
    }

    /**
     * Adds a transaction to the savings transaction history of the Customer
     * @param savingTransaction the transaction to be added to the savings account transaction history of the Customer
     * 
     */
    public void addSaving(Transaction savingTransaction) {

        savingsHist.add(0, savingTransaction);

    }

    /**
     * Gets the suspicious activity report history of the Customer
     * @return ArrayList of Report objects which represent all the suspicious activity reports the customer made
     * 
     */
    public ArrayList<Report> getReportSus() {

        return reportSus;

    }

    /**
     * Adds a suspicious activity report to the suspicious report history of the Customer
     * @param suspiciousReport the suspicious report to be added to the suspicious report history of the Customer
     * 
     */
    public void addReport(Report suspiciousReport) {

        reportSus.add(suspiciousReport);

    }

    /**
     * Gets the request history of the Customer
     * @return ArrayList of Request objects which represent the request history of the Customer
     * 
     */
    public ArrayList<Request> getRequests() {

        return requests;

    }

    /**
     * Adds a request to the request history of the Customer
     * @param request the request to be added to the request history of the Customer
     * 
     */
    public void addRequests(Request request) {

        requests.add(request);

    }

    /**
     * prints the Customer details
     */
    public void print() {
        System.out.printf("First Name: %-10s Last Name: %-10s | Email: %-25s | Password: %-20s " +
                " | Address: %-20s\n", getFirstName(), getLastName(), getEmail(), getPassword(), getAddress());
    }

    /**
     * prints the Customer Account history details
     */
    public void printHistory() {
        System.out.printf("%s %s -> ", getFirstName(), getLastName());
        System.out.printf("Chequing Transaction History: %-57s | Savings Transaction History: %-65s %n",
                chequingHist == null ? "No chequing transactions" : chequingHist.stream().map(Transaction::toString).collect(Collectors.joining(", ")),
                savingsHist == null ? "No savings transactions" : savingsHist.stream().map(Transaction::toString).collect(Collectors.joining(", "))
        );
    }
    
}   