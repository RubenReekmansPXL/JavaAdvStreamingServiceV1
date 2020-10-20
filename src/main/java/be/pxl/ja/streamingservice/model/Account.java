package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.TooManyProfilesException;
import be.pxl.ja.streamingservice.util.PasswordUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
	public int MINIMUM_PASSWORD_STRENGTH;
	private String email;
	private String password;
	private PaymentInfo paymentInfo;
	private StreamingPlan streamingPlan;
	private List<Profile> profiles = new ArrayList<>();

	public Account(String email, String password) {
		if(email.equals("") || email.equals(null) || password.equals("") || password.equals(null)){
			throw new IllegalArgumentException();
		}else{
			this.email = email;
			setPassword(password);
			profiles.add(new Profile("Profile 1"));
			streamingPlan = StreamingPlan.BASIC;
		}

	}

	public void setStreamingPlan(StreamingPlan streamingPlan) {
		this.streamingPlan = streamingPlan;
	}

	public void addProfile(Profile profile) throws TooManyProfilesException {
		int maxProfile = streamingPlan.getNumberOfScreens();
		if(profiles.size() < maxProfile){
			profiles.add(profile);
		}else{
			throw new TooManyProfilesException(profile, " can not be added to profiles, to many profiles for your streamingsplan");
		}
	}

	public String getEmail() {
		return email;
	}

	public boolean verifyPassword(String password) {
		return PasswordUtil.isValid(password, this.password);
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPassword(String password) {
		if(PasswordUtil.calculateStrength(password) < 5){
			throw new IllegalArgumentException();
		}else{
			this.password = PasswordUtil.encodePassword(password);
		}
	}

	public int getNumberOfProfiles() {
		return profiles.size();
	}

	public List<Profile> getProfiles() {
		//todo weet niet of dit juist is
		Collections.sort(this.profiles, Collections.reverseOrder());
		return profiles;
	}
}
