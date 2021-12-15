package com.example.fast1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    Drawable drawable1, drawable2, drawable3, drawable4;
    ImageView productoImagen1, productoImagen2, productoImagen3, productoImagen4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //------------------------------------------------------------------------------------------
        Resources res1 = getResources();
        drawable1 = res1.getDrawable(R.drawable.a1, getTheme());

        productoImagen1 = (ImageView) findViewById(R.id.inicio1);
        productoImagen1.setImageDrawable(drawable1);
        //------------------------------------------------------------------------------------------
        Resources res2 = getResources();
        drawable2 = res2.getDrawable(R.drawable.a2, getTheme());

        productoImagen2 = (ImageView) findViewById(R.id.imagen2);
        productoImagen2.setImageDrawable(drawable2);
        //------------------------------------------------------------------------------------------
        Resources res3 = getResources();
        drawable3 = res3.getDrawable(R.drawable.a3, getTheme());

        productoImagen3 = (ImageView) findViewById(R.id.imagen3);
        productoImagen3.setImageDrawable(drawable3);
        //------------------------------------------------------------------------------------------
        Resources res4 = getResources();
        drawable4 = res4.getDrawable(R.drawable.a4, getTheme());

        productoImagen4 = (ImageView) findViewById(R.id.imagen4);
        productoImagen4.setImageDrawable(drawable4);
    }
}