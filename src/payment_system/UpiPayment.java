package payment_system;

public class UpiPayment extends Payment {
    private BankAccount bankAccount;

    public UpiPayment(double amount, BankAccount bankAccount) {
        super(amount);
        this.bankAccount = bankAccount;
    }

    @Override
    public void makePayment() throws PaymentException {
        if (getAmount() <= 0) {
            throw new PaymentException("Invalid UPI payment amount.");
        }

        bankAccount.withdraw(getAmount());
        System.out.println("Paid ₹" + getAmount() + " using UPI.");
        System.out.println("Remaining bank balance: ₹" + bankAccount.getBalance());
    }
}