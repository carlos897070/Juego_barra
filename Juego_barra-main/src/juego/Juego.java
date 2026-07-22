package juego;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Image;

import entorno.Entorno;
import entorno.InterfaceJuego;
import entorno.Herramientas;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	
	private Entorno entorno;
	private Image fondoPrincipal;
	private Image barra;
	int puntaje, nivel, puntajeAnteriorParaNivel;;
	
	//Tamaño del entorno
	int ancho = 1200;
	int alto = 650;
	
	// Variables de instancia del rectángulo
    private double x, y, dx, dy;
    private int anchoRect = 200;
    private int altoRect = 70;
    Color colorRec;
	
    //Variables de instancia del círculo
	private double xc;
	private double yc;
	private double dxc;
	private double dyc;
	private double d;
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", ancho, alto);
		
		// Inicializar lo que haga falta para el juego
		// ...
		
		this.fondoPrincipal = Herramientas.cargarImagen("fondoMadera.jpg");
		
		//Puntaje y nivel inicial
		this.puntaje = 0;
		this.nivel = 0;
		this.puntajeAnteriorParaNivel = 0;
		
		 // Posición inicial rectángulo
        this.x = ancho/2;
        this.y = alto;

        // Velocidad inicial rectángulo
        this.dx = 12;
        this.dy = 28;
        
        //Angulo inicial rectángulo
        //this.r = 0;
        
        //Color inicial rectángulo
        this.colorRec = new Color(100, 10, 20);
        
        //Posicion inicial del círculo
        this.xc = 200;
        this.yc = 200;
        
        //Velocidad inicial del circulo
        this.dxc = 4;
        this.dyc = 3;
        
        //Diametro inicial del circulo
        this.d = 40;

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		
		 Color colorC = new Color(240, 240, 240);
		 
		 entorno.dibujarImagen(fondoPrincipal, ancho/2, alto/2, 0, 1);
		 
		 entorno.cambiarFont("Tahoma", 20, Color.WHITE, entorno.NEGRITA);
		 this.entorno.escribirTexto("Puntaje: " + puntaje, ancho-130, 25);
		 this.entorno.escribirTexto("Nivel: " + nivel, ancho-130, 50);
		 
		 this.entorno.dibujarRectangulo(this.x, this.y, anchoRect, altoRect, 0, colorRec);
		 this.entorno.dibujarCirculo(this.xc, this.yc, d, colorC);
		 
		
		 
	
	     // Verificar bordes horizontales
	    /* if (x - anchoRect/2 < 0 || x + anchoRect/2 > ancho) {
	         dx = -dx;  // Rebote horizontal
	         colorRec = new Color(255, 255, 255);
	     }
	
	     // Verificar bordes verticales
	     if (y - altoRect/2 < 0 || y + altoRect/2 > alto) {
	         dy = -dy;  // Rebote vertical
	         colorRec = new Color(0, 0, 0);
	     }*/
	     
	     //Movimiento del rectángulo
	     
	     if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
	    	 if (ancho - (this.x + anchoRect/2) < 10)
	    	 {
	    		 this.x += 0;
	    	 }else {
	    		 this.x += dx;
	    	 }
	    	 
		  }
		 if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			if(this.x - anchoRect /2 < 10)
			{
				this.x += 0;
			}else
			{
				this.x += -dx;
			}
			 
		 }
		 

		 /*
			if(entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				carlos.mover(0, 3);
			}
			if(entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				carlos.mover(0, -3)*/
	     
	     //Actualizar posición del círculo
	      xc += dxc;
          yc += dyc;
          
          
          //Verificar bordes horizontales del circulo para la colision
          if(xc - d/2 < 0 || xc + d/2 > ancho)
          {
        	  dxc = -dxc;
          }
          if(yc - d/2 < 0 )
          {
        	  dyc = -dyc;
          }
       // Verificar colisión del círculo con el rectángulo
          boolean colisionaHorizontalmente = 
              (xc - d/2 > x - anchoRect/2) && (xc + d/2 < x + anchoRect/2);

          boolean colisionaVerticalmente =
              (yc + d/2 >= y - altoRect/2) && (yc + d/2 <= y + altoRect/2);

          // Si hay colisión (parte inferior del círculo toca el rectángulo)
          if (colisionaHorizontalmente && colisionaVerticalmente && dyc > 0) {
              dyc = -dyc; // Rebote vertical
              yc = y - altoRect/2 - d/2; // Corrige posición para evitar quedarse pegado
              puntaje += 10;
              if(puntaje % 50 == 0 && puntaje > 0 && puntaje != puntajeAnteriorParaNivel)
              {
            	  nivel += 1;
            	  dxc = dxc > 0 ? dxc + 1 : dxc - 1;  // mantener dirección
            	  dyc = dyc > 0 ? dyc + 1 : dyc - 1;
                  puntajeAnteriorParaNivel = puntaje;
                  
            	  
              }
          }
          
          if(this.yc > alto)
 		 {
        	 entorno.cambiarFont("Tahoma", 60, Color.WHITE, entorno.NEGRITA);
 			 this.entorno.escribirTexto("Game Over", ancho/2 - 170, alto/2);
 			 entorno.cambiarFont("Tahoma", 20, Color.WHITE, entorno.NEGRITA);
 			this.entorno.escribirTexto("Puntaje final: " + puntaje, ancho/2 - 80, alto/2 + 40);
 			 this.x += 0;
 			 
 		 }
	      
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
