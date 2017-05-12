
package Taller2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdIn;
import ucn.StdOut;


public class App implements IApp {
     
    @Override
    
    public void leerPersonas(ListaPersonas listaPersonas, ListaCensistas listaCensistas, ListaComunas listaComunas) {
        try {
            ArchivoEntrada in = new ArchivoEntrada("Personas.txt");
            while(!in.isEndFile()){
                Registro reg = in.getRegistro();
                String nomb = reg.getString();
                int edad = reg.getInt();
                String comu = reg.getString();
                String ocup = reg.getString();
                int cantf = reg.getInt();
                String censis = reg.getString();
  
                Persona p = new Persona(nomb,edad,comu,ocup,cantf, censis);
                listaPersonas.ingresarPersonas(p);  
                
                /* Intento 1
                Censista censista = listaCensistas.buscarPorNombre(censis);
                if (censista != null){
                    p.setCensista(censista);   
                    censista.getListaPersonas().ingresarPersonas(p);
                }
               
                Comuna comuna = listaComunas.buscarPorNombre(comu);
                if (comuna != null){
                    p.setComuna(comuna);
                    comuna.getListaPersonas().ingresarPersonas(p);
                }  */           
                        
            }
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }
    }
    

    @Override   
    public void leerComunas (ListaComunas listaComunas) {
        try {
            ArchivoEntrada in = new ArchivoEntrada("Comunas.txt");
                while(!in.isEndFile()){
                Registro reg = in.getRegistro();
   
                int cod = reg.getInt();
                String nom = reg.getString();
                int numc = reg.getInt();
                int canth = reg.getInt();

                Comuna co = new Comuna(cod,nom,numc,canth);
                listaComunas.ingresarComunas(co); 
                
            }
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }
    }  
    
    @Override
    public void leerCensistas (ListaPersonas listaPersonas, ListaCensistas listaCensistas, ListaComunas listaComunas) {
        try {
            ArchivoEntrada in = new ArchivoEntrada("Comunas.txt");
                while(!in.isEndFile()){
                Registro reg = in.getRegistro();
   
                int cod = reg.getInt();
                String nom = reg.getString();
                String comu = reg.getString();

                Censista c = new Censista(cod,nom,comu);
                listaCensistas.ingresarCensistas(c);
                
                /*
                Comuna comuna = listaComunas.buscarPorNombre(comu);
                if (comuna != null){
                    c.setComuna(comuna);  
                    comuna.getListaCensistas().ingresarCensistas(c);
                    
                }*/
            }
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }
    }   
    
    
    @Override
    public void AsociarListas(ListaPersonas lp, ListaCensistas lc, ListaComunas lco){
                       
        for (int i=0; i<lp.getCantPersonas(); i++ ){
            
            Persona p = lp.getPersonaI(i);
            String NComuna = p.getNcomuna();
            String NCensista = p.getNCensista();
            
            // Del atributo Nombre Comuna de persona se busca en la lista y se asocia
            Comuna ComunaP = lco.buscarPorNombre(NComuna);
            p.setComuna(ComunaP);
            
            // Del atributo Nombre Censista de persona se busca en la lista y se asocia
            Censista CensistaP = lc.buscarPorNombre(NCensista);
            p.setCensista(CensistaP);
           // CensistaP.getListaPersonas().ingresarPersonas(p);
            
        }
      
        for (int i=0; i<lc.getCantCensistas(); i++ ){
        
                Censista c = lc.getCensistaI(i);
                String NCensista = c.getNombre();
                String NComuna = c.getNcomuna();
                
                Comuna ComunaC = lco.buscarPorNombre(NComuna);
                c.setComuna(ComunaC);     
                
                for (int j=0; j<lp.getCantPersonas(); j++ ){
                
                   Persona p = lp.getPersonaI(j);
                   String Ncp = p.getNCensista();
                   if(NCensista.equals(Ncp)){
                   
                      c.getListaPersonas().ingresarPersonas(p);
                   }

                }                
                
              //  ComunaC.getListaCensistas().ingresarCensistas(c);
           }
        
        for (int i=0; i<lco.getCantComunas(); i++ ){
                  
            Comuna co = lco.getComunaI(i);
            String Nc = co.getNombre();
            
            for (int j=0; j<lp.getCantPersonas(); j++ ){
                
                Persona p = lp.getPersonaI(j);
                String Ncp = p.getNcomuna();
                if (Ncp.equals(Nc)){
                
                    co.getListaPersonas().ingresarPersonas(p);
                }
             }                       
        }
     }
   
    
    @Override
    public void CensoRF1 (ListaComunas lco)  {
           
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
         fichero = new FileWriter("Censo.txt");
         pw = new PrintWriter(fichero);  
         
            for(int i=0; i < lco.getCantComunas(); i++){
                
                Comuna co = lco.getComunaI(i);
                pw.println("Comuna: " + co.getNombre());
                int ff = co.getListaPersonas().getCantPersonas();
                pw.println(ff);
                
                for(int j = 0; j < co.getListaPersonas().getCantPersonas(); j++){
                    
                    Persona p = co.getListaPersonas().getPersonaI(j);
                    
                    pw.println(" Nombre: " + co.getNombre());
                    pw.println(" Edad: " + p.getEdad());
                    pw.println(" Ocupacion: " + p.getOcupacion());    
                    pw.println(" Cantidad Familia: " + p.getCantidadFamilia());
                  //  pw.println(" Censista: " + p.getCensista().getNombre());
 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }  
    }    
    

    @Override
    public void DatosCensistasRF2(ListaCensistas lc, ListaPersonas lp){
         
        int SumaFamilia = 0;
        int ContMayor3 = 0;
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
         fichero = new FileWriter("DatosCensistas.txt");
         pw = new PrintWriter(fichero);  
           
         for(int i=0; i< lc.getCantCensistas(); i++){
             
            Censista c = lc.getCensistaI(i);
            String NombreC = c.getNombre();
            pw.println("Censista: " + c.getNombre());          
            
            // de aca se agrego
            for (int k=0; k < lp.getCantPersonas() ; k++){
                
                Persona p = lp.getPersonaI(k);
                String CensP = p.getNCensista();
                
                if(NombreC.equals(CensP)){
                pw.println(" " + p.getNombre());
                pw.println(" " + p.getEdad());      
                
                // Contador de familias
                int cont = p.getCantidadFamilia();
                SumaFamilia = cont + SumaFamilia;
             
                // Si es mayor que 3 se guarda en su respectivo contador
                if(cont > 3){
                ContMayor3 ++;            
                }
                                
                
                }
            
            
            }
            
            }
                     int Porcentaje = (ContMayor3/SumaFamilia)*100;
            pw.println("Total habitantes: " + SumaFamilia);    

            pw.print("Porcentaje Familias mayor 3 integrantes ");          
            pw.println((String.format("%.2s", Porcentaje)));
                  
            
            
          /*  for(int j=0; j < c.getListaPersonas().getCantPersonas() ; j++){
                 
                Persona p = c.getListaPersonas().getPersonaI(j);
                pw.println(" " + p.getNombre());
                pw.println(" " + p.getEdad());
             
                // Contador de familias
                int cont = p.getCantidadFamilia();
                SumaFamilia = cont + SumaFamilia;
             
                // Si es mayor que 3 se guarda en su respectivo contador
                if(cont > 3){
                ContMayor3 ++;            
                }
                
            }
            int Porcentaje = (ContMayor3/SumaFamilia)*100;
            pw.println("Total habitantes: " + SumaFamilia);    

            pw.print("Porcentaje Familias mayor 3 integrantes ");          
            pw.println((String.format("%.2s", Porcentaje)));*/
             
             
         
         
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }     
    }
 
    @Override
    public void CensistaRF3(ListaCensistas lc, ListaComunas lco){
        
        StdOut.println("Ingrese el código del cliente para ver sus Datos asociados: ");
        int cd = StdIn.readInt();
        Censista c = lc.buscarPorCodigo(cd);
         
        if (c != null){
         
        StdOut.println("Nombre: " + c.getNombre());
        StdOut.println("Codigo: " + c.getCodigo());
        StdOut.println("Comuna: " + c.getNcomuna());
         
        int TotalCensadosCen = c.getListaPersonas().getCantPersonas();
         
        for(int i = 0; i < TotalCensadosCen ; i++){
             
             Persona p = c.getListaPersonas().getPersonaI(i);
             
             StdOut.println("Nombre: " + p.getNombre());
             StdOut.println("Edad: " + p.getEdad());
             StdOut.println("Comuna: " + p.getComuna());
             StdOut.println("Ocupacion: " + p.getOcupacion());
             StdOut.println("Cantidad Familia: " + p.getCantidadFamilia());
         }
         
         // De acuerdo al nombre de la comuna asociada del censista se busca la comuna en la lista general
         String NcomunaCens = c.getComuna().getNombre();
         Comuna co = lco.buscarPorNombre(NcomunaCens);
         
         int TotalCensadosCom = co.getListaPersonas().getCantPersonas();
         
         int Porcentaje = (TotalCensadosCen/TotalCensadosCom)*100;
         
         StdOut.print("Porcentaje Censados Comuna: " );
         StdOut.println((String.format("%.2s", Porcentaje)));
         
         }else{
         StdOut.println("El código ingresado no se encuentra en la base de datos...");
         }
         
     }

    @Override
    public void DatosComunaRF4(ListaComunas lc, int CodigoComuna) {
        
        int Cont = 0;
        
        Comuna co = lc.buscarPorCodigo(CodigoComuna);
        if (co != null){
        StdOut.println("Nombre: " + co.getNombre());
        StdOut.println("Codigo: " + co.getCodigo());
        StdOut.println("Cantidad Total Habitantes: " + co.getCanthabitantes());
         
        for(int i=0; i < co.getListaPersonas().getCantPersonas() ; i++){
             
            int CantPersonas = co.getListaPersonas().getPersonaI(i).getCantidadFamilia();
            Cont = Cont + CantPersonas;
        }
        if (Cont == co.getCanthabitantes()){
            StdOut.println("El censo fue realizado exitosamente");

        }else{           
            StdOut.println("El censo NO fue realizado satisfactoriamente");
        
        }  
        }else{
        StdOut.println("El código ingresado no está en la base de datos.");
        }
    }
}
