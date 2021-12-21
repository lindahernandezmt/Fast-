package com.example.fast1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fast1.casos_uso.casoUsoProductos;
import com.example.fast1.datos.DBHelper;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity3 extends AppCompatActivity {

    Drawable drawable1, drawable2, drawable3;

    ImageView productoImagen1, productoImagen2, productoImagen3;

    private ImageView edtImage;

    private EditText edtName, edtDescription, edtPrice, edtId;

    private Button btnAgregar, btnEliminar, btnActualizar, btnConsultar, btnChoose;

    private LinearLayout layaoutButton;

    private DBHelper dbHelper;

    private casoUsoProductos casoUsoProductos;

    private static int READ_CODE_GALLERY=999;

    public byte[] imageViewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray =stream.toByteArray();
        return byteArray;
    }

    public void limpiarCampos(){
        edtName.setText("");
        edtImage.setImageResource(R.drawable.ser1);
        edtDescription.setText("");
        edtPrice.setText("");
        edtId.setText("");
    }

    public void showById(Cursor cursor){
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"Producto no encontrado",Toast.LENGTH_SHORT).show();
        }else{
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()){
                edtName.setText(cursor.getString(1));
                byte[] image = cursor.getBlob(2);
                edtDescription.setText(cursor.getString(3));
                edtPrice.setText(cursor.getString(4));
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
                edtImage.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //------------------------------------------------------------------------------------------
        Resources res1 = getResources();
        drawable1 = res1.getDrawable(R.drawable.s1, getTheme());

        productoImagen1 = (ImageView) findViewById(R.id.inicio1);
        productoImagen1.setImageDrawable(drawable1);
        //------------------------------------------------------------------------------------------
        Resources res2 = getResources();
        drawable2 = res2.getDrawable(R.drawable.s2, getTheme());

        productoImagen2 = (ImageView) findViewById(R.id.imagen2);
        productoImagen2.setImageDrawable(drawable2);
        //------------------------------------------------------------------------------------------
        Resources res3 = getResources();
        drawable3 = res3.getDrawable(R.drawable.s3, getTheme());

        productoImagen3 = (ImageView) findViewById(R.id.imagen3);
        productoImagen3.setImageDrawable(drawable3);

        btnAgregar= (Button) findViewById(R.id.btnAgregar);
        btnEliminar= (Button) findViewById(R.id.btnEliminar);
        btnActualizar= (Button) findViewById(R.id.btnActualizar);
        btnConsultar= (Button) findViewById(R.id.btnConsultar);
        btnChoose= (Button) findViewById(R.id.btnChoose);
        edtId= (EditText) findViewById(R.id.edtId);
        edtName= (EditText) findViewById(R.id.edtName);
        edtDescription= (EditText) findViewById(R.id.edtDescription);
        edtPrice= (EditText) findViewById(R.id.edtPrice);
        edtImage= (ImageView) findViewById(R.id.edtImage);
        layaoutButton= (LinearLayout) findViewById(R.id.layaoutButton);
        dbHelper = new DBHelper(getApplicationContext());
        casoUsoProductos = new casoUsoProductos();

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        MainActivity3.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        READ_CODE_GALLERY
                );
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Toast.makeText(getApplicationContext(), edtName.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                    dbHelper.insertProductos(
                            edtName.getText().toString().trim(),
                            imageViewToByte(edtImage),
                            edtDescription.getText().toString().trim(),
                            edtPrice.getText().toString().trim()
                    );
                    limpiarCampos();
                    Toast.makeText(getApplicationContext(), "Gracias por tu sugerencia", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                if(id.equals("")){
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }else{
                    dbHelper.deleteProductos(id);
                    limpiarCampos();
                    Toast.makeText(getApplicationContext(), "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                if(id.equals("")){
                    Cursor cursor = dbHelper.getProductos();
                    String result = casoUsoProductos.cursorToString(cursor);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                }else{
                    Cursor cursor = dbHelper.getProductosById(id);
                    showById(cursor);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode== READ_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, READ_CODE_GALLERY);
            }else{
                Toast.makeText(getApplicationContext(), "Sin Permisos", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == READ_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                edtImage.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}