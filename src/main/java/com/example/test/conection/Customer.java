package com.example.test.conection;

public class Customer {

    private int id;
    private String firstName, password, email;

    public Customer(int id, String firstName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", password=" + password + ", email=" + email + "]";
	}



}