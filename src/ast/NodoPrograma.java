package ast;

/**
 *
 * @author Yorman
 */
public class NodoPrograma extends NodoBase{
    private String nombre;
    private NodoBase variables;
    private NodoBase funciones;
    private NodoBase principal;

    public String getNombre() {
        return this.nombre;
    }

    public NodoBase getVariables() {
        return this.variables;
    }

    public NodoBase getFunciones() {
        return this.funciones;
    }

    public NodoBase getPrincipal() {
        return this.principal;
    }

    public NodoPrograma() {
        super();
        this.nombre = null;
        this.variables = null;
        this.funciones = null;
        this.principal = null;
    }

    public NodoPrograma(String nombre, NodoBase variables, NodoBase funciones, NodoBase principal) {
        this.nombre = nombre;
        this.variables = variables;
        this.funciones = funciones;
        this.principal = principal;
    }
    
    public NodoPrograma(String nombre, NodoBase principal) {
        this.nombre = nombre;
        this.principal = principal;
    }
    
    public NodoPrograma(String nombre, NodoBase variables,  NodoBase principal) {
        this.nombre = nombre;
        this.variables = variables;
        this.principal = principal;
    }
}
