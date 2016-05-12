
package ast;

/**
 *
 * @author katherine
 */
public class NodoFor extends NodoBase{
    private NodoBase asignacion;
    private Integer paso;
    private NodoBase cuerpo;

    public NodoFor(NodoBase asignacion, Integer paso, NodoBase cuerpo) {
        this.asignacion = asignacion;
        this.paso = paso;
        this.cuerpo = cuerpo;
    }

    public NodoFor() {
		super();
		this.cuerpo = null;	
		this.paso = null;
		this.asignacion = null;
	}
    public NodoBase getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(NodoBase asignacion) {
        this.asignacion = asignacion;
    }

    public Integer getPaso() {
        return paso;
    }

    public void setPaso(Integer paso) {
        this.paso = paso;
    }

    public NodoBase getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(NodoBase cuerpo) {
        this.cuerpo = cuerpo;
    }
    
    
}
