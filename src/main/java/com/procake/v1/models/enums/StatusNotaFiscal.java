package com.procake.v1.models.enums;

public enum StatusNotaFiscal {
	LANCADO(0), MODIFICADO(1);

	private final int value;

	private StatusNotaFiscal(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
