package model.service;

public class PaypalService implements OnlinePaymentService{

	private static final double FEE_PERCENTAGE = 0.02;
	private static final double MONTHLY_INTEREST = 0.01;
	
	@Override
	public double paymentFee(double amount) {
		return amount += amount * FEE_PERCENTAGE;
	}

	@Override
	public double interest(double amount, int month) {
		return amount += amount * MONTHLY_INTEREST * month;
	}
	
}
