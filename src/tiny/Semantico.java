
package tiny;
import ast.*;
import java.util.ArrayList;
/**
 *
 * @author katherine
 */
public class Semantico {
        private TablaSimbolos tablaSimbolos;	
	public boolean debug = true;
	private boolean anyError = false;
        
	public void recorrerArbol(NodoBase raiz){		
		while (raiz != null) {
			/* Hago el recorrido recursivo */
			if (raiz instanceof NodoIdentificador){	 	
				 verificarIdentificador(raiz);		    		
		    }	
		    else if (raiz instanceof  NodoIf)
		    	verificarIf(raiz);
		    else if (raiz instanceof  NodoRepeat)
		    	verificarRepeat(raiz);
		    else if (raiz instanceof  NodoAsignacion)	    	
		    	verificarAsignacion(raiz);		    	
		    else if (raiz instanceof  NodoEscribir)
		    	verificarEscribir(raiz);
		    else if (raiz instanceof  NodoLeer)
		    	verificarLeer(raiz);			
		    else if (raiz instanceof NodoOperacion)		    	
		    	verificarOperacion(raiz);
		    else if (raiz instanceof NodoDeclaracion) 
		    	verificarDeclaracion(raiz); 
		    else if (raiz instanceof NodoFor) 
		    	verificarFor(raiz);
		    else if (raiz instanceof NodoFuncion)
		    	verificarFuncion(raiz);		     
		    else if (raiz instanceof NodoCall){ 
		    	verificarCallFuncion(raiz);	
                    }else if (raiz instanceof NodoWhile){
                        verificarWhile(raiz);
                    }     
		    
		    else if (raiz instanceof NodoReturn)
	    		verificarReturn(raiz);
		    else if (raiz instanceof NodoPrograma) {
		    	if(((NodoPrograma)raiz).getFunciones()!=null){
		    		recorrerArbol(((NodoPrograma)raiz).getFunciones());
		    	}	
		    	
		    	recorrerFuncion(((NodoPrograma)raiz).getPrincipal());
		    	recorrerArbol(((NodoPrograma)raiz).getPrincipal());
		    }  		    	
		    raiz = raiz.getHermanoDerecha();
	  }
		
	}
        
        private boolean recorrerFuncion(NodoBase raiz){
		boolean ban=false;
		while (raiz != null && !ban) {
			if(raiz instanceof NodoReturn)
			    ban=true; 				   
			raiz = raiz.getHermanoDerecha();
		}
		return ban;
	}
        private void verificarWhile(NodoBase raiz){
        
        
        }
        private void verificarReturn(NodoBase raiz) {
		NodoReturn n = (NodoReturn)raiz;
		String tipo=tablaSimbolos.getTipoFuncion();
		if (tipo==null)
			tipo="Procedure";
		if(tipo!="Procedure"){
			//Si entro es una funcion con return exp
			if((n.getExpresion())!=null){
					//Si entro si es correcto es return exp
					   if (comprobarTipo(n.getExpresion())!=tipo)
				    		printError("El tipo de dato retornado no corresponde. Debe ser tipo "+tipo);
				   }else{
					   //Error no tiene exp y debe tene expresion
					   printError(" La expresion return no es compatible con el tipo de funcion. Debe retornar un dato de tipo "+tipo);
				   }	
			   }else{
				   if(n.getExpresion()!=null)
					   //error tiene exp y no debe retornar nada
					   printError(" La expresion return no es compatible con el tipo de funcion");
			   }
	}
        
      private void verificarIf(NodoBase nodo){
    	if (comprobarTipo(((NodoIf)nodo).getPrueba()) != "Boolean")
    		printError("No se puede probar la expresion en el if");
    	
    	recorrerArbol(((NodoIf)nodo).getParteThen());
    	if(((NodoIf)nodo).getParteElse()!=null){
    		recorrerArbol(((NodoIf)nodo).getParteElse());
    	}  			
	} 
	
      private void verificarRepeat(NodoBase nodo){
    	recorrerArbol(((NodoRepeat)nodo).getCuerpo());
    	if (comprobarTipo(((NodoRepeat)nodo).getPrueba()) != "Boolean")
    		printError("No se puede probar la expresion en el repeat");		
	}
	
	private void verificarAsignacion(NodoBase nodo){
		//Compruebo que la variable a asignar ha sido declara en el ambito		
		//recorrerAsignacion(nodo);
	}

	private void verificarEscribir(NodoBase nodo){
		recorrerArbol(((NodoEscribir)nodo).getExpresion());
	}
      
        private void verificarLeer(NodoBase nodo){
		String identificador = ((NodoLeer)nodo).getIdentificador();	
	    
    	/*if (verificarExistenciaDeVariable(identificador)){	
    		tablaSimbolos.setInizializacion(ultimoAmbito, identificador, true);
    		String tipo = tablaSimbolos.getTipo(ultimoAmbito, identificador);
        	boolean ifArray = tablaSimbolos.getIfArray(ultimoAmbito, identificador);	
        	if(ifArray){
        		if ( ((NodoLeer)nodo).getPosicion() == null) {
        			printError("El identificador " + identificador + " es vector y debe ser llamado usado: "+identificador+"[]");
        		} else{
        			recorrerArbol(((NodoLeer)nodo).getPosicion());
        			if (comprobarTipo(((NodoLeer)nodo).getPosicion()) != "Int") {
        				printError("El indice del vector "+identificador+" debe ser tipo Int");
        			}
        		}
        	} else {
        		if( ((NodoLeer)nodo).getPosicion() != null ){
        			printError("El identificador " + identificador + " no ha sido declarado como vector");
        		}
        	}
    	}	    
	*/	
	}
	
	private void verificarDeclaracion(NodoBase nodo){
//		recorrerArbol(((NodoDeclaracion)nodo).getVariable());
	}
	
	private void verificarOperacion(NodoBase nodo){
		NodoBase tipoIzquierdo 	= (NodoBase) ((NodoOperacion)nodo).getOpIzquierdo();
		NodoBase tipoDerecho 	= (NodoBase) ((NodoOperacion)nodo).getOpDerecho();

		// Comprobacion de tipo en expresion
		if( comprobarTipo(tipoDerecho) != comprobarTipo(tipoIzquierdo) )
			printError("Tipos diferentes");	
	}
	
	private void verificarFor(NodoBase raiz){
    	NodoBase nodoAsignacion = ((NodoFor)raiz).getAsignacion();
    	Integer nodoPaso = ((NodoFor)raiz).getPaso();
    	
    	recorrerArbol(nodoAsignacion); // Ir a recorrer para marcar inicializacion
    	if (comprobarTipo(((NodoAsignacion)nodoAsignacion).getExpresion()) != "Int")
    		printError("No se puede probar la expresion de asignacion en la sentencia for");
    
    /*	recorrerArbol(nodoPaso); // Ir a recorrer para marcar inicializacion
    	if (comprobarTipo(((NodoAsignacion)nodoPaso).getExpresion()) != "Int")
    		printError("No se puede probar la expresion paso en la sentencia for");		    	
    	*/
    	recorrerArbol(((NodoFor)raiz).getCuerpo());	
	}
	
	private void verificarFuncion(NodoBase nodo){
    //	ultimoAmbito = ((NodoFuncion)nodo).getNombre();	// Cambio el ambito cuando entro a una funcion 
    //	tablaSimbolos.getPrimerArgumento(ultimoAmbito);
    	//Buscar el return
    /*	if(( (((NodoFuncion)nodo).getTipo())=="Int" || (((NodoFuncion)nodo).getTipo())=="Boolean") 
    			&& !recorrerFuncion(((NodoFuncion)nodo).getSent(),((NodoFuncion)nodo).getTipo(),((NodoFuncion)nodo).getNombre()))
    		printError("La funcion "+((NodoFuncion)nodo).getNombre()+" debe contener una clausula RETURN");
    	else if(((NodoFuncion)nodo).getTipo()=="Void"){
    		recorrerFuncion(((NodoFuncion)nodo).getSent(),((NodoFuncion)nodo).getTipo(),((NodoFuncion)nodo).getNombre());
    	}
*/    	//incializar argumentos
    	if(((NodoFuncion)nodo).getArgs()!=null){
    		//lamar a inicializar los argumentos
    		inicializarargumentos(((NodoFuncion)nodo).getArgs());
    	}
    	recorrerArbol(((NodoFuncion)nodo).getSent());    	
	}
	private void inicializarargumentos(NodoBase nodo){
		NodoDeclaracion n = (NodoDeclaracion)nodo;
		//tablaSimbolos.setInizializacion(ultimoAmbito, ((NodoIdentificador)n.getVariable()).getNombre(), true);
    	if((nodo.getHermanoDerecha())!= null)
			inicializarargumentos((nodo.getHermanoDerecha()));
	}
        
        
	private void verificarCallFuncion(NodoBase nodo){
	
        ArrayList<String> arrayArgumentos 	= new ArrayList<String>();
    	String nombreFuncion 				= ((NodoCall)nodo).getNombre();
    	NodoBase argumentos 				= ((NodoCall)nodo).getArgumentos();
    	
    	// Si la funcion ha sido declarada
    /*	if(verificarExistenciaDeFuncion(nombreFuncion)){
    		// Si la funcion tiene argumentos
	    	if(argumentos != null){
	    		recorrerArbol((argumentos));			    		
		    	recorrerArgumentos((argumentos),arrayArgumentos);
		    	// Si la funcion recibe argumentos
		    	if (tablaSimbolos.getArrayArguments( nombreFuncion) != null) {

			    	if (!tablaSimbolos.getArrayArguments(nombreFuncion).equals(arrayArgumentos) ){ 
			    		printError("Llamada a funcion "+nombreFuncion+" invalida, debe ser "+nombreFuncion+ "(" +tablaSimbolos.getArrayArguments( nombreFuncion) +",)");
			    	}		
		    	} else {
		    		printError("Llamada a funcion "+nombreFuncion+" invalida, debe ser "+nombreFuncion+ "()");
		    	}
	    	} else if (tablaSimbolos.getArrayArguments( nombreFuncion) != null ){
	    		if(tablaSimbolos.getArrayArguments( nombreFuncion).size() != 0 )
	    			printError("LLamada a funcion "+nombreFuncion+" invalida, faltan argumanetos, debe ser "+nombreFuncion+ "(" +tablaSimbolos.getArrayArguments( nombreFuncion) +",)");
	    	}
    	} 
    	
    	*/
	}

        
        private boolean verificarIdentificador(NodoBase nodo){
		String nombre = ((NodoIdentificador)nodo).getNombre();	
		boolean retorno = true;
		/*if (verificarExistenciaDeVariable(nombre)){    	
			boolean esArray = tablaSimbolos.getIfArray(ultimoAmbito, nombre);
	    	if(esArray){
	    		if(((NodoIdentificador)nodo).getExpresion() == null )
	    			printError("El identificador " + nombre + " es vector y debe ser declarado usado: "+nombre+"[]");
	    		else {
	    			recorrerArbol(((NodoIdentificador)nodo).getExpresion());
	    			if (comprobarTipo(((NodoIdentificador)nodo).getExpresion()) != "Int") {
	    				printError("El indice del vector "+nombre+" debe ser tipo Int");
	    			}
	    		}
	    	} else {
	    		if( ((NodoIdentificador)nodo).getExpresion() != null ){
	    			printError("El identificador " + nombre + " no ha sido declarado como vector");
	    		}
	    	}
	    	
		} else
			retorno = false;
		*/
		return retorno;
	}
        
        private String comprobarTipo(NodoBase nodo){
		if (nodo instanceof NodoOperacion){
			
			String tipoIzquierdo 	= comprobarTipo(((NodoOperacion)nodo).getOpIzquierdo());
			String tipoDerecho 		= comprobarTipo(((NodoOperacion)nodo).getOpDerecho());			
			tipoOp operador 		= ((NodoOperacion)nodo).getOperacion();
			
			if(operador == tipoOp.and || operador == tipoOp.or){
				// Si el operador es and o or ambos operando deben ser Boolean			
				if( tipoIzquierdo == "Boolean" && tipoDerecho == "Boolean" )
					return "Boolean";	
				else{
					printError("Operandos de diferente tipo");
					return "TyperEror";
				}
				
			} else if(operador == tipoOp.igual || operador == tipoOp.dif){
				// Si el operador es igual o diferente debe ser ambos operandos Boolean o Int 
				if( tipoIzquierdo == "Boolean" && tipoDerecho == "Boolean" )
					return "Boolean";	
				else if(tipoIzquierdo=="Int" && tipoDerecho=="Int")
					return "Boolean";
				else {
					printError("Operandos de diferente tipo");
					return "TypeError";
				}
			} else if( operador 	== tipoOp.mas 
					|| operador == tipoOp.menos
					|| operador == tipoOp.por
					|| operador == tipoOp.entre){ 
				// Si el operador es + - * / ambos operandos tipos deben ser enteros
				if( tipoIzquierdo == "Integer" && tipoDerecho == "Integer")									
					return "Integer";
				else{
					printError("Operandos de diferente tipo");
					return "TypeError";
				}
				
			} else {
				// Si no son ninguno de los anteriores son < <= > >= y tienen que ser ambos operandos Int
				if( tipoIzquierdo == "Integer" && tipoDerecho == "Integer")
					return "Boolean";
				else {
					printError("Operandos de diferente tipo");
					return "TypeError";
				}
			}
		}
		else if(nodo instanceof NodoCall){
			// Si es una funcion verificar que ha sido declarada y retornar tipo buscando en la tabla de simbolos
			String nombreFuncion =  ((NodoCall)nodo).getNombre();
			/*verificarCallFuncion(nodo);
			if(verificarExistenciaDeFuncion(nombreFuncion))
				return tablaSimbolos.getTipoFuncion( nombreFuncion );	
			else 
				return "TypeError";
	*/
		}		
		else if(nodo instanceof NodoIdentificador){
		/*	// Si es un identificador verificar que ha sido declarada en el ambito y retornar tipo buscando en la tabla de simbolos		
		    String identificador = ((NodoIdentificador)nodo).getNombre();
		    verificarIdentificador(nodo);
		    verificarInicializacion(identificador);
		    if( verificarExistenciaDeVariable(identificador)){
				String tipoIdentificador = tablaSimbolos.getTipo(ultimoAmbito, identificador);
				return tipoIdentificador;
		    }
		    else
		    	return "TypeError";*/
		}
		else if(nodo instanceof NodoValor){	
			// Si es nodo valor retornar tipo de valor e.g. 1,2,3,4 o true o false
			Integer tipo= ((NodoValor)nodo).getTipo();
			if(tipo==0)
				return "Int";			
			if(tipo==1)
				return "Boolean";	
		}
		
		return "TypeError";
	}
      
      private void printError(Object chain){				
		System.err.println("[Error Semantico]: "+chain);
		this.anyError = true;
	}
}
