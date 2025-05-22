package payment_system;

abstract class Payment {
	private double amount;
	
	public Payment(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public abstract void makePayment() throws PaymentException;

}
