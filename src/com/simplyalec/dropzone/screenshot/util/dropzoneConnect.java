package com.simplyalec.dropzone.screenshot.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Alec Dusheck on 6/8/2017.
 */
public class dropzoneConnect {
    public static boolean checkCreds(String usr, String pass) {
        return true;
    }

    public static void uploadFile(File file) {
        try {
            //Try 2..
            CloseableHttpClient httpclient = HttpClients.createDefault();

            try {
                HttpPost httppost = new HttpPost("http://localhost:8080/");

                FileBody bin = new FileBody(file);
                StringBody username = new StringBody(config.username);
                StringBody password = new StringBody(config.password);

                HttpEntity reqEntity = MultipartEntityBuilder.create()
                        .addPart("filetoupload", bin)
                        .addPart("username", username)
                        .addPart("password", password)
                        .build();

                httppost.setEntity(reqEntity);
                System.out.println("executing request " + httppost.getRequestLine());
                CloseableHttpResponse response = httpclient.execute(httppost);
                try {
                    System.out.println("----------------------------------------");
                    System.out.println(response.getStatusLine());
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        System.out.println("Response content length: " + resEntity.getContentLength());
                    }
                    EntityUtils.consume(resEntity);
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        }catch (IOException e){
            messages.displayMessage("There was an error while connect to Dropzone.");
            e.printStackTrace();
            return;
        }
    }
}
