package com.floradex.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends Exception{

	private static final long serialVersionUID = 3813164289998523628L;
	private String messaggio;

	public NotFoundException() {

	}

	public NotFoundException(String messaggio) {
		super(messaggio);
		this.messaggio = messaggio;
	}


}
