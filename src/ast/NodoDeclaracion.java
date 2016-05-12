
package ast;

/**
 *
 * @author katherine
 */
public class NodoDeclaracion extends NodoBase{
    private String tipo;
    private NodoBase variable;

    public NodoDeclaracion() {
    }

    public NodoDeclaracion(String tipo, NodoBase variable) {
        this.tipo = tipo;
        this.variable = variable;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public NodoBase getVariable() {
        return variable;
    }

    public void setVariable(NodoBase variable) {
        this.variable = variable;
    }
    
    
}
