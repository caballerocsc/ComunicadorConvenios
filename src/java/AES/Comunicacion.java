/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AES;

import conexionconvenios.Rest;
import conexionconvenios.Soap;
import java.util.regex.Pattern;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import transformar.TxSoa;

/**
 * REST Web Service
 *
 * @author CESS
 */
@Path("taller")
public class Comunicacion {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Comunicacion
     */
    public Comunicacion() {
    }

    /**
     * Retrieves representation of an instance of AES.Comunicacion
     * @param data
     * @return an instance of java.lang.String
     */
    @POST
    //@Path("{data}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces("application/json")
    public String getJson( String data) {
        System.out.println("entrada: "+data);
        //param0 tipo
        //param1 url  
        //param2 recurso
        //param3 verbo 
        //param4 plantilla
        //param5 referenciafact
        //param6 valorfact
        //param7 contentType
        //param8 respuesta
        String respuesta = "Ha ocurrido un error, por favor contacte al administrador";
        String[] parametros = data.split(Pattern.quote("|"));
        if(parametros[0].equals("SOAP")){
            Soap clienteSoap = new Soap();
            TxSoa tx = new TxSoa();
            respuesta = clienteSoap.getSoap(parametros[1], tx.getXMLFormatter(parametros[4], parametros[5], parametros[6]), parametros[7], parametros[8], parametros[2]);
        }if(parametros[0].equals("REST")){
            Rest clienteRest = new Rest();
            respuesta
                =clienteRest.getRest(parametros[1], parametros[5], parametros[3],parametros[4], parametros[5], Integer.parseInt(parametros[6]),parametros[7]);
        }
        
        return respuesta;
    }
    
    @GET
    @Produces("application/json")
    public String helloWorld(){
        return "hello world";
    }

    
}
