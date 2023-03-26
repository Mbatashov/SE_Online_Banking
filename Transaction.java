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
    public Transaction(String sender, String receiver, double amount, int id)
    {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.id = id;
    }

    //Getter for sender, receiver, and amount
    public String getSender()
    {
        return sender;
    }
    public void setSender(String sender){
        this.sender = sender;
    }
    public String getReceiver()
    {
        return receiver;
    }
    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }
    public double getAmount()
    {
        return amount;
    }
    public void setAmount(double amount)
    {
        this.amount = amount;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
}
