package com.example.cice.descargaimagen;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String URL_IMAGEN="https://i1.wp.com/www.todouruguay.net/wp-content/uploads/2016/01/Buhos_y_lechuzas_del_Uruguay0.jpg?w=650";
    private static final String URL_IMAGEN_SEGUNDA="http://noticiasinsolitas.org/wp-content/uploads/2012/03/buho.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void  descargaImagen (View view)
    {
        Log.d("MENSAJE", "BOTON DE DESCARGA PRESIONADO");

        AsyncTaskDownloadImg asyncTaskDownloadImg = new AsyncTaskDownloadImg(this);

        asyncTaskDownloadImg.execute(URL_IMAGEN, URL_IMAGEN_SEGUNDA);

        Log.d("MENSAJE", "DESCARGA LANZADA");

        view.setEnabled(false);

    }


    public void mostrarImagen (Bitmap[] bitmap){
        //TODO mostrar imagen

        ImageView imageView = (ImageView) findViewById(R.id.miimagen);
        imageView.setImageBitmap(bitmap[0]);

        ImageView imageView2 = (ImageView) findViewById(R.id.misegundaimagen);
        imageView2.setImageBitmap(bitmap[1]);

        Log.d("MENSAJE", "MOSTRANDOOOOOOO!");

        Button button = (Button) findViewById(R.id.botondescarga);
        button.setEnabled(true);
    }

}
