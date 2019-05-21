package conexionconvenios;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.json.simple.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CESS
 */
public class Rest {

    public Rest() {
    }

    public String getRest(String url, String resource, String verb, String plantilla, 
            String ref, int val, String contentType) {
        // TODO code application logic here
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        RequestSpecification httpRequest = RestAssured.given();
        Response response = null;
        //acceder a las plantillas
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(plantilla);
            prop.load(is);
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        // Acceder a las propiedades por su nombre
        System.out.println("Propiedades por nombre:");
        System.out.println("-----------------------");
        String prop1 = prop.getProperty("propiedad1");
        System.out.println(prop1);
        String prop2 = prop.getProperty("propiedad2");
        System.out.println(prop2);
        String resp = prop.getProperty("respuesta");
        System.out.println(resp);
        String retorno = "";
        switch (verb) {
            case "GET": {
                response = httpRequest.request(Method.GET, resource);
                String responseBody = response.getBody().asString();
                System.out.println("respuesta: " + response.statusCode());
                System.out.println("Response Body is =>  " + responseBody);
                retorno = response.jsonPath().get(resp).toString();
                break;
            }
            case "POST": {
                // We can add Key - Value pairs using the put method
                JSONObject requestParams = new JSONObject();
                requestParams.put(prop1, ref);
                //requestParams.put(prop2, val);
                request.header("Content-Type", contentType);
                request.body(requestParams.toJSONString());
                response = request.post(resource);
                String responseBody = response.getBody().asString();
                System.out.println("respuesta: " + response.statusCode());
                System.out.println("Response Body is =>  " + responseBody);
                //System.out.println("respuesta: " + response.jsonPath().get("idFactura"));
                retorno = response.jsonPath().get(resp);
                break;
            }
            case "DELETE": {
                // We can add Key - Value pairs using the put method
                JSONObject requestParams = new JSONObject();
                requestParams.put(prop1, ref);
                //requestParams.put(prop2, val);
                request.header("Content-Type", contentType);
                request.body(requestParams.toJSONString());
                response = request.delete(resource);
                String responseBody = response.getBody().asString();
                System.out.println("respuesta: " + response.statusCode());
                System.out.println("Response Body is =>  " + responseBody);
                //System.out.println("respuesta: " + response.jsonPath().get("idFactura"));
                retorno = response.jsonPath().get(resp);
                break;
            }
        }
        return retorno;
    }

}
