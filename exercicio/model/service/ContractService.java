package model.service;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		super();
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer month) {
		Date d = contract.getDate();
		
		double contractValue = contract.getTotalValue() / month;

		for (int i=1; i<=month; i++) {	
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.MONTH, i);
			Date aux = cal.getTime();
			
			double interestMonth = onlinePaymentService.interest(contractValue, i);
			double taxPayment = onlinePaymentService.paymentFee(interestMonth);
			contract.addInstallment(new Installment(aux, taxPayment));
			
			cal.setTime(d);
		}
	}
}
