package payment_system;

public class CreditCardPayment extends Payment {
    private static final double MONTHLY_LIMIT = 500000;
    private static double availableLimit = MONTHLY_LIMIT;
    private static double outstandingBalance = 0;

    public CreditCardPayment(double amount) {
        super(amount);
    }

    @Override
    public void makePayment() throws PaymentException {
        if (getAmount() <= 0) {
            throw new PaymentException("Invalid credit card payment amount.");
        }

        if (getAmount() > availableLimit) {
            throw new PaymentException("Payment exceeds available credit card limit.");
        }

        availableLimit -= getAmount();
        outstandingBalance += getAmount();
        System.out.println("Paid ₹" + getAmount() + " using Credit Card.");
        System.out.println("Available credit card limit: ₹" + availableLimit);
        System.out.println("Outstanding credit card balance: ₹" + outstandingBalance);
    }

    public static void payCreditCardBill(BankAccount bankAccount) throws PaymentException {
        if (outstandingBalance == 0) {
            System.out.println("No outstanding credit card balance to pay.");
            return;
        }

        System.out.println("Attempting to pay credit card bill of ₹" + outstandingBalance + " from bank account...");
        bankAccount.withdraw(outstandingBalance);

        // On successful withdrawal
        availableLimit = MONTHLY_LIMIT;
        outstandingBalance = 0;
        System.out.println("Credit card bill paid successfully. Credit limit reset to ₹" + MONTHLY_LIMIT);
    }

    public static double getAvailableLimit() {
        return availableLimit;
    }

    public static double getOutstandingBalance() {
        return outstandingBalance;
    }
}