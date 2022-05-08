package com.lvrpc.transport;

import com.lvrpc.rpc.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPTransportClient implements TransportClient{
    private String url;
    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection urlConnection =(HttpURLConnection) new URL(url).openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestMethod("POST");

            urlConnection.connect();
            IOUtils.copy(data,urlConnection.getOutputStream());

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                return urlConnection.getInputStream();
            } else {
                return urlConnection.getErrorStream();
            }

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
