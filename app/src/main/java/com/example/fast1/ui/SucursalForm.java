package com.example.fast1.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fast1.MainActivity3;
import com.example.fast1.R;
import com.example.fast1.datos.DBHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SucursalForm extends AppCompatActivity {

    private Button btnInsertar, btnChoose;

    private EditText tvName, tvDescription;

    private TextView tvLocalization;

    private LinearLayout layaoutButton;

    private DBHelper dbHelper;

    private ImageView imgSucursal;

    private static int READ_CODE_GALLERY=999;

    public byte[] imageViewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray =stream.toByteArray();
        return byteArray;
    }

    public void limpiarCampos(){
        tvName.setText("");
        imgSucursal.setImageResource(R.drawable.ser2);
        tvDescription.setText("");
        tvLocalization.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal_form);

        btnInsertar= (Button) findViewById(R.id.btnInsertar);
        btnChoose= (Button) findViewById(R.id.btnChoose);
        tvName= (EditText) findViewById(R.id.tvName);
        tvDescription= (EditText) findViewById(R.id.tvDescription);
        tvLocalization= (TextView) findViewById(R.id.tvLocalization);
        imgSucursal= (ImageView) findViewById(R.id.imgSucursal);
        layaoutButton= (LinearLayout) findViewById(R.id.layaoutButton);
        dbHelper = new DBHelper(getApplicationContext());

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(), tvName.getText().toString().trim(), Toast.LENGTH_SHORT).show();

                    limpiarCampos();
                    Toast.makeText(getApplicationContext(), "Inserta Sucursal", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        SucursalForm.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        READ_CODE_GALLERY
                );
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
                imgSucursal.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}