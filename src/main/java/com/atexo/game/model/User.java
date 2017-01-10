package com.atexo.game.model;


public class User implements Cloneable{

	private String candidateId;

	private String firstName;

	private String lastName;

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

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

	@Override
	public String toString() {
		return "User [candidateId=" + candidateId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}


	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch(CloneNotSupportedException e) {
			e.printStackTrace(System.err);
		}
		return o;
	}

}