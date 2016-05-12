package ast;

/**
 *
 * @author katherine
 */
public class NodoReturn extends NodoBase {
    private NodoBase expresion;

    public NodoReturn(NodoBase expresion) {
        this.expresion = expresion;
    }

    public NodoReturn() {
        expresion=null;
    }

    
    public NodoBase getExpresion() {
        return expresion;
    }

    public void setExpresion(NodoBase expresion) {
        this.expresion = expresion;
    }
    
    
}
