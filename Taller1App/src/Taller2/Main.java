/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Taller2;

import static Taller2.App.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdIn;
import ucn.StdOut;

/**
 *
 * @author favya
 */
public class Main {
    
 public static void main(String[] args) throws IOException  {
 
    ListaCensistas lc = new ListaCensistas(1000);
    ListaPersonas lp = new ListaPersonas(1000);
    ListaComunas lco = new ListaComunas(1000);
  
     
    App app = new App();
     
    app.leerCensistas(lp, lc, lco);
    app.leerPersonas(lp, lc, lco);
    app.leerComunas(lco);
    app.AsociarListas(lp, lc, lco);
     
  
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
  
    StdOut.println(" 1.- Datos Censo." );
    StdOut.println(" 2.- Datos Censistas. " );
    StdOut.println(" 3.- Datos Censista Según Código. " );
    StdOut.println(" 4.- Datos Comuna Según Código. " );
    StdOut.println(" 5.- Salir. " );
    
    int op;
        do{  
            StdOut.println("\nIngrese una opción del menú: " );
            op = Integer.parseInt(bf.readLine()); 
            switch(op){
                
                case 1: 
                    app.CensoRF1(lco); 
                    break;
                case 2:             
                    app.DatosCensistasRF2(lc,lp);
                    break;
                case 3:
                    app.CensistaRF3(lc,lco);
                    break;
                case 4:
                    StdOut.println("\nIngrese código comuna: " );
                    int cod1 =StdIn.readInt();
                    app.DatosComunaRF4(lco, cod1);
                    break;
                case 5:
                    StdOut.println("Usted está saliendo del menú..." );
                    break;    
                default:
                    StdOut.println("OPCION NO VALIDA" );
                    break;               
            }
            }while( op != 5 );
  } 
}

