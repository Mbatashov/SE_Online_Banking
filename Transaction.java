package bank.core;

import java.io.Serializable;

public class Transaction implements Serializable
{

    String sender;
    String receiver;
    String accountFrom;
    double amount;
    int id;
    double senderRemaining;
    double receiverRemaining;

    // Transaction types:
    //  1- If sender = "Chequing" and receiver = "Savings", transfer funds between accounts locally, and vice versa between accounts
    //  2- If sender = "Current" and receiver = Valid Email, etransfer funds
    //  3- If sender = "Current" and receiver = 5-digit number, bank transfer to that account number
    public Transaction(String sender, String receiver, double amount, int id)
    {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.id = id;
    }

    /*
     * Gets the sender of the transaction
     * @return the sender of the transaction
     * 
     */
    public String getSender() {
        return sender;
    }

    /*
     * Sets the sender of the transaction
     * @param sender the sender of the transaction
     * 
     */
    public void setSender(String sender){
        this.sender = sender;
    }

    /*
     * Gets the receiver of the transaction
     * @return the receiver of the transaction
     * 
     */
    public String getReceiver() {
        return receiver;
    }

    /*
     * Sets the receiver of the transaction
     * @param receiver the receiver of the transaction
     * 
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /*
     * Gets the amount of the transaction
     * @return the amount of the transaction
     * 
     */
    public double getAmount() {
        return amount;
    }

    /*
     * Sets the amount of the transaction
     * @param amount: the amount of the transaction
     * 
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /*
     * Gets the id of the transaction
     * @return the id of the transaction
     * 
     */
    public int getId() {
        return id;
    }

    /*
     * Sets the id of the transaction
     * @param id the id of the transaction
     * 
     */
    public void setId(int id) {
        this.id=id;
    }

    /*
     * Gets the account the transaction was sent from
     * @return the account the transaction was sent from
     *
     */
    public String getAccountFrom()
    {
        return accountFrom;
    }
    /*
     * Sets the account the transaction was sent from
     * @param from: the account that initiated the transaction
     *
     */
    public void setAccountFrom(String from)
    {
        this.accountFrom = from;
    }

    /*
     * Gets the remaining amount the sender has in their account after the transaction
     * @return the remaining amount
     *
     */
    public double getSenderRemaining()
    {
        return senderRemaining;
    }
    /*
     * Sets the remaining amount the sender has in their account after the transaction
     * @param remaining: the amount remaining in the sender's account
     *
     */
    public void setSenderRemaining(double remaining)
    {
        this.senderRemaining = remaining;
    }

    /*
     * Gets the remaining amount the receiver has in their account after the transaction
     * @return the remaining amount
     *
     */
    public double getReceiverRemaining()
    {
        return receiverRemaining;
    }
    /*
     * Sets the remaining amount the receiver has in their account after the transaction
     * @param remaining: the amount remaining in the receiver's account
     *
     */
    public void setReceiverRemaining(double remaining)
    {
        this.receiverRemaining = remaining;
    }
    
}
