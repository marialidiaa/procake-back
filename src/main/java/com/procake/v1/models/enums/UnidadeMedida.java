package com.procake.v1.models.enums;

public enum UnidadeMedida {
	KG(1), G(2), MG(3), ML(4), L(5), UN(6);

	private final int value;

	private UnidadeMedida(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
