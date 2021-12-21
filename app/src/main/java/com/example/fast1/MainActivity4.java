package com.example.fast1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fast1.ui.SucursalForm;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity4 extends AppCompatActivity {

    Drawable drawable1, drawable2, drawable3;

    ImageView productoImagen1, productoImagen2, productoImagen3;

    private Button btnSucursalForm;

    private LinearLayout layaoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //------------------------------------------------------------------------------------------
        Resources res1 = getResources();
        drawable1 = res1.getDrawable(R.drawable.ser1, getTheme());

        productoImagen1 = (ImageView) findViewById(R.id.inicio1);
        productoImagen1.setImageDrawable(drawable1);
        //------------------------------------------------------------------------------------------
        Resources res2 = getResources();
        drawable2 = res2.getDrawable(R.drawable.ser2, getTheme());

        productoImagen2 = (ImageView) findViewById(R.id.imagen2);
        productoImagen2.setImageDrawable(drawable2);
        //------------------------------------------------------------------------------------------
        Resources res3 = getResources();
        drawable3 = res3.getDrawable(R.drawable.ser3, getTheme());

        productoImagen3 = (ImageView) findViewById(R.id.imagen3);
        productoImagen3.setImageDrawable(drawable3);
        btnSucursalForm= (Button) findViewById(R.id.btnSucursalForm);
        layaoutButton= (LinearLayout) findViewById(R.id.layaoutButton);

        btnSucursalForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar= Snackbar.make(layaoutButton, "¿Quieres conocer más?", Snackbar.LENGTH_LONG);
                snackbar.setAction("Sí", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(MainActivity4.this, SucursalForm.class);
                        startActivity(intent);;
                    }
                });
                snackbar.show();
            }
        });
    }
}