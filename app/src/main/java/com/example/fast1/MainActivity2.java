package com.example.fast1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity {

    Drawable drawable1, drawable2, drawable3, drawable4;

    ImageView productoImagen1, productoImagen2, productoImagen3, productoImagen4;

    private Button btnMensajes, btnNotificaciones, btnDialogos;

    private LinearLayout layaoutButton;

    private final static String CHANNEL_ID= "Notificaciones";

    private final static int Notification_ID= 0;

    public void createNotificationChanel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Notificacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void createNotification(String titulo, String contenido){
        NotificationCompat.Builder builder= new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.s1);
        builder.setContentTitle(titulo);
        builder.setContentText(contenido);
        builder.setDefaults(NotificationCompat.PRIORITY_DEFAULT);
        builder.setColor(Color.WHITE);
        builder.setLights(Color.BLUE, 500, 500);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(Notification_ID, builder.build());
    }

    private void createDialog(String titulo, String contenido){
        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle(titulo);
        builder.setMessage(contenido)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Compra efectiva", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Compra fallida", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
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

        btnMensajes= (Button) findViewById(R.id.btnMensajes);
        btnNotificaciones= (Button) findViewById(R.id.btnNotificaciones);
        btnDialogos= (Button) findViewById(R.id.btnDialogos);
        layaoutButton= (LinearLayout) findViewById(R.id.layaoutButton);

        btnMensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar= Snackbar.make(layaoutButton, "30 minutos", Snackbar.LENGTH_LONG);
                snackbar.setAction("llamar a servicios", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Va a servicios", Toast.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();
            }
        });

        btnNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChanel();
                createNotification("Bienvenido", "Disfruta de comida FAST con sabor celestial");
            }
        });

        btnDialogos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog("Comprobación de compra", "¿Pudo realizar el pago de sus productos?");
            }
        });
    }
}