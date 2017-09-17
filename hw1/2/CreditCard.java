import java.util.Calendar;

public class CreditCard {
    private String creditCardNumber;
    private String cardHolderName;
    private String bank;
    private int limit;
    private double balance;

    // Constructor
    public CreditCard(String creditCardNumber, String cardHolderName,
                      String bank, int limit, double balance) {
        this.creditCardNumber = creditCardNumber;
        this.cardHolderName = cardHolderName;
        this.bank = bank;
        this.limit = limit;
        this.balance = balance;
    }

    //Accessor Methods
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getBank() {
        return bank;
    }

    public int getLimit() {
        return limit;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "CreditCard [creditCardNumber=" + creditCardNumber
                + ", cardHolderName='" + cardHolderName + "', bank = '" + bank
                + "', limit = " + limit + ", balance = " + balance + "]";

    }


    /***********************************
     ASSIGNMENT STARTS HERE
     ***********************************/

    /* 
     * chargeIt() takes price as an argument and returns whether
     * transaction was successful or not
     * 
     * If post-balance doesn't exceed limit,
     * charge account and return true.
     * Returns false otherwise
     *
     * Precond: price is positive
     */
    public boolean chargeIt(double price) {
        if (balance + price > limit) return false;
        balance += price;
        return true;
    }


    /*
     * Precond: amount is positive
     */
    public void payment(double amount) {
        //check for late fee (15th of month)
        Calendar c = Calendar.getInstance();
        if (c.get(Calendar.DAY_OF_MONTH) > 15) {
            //change late fee here
            double late_fee = 0.05 * balance;
            balance += late_fee;
        }
        balance -= amount;
    }


    public static void main(String[] args) {
        CreditCard cc = new CreditCard("5498300036895870",
                "John Doe", "Visa",
                1000, 0.0);
        System.out.println("Testing chargeIt...");
        System.out.println(cc);

        System.out.println("\ncc.chargeIt(700.00) -> " + cc.chargeIt(700.0));
        System.out.println("balance = " + cc.getBalance());

        System.out.println("\ncc.chargeIt(700.00) -> " + cc.chargeIt(700.0));
        System.out.println("balance = " + cc.getBalance());

        System.out.println("\ncc.chargeIt(300.00) -> " + cc.chargeIt(300.0));
        System.out.println("balance = " + cc.getBalance());

        System.out.println("\nRunning cc.payment(243.92)...");
        cc.payment(243.92);
        System.out.println("balance = " + cc.getBalance());

        //wait 15 days time for testing late/not-late payment functionality
    }
}
