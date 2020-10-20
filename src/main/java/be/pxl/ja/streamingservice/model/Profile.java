package be.pxl.ja.streamingservice.model;


import be.pxl.ja.streamingservice.exception.InvalidDateException;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.Queue;

public class Profile{

	private String name;
	private LocalDate dateOfBirth;
	private String avatar;
	private ArrayDeque<Content> recentlyWatched = new ArrayDeque<Content>();
	private ArrayDeque<Content> currentlyWatching = new ArrayDeque<Content>();
	private ArrayDeque<Content> myList = new ArrayDeque<Content>();

	public Profile(String name, String avatar) {
		this.name = name;
		this.avatar = avatar;
	}

	public Profile(String name) {
		this.name = name;
		this.avatar = "profile1";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		if (dateOfBirth.isAfter(LocalDate.now())) {
			throw new InvalidDateException(dateOfBirth, "date of birth", "No date of birth in future allowed.");
		}
		this.dateOfBirth = dateOfBirth;
	}

	public String getAvatar() {
		return avatar;
	}

	public int getAge() {
		if (dateOfBirth == null) {
			return 0;
		}
		return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDateTime.now());
	}

	public void startWatching(Content content){
		for(Content c : currentlyWatching){
			if(!c.equals(content)){
				currentlyWatching.addFirst(content);
			}
		}
	}

	public void finishWatching(Content content){
		currentlyWatching.removeFirstOccurrence(content);
		for(Content r : recentlyWatched){
			if(!r.equals(content)){
				recentlyWatched.addFirst(content);
			}
		}
	}

	public ArrayDeque<Content> getRecentlyWatched() {
		return recentlyWatched;
	}

	public ArrayDeque<Content> getCurrentlyWatching() {
		return currentlyWatching;
	}

	public ArrayDeque<Content> getMyList() {
		return myList;
	}

	public void addToMyList(Content content){
		for(Content m : myList){
			if(!m.equals(content)){
				myList.addFirst(content);
			}
		}
	}


	public boolean allowedToWatch(Content content) {
		return content.getMaturityRating().getMinimumAge() <= getAge();
	}

	public void removeFromMyList(Content content){
		myList.removeFirstOccurrence(content);
	}

    public boolean isInMyList(Content content) {
		boolean isInList = false;
		for(Content m : myList){
			if(!m.equals(content)){
				isInList = false;
			}else{
				isInList = true;
			}
		}
		return isInList;
    }
}
