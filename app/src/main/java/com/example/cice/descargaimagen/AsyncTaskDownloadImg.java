package com.example.cice.descargaimagen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cice on 25/3/17.
 */

public class AsyncTaskDownloadImg extends AsyncTask <String, Void, Bitmap[]>
{

    private MainActivity activity_pater;

    public AsyncTaskDownloadImg(MainActivity mainActivity)
    {
                this.activity_pater= mainActivity;
    }

    @Override
    protected Bitmap[] doInBackground(String... url) {

        Bitmap bitmap[] = null;
        String url_foto = null;
        String url_segundafoto = null;
        URL url1 = null;
        URL url2 = null;
        HttpURLConnection httpURLConnection = null;
        HttpURLConnection httpURLConnection2 = null;
        int respuesta = -5;
        int respuesta2 = -5;
        InputStream inputStream = null;

        bitmap = new Bitmap[2];


        try
        {
            url_foto = url[0]; //ruta de la foto
            url_segundafoto = url[1];
            url1 = new URL(url_foto);
            url2 = new URL(url_segundafoto);
            httpURLConnection = (HttpURLConnection) url1.openConnection();
            httpURLConnection2 = (HttpURLConnection) url2.openConnection();

            respuesta= httpURLConnection.getResponseCode();
            respuesta2= httpURLConnection2.getResponseCode();

            if (respuesta == HttpURLConnection.HTTP_OK && respuesta2 == HttpURLConnection.HTTP_OK)
            {
                Log.d("MENSAJE", "FUE TODO BIEN. ACCEDO A LA FOTO");
                inputStream = httpURLConnection.getInputStream();//accedo al cuerpo del mensaje
                bitmap[0] =BitmapFactory.decodeStream(inputStream);
                inputStream = httpURLConnection2.getInputStream();//accedo al cuerpo del mensaje
                bitmap[1] =BitmapFactory.decodeStream(inputStream);

            }
            else
            {
                Log.d("MENSAJE", "PETIION FUE MAL. NO HAY FOTO");
            }

        }
        catch (Throwable t)
        {
            Log.e("MENSAJE", "ALGO HA IDO MAL", t);
        }
        finally
        {
            httpURLConnection.disconnect();
            httpURLConnection2.disconnect();
        }


        return (bitmap);
    }

    @Override
    protected void onPostExecute(Bitmap[] bitmap) {
        Log.d("MENSAJE", "TAREA FINALIZADA");
        activity_pater.mostrarImagen(bitmap);
    }
}
