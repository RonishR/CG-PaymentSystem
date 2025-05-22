package payment_system;

import java.util.Scanner;

public class PaymentSystem {
    public static void processPayment(Payment payment) throws PaymentException {
        payment.makePayment();

        if (payment instanceof CreditCardPayment) {
            System.out.println("Credit Card payment processed.");
        } else if (payment instanceof UpiPayment) {
            System.out.println("UPI payment processed.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount bankAccount = new BankAccount(100000);

        boolean exit = false;

        while (!exit) {
        	System.out.println("\n==== Welcome to Ronish's bank ====");
            System.out.println("1. Pay via Credit Card");
            System.out.println("2. Pay via UPI");
            System.out.println("3. Deposit to Bank Account");
            System.out.println("4. Withdraw from Bank Account");
            System.out.println("5. Check Bank Account Balance");
            System.out.println("6. Pay Credit Card Bill from Bank Account");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter payment amount: ");
                        double ccAmount = sc.nextDouble();
                        processPayment(new CreditCardPayment(ccAmount));
                        break;

                    case 2:
                        System.out.print("Enter payment amount: ");
                        double upiAmount = sc.nextDouble();
                        processPayment(new UpiPayment(upiAmount, bankAccount));
                        break;

                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double deposit = sc.nextDouble();
                        bankAccount.deposit(deposit);
                        break;

                    case 4:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawal = sc.nextDouble();
                        bankAccount.withdraw(withdrawal);
                        break;

                    case 5:
                        System.out.println("Current Bank Account balance: ₹" + bankAccount.getBalance());
                        System.out.println("Credit Card available limit: ₹" + CreditCardPayment.getAvailableLimit());
                        System.out.println("Credit Card outstanding balance: ₹" + CreditCardPayment.getOutstandingBalance());
                        break;

                    case 6:
                        CreditCardPayment.payCreditCardBill(bankAccount);
                        break;

                    case 7:
                        exit = true;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (PaymentException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }

        sc.close();
    }
}