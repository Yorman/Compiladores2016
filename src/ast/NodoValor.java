package ast;

public class NodoValor extends NodoBase {
	private int valor;
        private int tipo;

	public NodoValor(int valor) {
		super();
		this.valor = valor;
	}

    public NodoValor(int valor, int tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

	public NodoValor() {
		super();
	}
	
	public int getValor() {
		return valor;
	}

}
