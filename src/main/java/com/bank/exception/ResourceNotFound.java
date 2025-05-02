package com.bank.exception;

public class ResourceNotFound extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFound(String mensaje) {
		super(mensaje);
	}
}
