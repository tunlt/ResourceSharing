/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.net.ssl.HttpsURLConnection;
import javax.json.JsonReader;

/**
 *
 * @author Tu
 */
public class verifyRecaptcha {
    public static final String SITE_VERIFY_URL =
            "https://www.google.com/recaptcha/api/siteverify";
    
    public static boolean verify(String gRecaptchaResponse){
        if(gRecaptchaResponse == null || gRecaptchaResponse.length() == 0)
            return false;
        
        try{
            // openconnet 
            URL verifyUrl = new URL(SITE_VERIFY_URL);
            HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();
            
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-us,en;q=5.0");
            // du lieu gui toi server
            String postParams ="secret="+ MyConstants.SECRET_KEY 
                    + "&response=" + gRecaptchaResponse;
            // send
            conn.setDoOutput(true);
            
           //lay output Stream cua ket noi toi server
           //ghi du lieu vao output stream co nghia la gui du lieu den server
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());
            
            outStream.flush();
            outStream.close();
            
            // ma tra ve tu phia server
            // de doc du lieu gui den server
            
            InputStream inputStream = conn.getInputStream();
            JsonReader jsonreader = Json.createReader(inputStream);
            JsonObject jsonO = jsonreader.readObject();
            jsonreader.close();
            
            boolean success = jsonO.getBoolean("success");
            return success;
            
        } catch (MalformedURLException ex) {
           ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
           return false;
        }
    }
}
