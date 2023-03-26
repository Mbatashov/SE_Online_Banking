import java.io.Serializable;

public class Transaction implements Serializable
{
    String sender;
    String receiver;
    double amount;
    int id;

    // Transaction types:
    //  1- If sender = "Chequing" and receiver = "Savings", transfer funds between accounts locally, and vice versa between accounts
    //  2- If sender = "Current" and receiver = Valid Email, etransfer funds from chequing
    //  3- If sender = "Current" and receiver = 5-digit number, bank transfer to that account number from chequing
    //  Add a function called receive funds that loops and checks if it's a local transfer, and if it's not it brings up
    //  an "Are you sure you wanna transfer to a customer not in BCS". Otherwise, a make sure message to transfer to someone
    //  within BCS.
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
     * @param amount the amount of the transaction
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
    
}
