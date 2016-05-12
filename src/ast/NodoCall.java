
package ast;

/**
 *
 * @author katherine
 */
public class NodoCall extends NodoBase {
    private String nombre;
    private NodoBase argumentos;

    public NodoCall(String nombre, NodoBase argumentos) {
        this.nombre = nombre;
        this.argumentos = argumentos;
    }

    public NodoCall(String nombre) {
        this.nombre = nombre;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public NodoBase getArgumentos() {
        return argumentos;
    }

    public void setArgumentos(NodoBase argumentos) {
        this.argumentos = argumentos;
    }
    
    
}
