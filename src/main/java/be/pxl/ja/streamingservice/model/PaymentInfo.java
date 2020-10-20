package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.InvalidDateException;

import java.time.LocalDate;

public class PaymentInfo {
	private String firstName;
	private String lastName;
	private CreditCardNumber creditCardNumber;
	private LocalDate expirationDate;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		LocalDate now = LocalDate.now();
		if(expirationDate.compareTo(now) < 31){
			throw new InvalidDateException(expirationDate, "expiration date", "\n" + "The credit card must be valid for at least another month");
		}else{
			this.expirationDate = expirationDate;
		}
	}

	public void setCreditCardNumber(CreditCardNumber creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
}
