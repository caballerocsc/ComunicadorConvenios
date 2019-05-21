/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transformar;

import conexionconvenios.Soap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author CESS
 */
public class TxSoa {

    public TxSoa() {
    }
    
    public String getXMLFormatter(String ruta,String ref, String val){
        String strRequest = "";
        File f = new File(ruta);
        Scanner s;
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
            strRequest = strRequest+s.nextLine()+"\n";
            // 
            // Aquí el tratamiento de la línea
            //
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        strRequest=strRequest.replace("${ref}", ref);
        strRequest=strRequest.replace("${val}", val);
        System.out.println(strRequest);
        return strRequest;
    }
    
}
