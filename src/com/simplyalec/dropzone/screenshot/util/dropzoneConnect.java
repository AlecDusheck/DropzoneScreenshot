package com.simplyalec.dropzone.screenshot.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import java.io.File;

/**
 * Created by Alec Dusheck on 6/8/2017.
 */
public class dropzoneConnect {
    public static boolean checkCreds(String usr, String pass) {
        return true;
    }

    public static void uploadFile(File file) {
        try {

            //Setup HttpClient
            //Don't care.
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

            //Create post
            //TODO switch this to https when I get that done.
            HttpPost httppost = new HttpPost("http://dropzone.simplyalec.com/upload");

            //Don't care.
            MultipartEntity mpEntity = new MultipartEntity();

            //Define vars to send to dropzone
            ContentBody cbFile = new FileBody(file, "image/png");
            ContentBody cbUsername = new StringBody(config.username);
            ContentBody cbPassword = new StringBody(config.password);

            //Actually add vars to multipartentity
            mpEntity.addPart("userfile", cbFile);
            mpEntity.addPart("username", cbUsername);
            mpEntity.addPart("password", cbPassword);

            //Add vars to post.
            httppost.setEntity(mpEntity);

            //TODO remove this when done | Log it
            System.out.println("executing request " + httppost.getRequestLine());

            //Execute it.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity resEntity = response.getEntity();

            //TODO remove this when done | Print debug info
            System.out.println(response.getStatusLine());

            //Print the req if we did it, else consume.
            if (resEntity != null) {
                System.out.println(EntityUtils.toString(resEntity));
            }
            if (resEntity != null) {
                resEntity.consumeContent();
            }

            //Kill the connection.
            httpclient.getConnectionManager().shutdown();
        } catch (java.io.IOException e) {
            //Print error message, there is some error with reaching dropzone.
            messages.displayMessage("An error occurred while uploading file. Servers Down?");
            return;
        }
    }
}
