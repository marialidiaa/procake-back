package com.procake.v1.models.enums;

public enum Tipo {
	LANCADO(0), BAIXADO(1), ESTORNADO_LANCAMENTO(2), ESTORNADO_BAIXA(3);

	private final int value;

	private Tipo(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
