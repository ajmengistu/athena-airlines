package com.airline.athena.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationError;

@Service
public class ProcessPaymentService {
	/* ********Payment Credentials ************ */
	private static final String MERCHANT_ID = "64fmbfx4mt6pc69j";
	private static final String PUBLIC_KEY = "cnt5rnqt5zxcmcbf";
	private static final String PRIVATE_KEY = "e533d5e2074d2bdad3e78fb988e000f6";

	/* ********Payment Credentials ************ */

	public BraintreeGateway getBrainTreeGateway() {
		return new BraintreeGateway(Environment.SANDBOX, MERCHANT_ID, PUBLIC_KEY, PRIVATE_KEY);
	}

	public boolean processPayment(ModelMap modelMap, String paymentMethodNonce) {
		Result<Transaction> result = this.processTransaction(modelMap, paymentMethodNonce);
		if (result.isSuccess()) {
			Transaction transaction = result.getTarget();
			System.out.println("Success!: " + transaction.getId());
			String transactionId = transaction.getId();
			System.out.println(transactionId);

			return true;

		} else if (result.getTransaction() != null) {
			Transaction transaction = result.getTransaction();
			System.out.println("Failed!: " + transaction.getId());
			System.out.println("Error processing transaction:");
			System.out.println("  Status: " + transaction.getStatus());
			System.out.println("  Code: " + transaction.getProcessorResponseCode());
			System.out.println("  Text: " + transaction.getProcessorResponseText());
			return false;
		} else {
			for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
				System.out.println("Attribute: " + error.getAttribute());
				System.out.println("  Code: " + error.getCode());
				System.out.println("  Message: " + error.getMessage());
			}
			return false;
		}
	}

	public Result<Transaction> processTransaction(ModelMap modelMap, String paymentMethodNonce) {
		TransactionRequest req = new TransactionRequest().amount(new BigDecimal(modelMap.get("totalCost").toString()))
				.paymentMethodNonce(paymentMethodNonce).options().submitForSettlement(true).done();
		Result<Transaction> result = this.getBrainTreeGateway().transaction().sale(req);
		System.out.println(result.getMessage());
		return result;
	}

}
