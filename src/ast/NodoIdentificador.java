package ast;

public class NodoIdentificador extends NodoBase {
	private String nombre;
        private Integer tamano;
	private NodoBase siguiente;
	private NodoBase expresion;

    public Integer getTamano() {
        return tamano;
    }

    public void setTamano(Integer tamano) {
        this.tamano = tamano;
    }

    public NodoBase getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoBase siguiente) {
        this.siguiente = siguiente;
    }

    public NodoBase getExpresion() {
        return expresion;
    }

    public void setExpresion(NodoBase expresion) {
        this.expresion = expresion;
    }

	public NodoIdentificador(String nombre) {
		super();
		this.nombre = nombre;
	}

    public NodoIdentificador(String nombre, NodoBase siguiente, NodoBase expresion) {
        this.nombre = nombre;
        this.siguiente = siguiente;
        this.expresion = expresion;
    }

    public NodoIdentificador(String nombre, NodoBase siguiente) {
        this.nombre = nombre;
        this.siguiente = siguiente;
    }

    public NodoIdentificador(String nombre, Integer tamano) {
        this.nombre = nombre;
        this.tamano = tamano;
    }

	public NodoIdentificador() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

}
