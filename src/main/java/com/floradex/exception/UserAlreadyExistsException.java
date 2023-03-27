package com.floradex.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 7805440189960691596L;
	private String messaggio;

	public UserAlreadyExistsException() {

	}

	public UserAlreadyExistsException(String messaggio) {
		super(messaggio);
		this.messaggio = messaggio;
	}

}
