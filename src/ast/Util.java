package ast;

public class Util {
	
	static int sangria = 0;
	static int contador=0;
	//Imprimo en modo texto con sangrias el AST
        
	public static void imprimirAST(NodoBase raiz){
            
		  sangria+=2;
		  while (raiz != null) {
		    printSpaces();
		    if (raiz instanceof  NodoIf)
		    	System.out.println("If");
		    else if (raiz instanceof  NodoRepeat)
		    	System.out.println("Repeat");
		    
		    else if (raiz instanceof  NodoAsignacion)
		    	System.out.println("Asignacion a: "+((NodoAsignacion)raiz).getIdentificador());

		    else if (raiz instanceof  NodoLeer)  
		    	System.out.println("Lectura: "+((NodoLeer)raiz).getIdentificador());

		    else if (raiz instanceof  NodoEscribir)
		    	System.out.println("Escribir");
                    else if (raiz instanceof NodoPrograma)
                          System.out.println("Programa");
                    else if (raiz instanceof NodoFor)
                          System.out.println("For");
                    else if (raiz instanceof NodoCall)
                          System.out.println("Llamado de funcion");
                    else if (raiz instanceof NodoDeclaracion)
                          System.out.println("Declaracion");
                    else if (raiz instanceof NodoFuncion)
                          System.out.println("Funcion");
                    else if (raiz instanceof NodoReturn)
                          System.out.println("Return");
                    else if (raiz instanceof NodoWhile)
                          System.out.println("While");
		    else if (raiz instanceof NodoOperacion
		    		|| raiz instanceof NodoValor
		    		|| raiz instanceof NodoIdentificador )
		    	imprimirNodo(raiz);
		    else System.out.println("Tipo de nodo desconocido");;
		    
		    /* Hago el recorrido recursivo */
		    if (raiz instanceof  NodoIf){
		    	printSpaces();
		    	System.out.println("**Prueba IF**");
		    	imprimirAST(((NodoIf)raiz).getPrueba());
		    	printSpaces();
		    	System.out.println("**Then IF**");
		    	imprimirAST(((NodoIf)raiz).getParteThen());
		    	if(((NodoIf)raiz).getParteElse()!=null){
		    		printSpaces();
		    		System.out.println("**Else IF**");
		    		imprimirAST(((NodoIf)raiz).getParteElse());
		    	}
		    }
		    else if (raiz instanceof  NodoRepeat){
		    	printSpaces();
		    	System.out.println("**Cuerpo REPEAT**");
		    	imprimirAST(((NodoRepeat)raiz).getCuerpo());
		    	printSpaces();
		    	System.out.println("**Prueba REPEAT**");
		    	imprimirAST(((NodoRepeat)raiz).getPrueba());
		    }
		    else if (raiz instanceof  NodoEscribir)
		    	imprimirAST(((NodoEscribir)raiz).getExpresion());
                    else if (raiz instanceof NodoLeer)
                          System.out.println(""+((NodoLeer)raiz).getIdentificador());
		    else if (raiz instanceof NodoOperacion){
		    	printSpaces();
		    	System.out.println("**Expr Izquierda Operacion**");
		    	imprimirAST(((NodoOperacion)raiz).getOpIzquierdo());
		    	printSpaces();
		    	System.out.println("**Expr Derecha Operacion**");		    	
		    	imprimirAST(((NodoOperacion)raiz).getOpDerecho());
		    }
                    else if (raiz instanceof NodoPrograma){
                       printSpaces();
                        System.out.println(" "+((NodoPrograma)raiz).getNombre());
                        if(((NodoPrograma)raiz).getVariables()!=null){
                                printSpaces();
		    		System.out.println("Variables");
		    		imprimirAST(((NodoPrograma)raiz).getVariables());
                        }
		    	if(((NodoPrograma)raiz).getFunciones()!=null){
		    		printSpaces();
		    		System.out.println("Bloque de Funciones");
		    		imprimirAST(((NodoPrograma)raiz).getFunciones());
                        }
                        printSpaces();
                        if(((NodoPrograma)raiz).getPrincipal()!=null){
                            printSpaces();
		    	    System.out.println("Principal");
                            imprimirAST(((NodoPrograma)raiz).getPrincipal());
                        }
		    	
                    }
                    else if (raiz instanceof NodoDeclaracion){
                       printSpaces();
                        System.out.println("**Expr Izquierda Operacion**");
                        imprimirAST(((NodoDeclaracion)raiz).getVariable());
		    	printSpaces();
		    	System.out.println("**Expr Derecha Operacion**");
                        System.out.println("Tipo "+((NodoDeclaracion)raiz).getTipo());
		    	
		       
                    }
                     else if (raiz instanceof NodoReturn){
		    	imprimirAST(((NodoReturn)raiz).getExpresion());
		    }
                    
                    else if (raiz instanceof NodoFuncion){
                       printSpaces();
                       System.out.println("Nombre "+((NodoFuncion)raiz).getNombre());
                        System.out.println("Tipo "+((NodoFuncion)raiz).getTipo());
                        if(((NodoFuncion)raiz).getArgs()!=null){
		    		contador++;
		    		System.out.println("Argumentos");
		    		imprimirAST(((NodoFuncion)raiz).getArgs());
		    		((NodoFuncion)raiz).setNum(contador);
		    		//System.out.println("Cantidad de Argumentos de Funcion: "+((NodoFuncion)raiz).getNum());
		    		
		    	}else if(((NodoFuncion)raiz).getArgs()==null && ((NodoFuncion)raiz).getSent()!=null)
		    		System.out.println("-> Sin Argumentos");
		    	if(((NodoFuncion)raiz).getSent()!=null){
		    		System.out.println("Sentencias");
			    	imprimirAST(((NodoFuncion)raiz).getSent());
		    	}
                        if(((NodoFuncion)raiz).getSiguiente()!=null){
		    		System.out.println("Sentencias");
			    	imprimirAST(((NodoFuncion)raiz).getSiguiente());
		    	}
                    }
                    else if (raiz instanceof NodoFor){
                        printSpaces();
                        System.out.println("**Asignacion For**");
		    	imprimirAST(((NodoFor)raiz).getAsignacion());
		    	printSpaces();
		    	System.out.println("**Paso For**");
		    	System.out.println(" "+((NodoFor)raiz).getPaso());
		    	printSpaces();
		    	System.out.println("**Bloque For**");
		    	imprimirAST(((NodoFor)raiz).getCuerpo());
		    	
                    }
                    else if (raiz instanceof NodoAsignacion){
                        printSpaces();
                        System.out.println("**Identificador**");
		    	System.out.println(""+((NodoAsignacion)raiz).getIdentificador());
		    	printSpaces();
		    	System.out.println("**Expresion**");
		    	imprimirAST(((NodoAsignacion)raiz).getExpresion());
		    	printSpaces();
                        if (((NodoAsignacion)raiz).getPosicion()!=null){
                            System.out.println("**Posicion**");
                            imprimirAST(((NodoAsignacion)raiz).getPosicion());
                        }
		    	
                    }
                    else if (raiz instanceof NodoCall){
                        printSpaces();
                        System.out.println("**Nombre**");
		    	System.out.println(""+((NodoCall)raiz).getNombre());
		    	printSpaces();
                        if(((NodoCall)raiz).getArgumentos()!=null){
                                System.out.println("**Argumentos**");
                                imprimirAST(((NodoCall)raiz).getArgumentos());
                                printSpaces();
                        }
		    	
                    }
                    else if (raiz instanceof NodoWhile){
                        printSpaces();
                        System.out.println("**Prueba While**");
		    	imprimirAST(((NodoWhile)raiz).getPrueba());
		    	printSpaces();
		    	System.out.println("**Cuerpo While**");
		    	imprimirAST(((NodoWhile)raiz).getCuerpo());
                    }
		    raiz = raiz.getHermanoDerecha();
		  }
		  sangria-=2;
		}

/* Imprime espacios con sangria */
static void printSpaces()
{ int i;
  for (i=0;i<sangria;i++)
	  System.out.print(" ");
}

/* Imprime informacion de los nodos */
static void imprimirNodo( NodoBase raiz )
{
	if(	raiz instanceof NodoRepeat
		||	raiz instanceof NodoLeer
		||	raiz instanceof NodoEscribir  ){
		System.out.println("palabra reservada: "+ raiz.getClass().getName());
	}
	
	if(	raiz instanceof NodoAsignacion )
		System.out.println(":=");
	
	if(	raiz instanceof NodoOperacion ){
		tipoOp sel=((NodoOperacion) raiz).getOperacion();
		if(sel==tipoOp.menor)
			System.out.println("<"); 
		if(sel==tipoOp.igual)
			System.out.println("=");
		if(sel==tipoOp.mas)
			System.out.println("+");
		if(sel==tipoOp.menos)
			System.out.println("-");
		if(sel==tipoOp.por)
			System.out.println("*");
		if(sel==tipoOp.entre)
			System.out.println("/");
                if(sel==tipoOp.menor_igual)
			System.out.println("<=");
                if(sel==tipoOp.mayor_igual)
			System.out.println(">=");
                if(sel==tipoOp.mayor)
			System.out.println(">");
                if(sel==tipoOp.and)
			System.out.println("and");
                if(sel==tipoOp.or)
			System.out.println("or");
                if(sel==tipoOp.dif)
			System.out.println("<>");
	}

	if(	raiz instanceof NodoValor ){
		System.out.println("NUM, val= "+ ((NodoValor)raiz).getValor());
	}

	if(	raiz instanceof NodoIdentificador ){
		String variable;
                variable = "ID, nombre= "+ ((NodoIdentificador)raiz).getNombre();
                if (((NodoIdentificador)raiz).getTamano()!=null)
                    variable += " vector, tamano= " + ((NodoIdentificador)raiz).getTamano();
		System.out.println(variable);
                
	}

}


}
