package com.endava.entities;

public class Image {

	private String name;

	public Image() {

	}

	public Image(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name.toString();
	}
}
