/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conexionconvenios;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;

/**
 *
 * @author CESS
 */
public class Soap {

    public Soap() {
    }
    
    public String getSoap(String url,String strRequest, String contentType, String respuesta, String action) {
        HttpClient httpClient = null;
        String result = null;
        try {
            // Crear la llamada al servidor
            httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url);
            StringEntity input = new StringEntity(strRequest);
            input.setContentType(contentType);
            postRequest.setEntity(input);
            postRequest.addHeader("SOAPAction", action);
            // Tratar respuesta del servidor
            HttpResponse response = httpClient.execute(postRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Error : Código de error HTTP : "
                        + response.getStatusLine().getStatusCode());
            }
            DocumentBuilderFactory factory
                    = DocumentBuilderFactory.newInstance();
            Document XMLDoc
                    = factory.newDocumentBuilder().parse(
                            response.getEntity().getContent());
            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression expr = xpath.compile("//" + respuesta);
            result = String.class.cast(expr.evaluate(XMLDoc,
                    XPathConstants.STRING));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
// Cierre de la conexión
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
        return result;
    }
    
    
}
