package payment_system;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Deposited ₹" + amount + ". New balance: ₹" + balance);
    }

    public void withdraw(double amount) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new PaymentException("Insufficient balance.");
        }
        balance -= amount;
        System.out.println("Withdrew ₹" + amount + ". Remaining balance: ₹" + balance);
    }
}