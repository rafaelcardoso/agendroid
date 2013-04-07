package controller;

import model.Contact;

public class Controller {

	private static Controller instance = null;
	private Contact contact;

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact c) {
		this.contact = c;
	}

}
