/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Taller2;

/**
 *
 * @author favya
 */
public interface IApp 
{
    
    public void leerComunas(ListaComunas listaComunas);
    public void leerCensistas(ListaPersonas listaPersonas, ListaCensistas listaCensistas, ListaComunas listaComunas);
    public void leerPersonas(ListaPersonas listaPersonas, ListaCensistas listaCensistas, ListaComunas listaComunas);
    public void AsociarListas(ListaPersonas lp, ListaCensistas lc, ListaComunas lco);
    public void CensoRF1(ListaComunas listaComunas);
    public void DatosCensistasRF2(ListaCensistas lc, ListaPersonas lp);
    public void CensistaRF3(ListaCensistas lc, ListaComunas lco);
    public void DatosComunaRF4(ListaComunas listaComunas, int CodigoComuna);
    
    
    
}
