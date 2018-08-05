package com.ubs.opsit.utils;

public enum LampType {

	RED("R"), YELLOW("Y");

	private String value;

	LampType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
