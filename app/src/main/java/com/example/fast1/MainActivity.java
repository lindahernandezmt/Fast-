package com.example.fast1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    /**
     * Se instancia el button1
     *
     */
    Button button1;

    /**
     * Se instancia el text1
     *
     */
    TextView text1;

    /**
     * Se instancia el actionBar
     *
     */
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Se coloca el icono en la barra
         *
         */
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher_round);

        /**
         * Boton que inicializa mensaje de bienvenida
         *
         */
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setText("BIENVENIDO");
                Toast.makeText(getApplicationContext(),"Disfrute los productos", Toast.LENGTH_LONG).show();
            }
        });

        /**
         * TextView que da la bienvenida
         *
         */
        text1 = (TextView) findViewById(R.id.text1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /**
         * Clase MenuInflater que controla el menú
         *
         */
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Definicion de la funcionalidad para que se ejute cada vez que se le da click a un item del menú
     *
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        /**
         * Se declara como va a tomar los items
         *
         */
        int id = item.getItemId();

        /**
         * Toma el id y lo compara con lo que esta en R de productosOpcion
         *
         */
        if(id == R.id.productosOpcion){
            Toast.makeText(getApplicationContext(),"Aquí verá los productos", Toast.LENGTH_LONG).show();
            Intent productosPantalla = new Intent(this, MainActivity2.class);
            startActivity(productosPantalla);
        }
        /**
         * Toma el id y lo compara con lo que esta en R de serviciosOpcion
         *
         */
        if(id == R.id.serviciosOpcion){
            Toast.makeText(getApplicationContext(),"Aquí verá los servicios", Toast.LENGTH_LONG).show();
            Intent serviciosPantalla = new Intent(this, MainActivity3.class);
            startActivity(serviciosPantalla);
        }
        /**
         * Toma el id y lo compara con lo que esta en R de sucursalesOpcion
         *
         */
        if(id == R.id.sucursalesOpcion){
            Toast.makeText(getApplicationContext(),"Aquí verá las sucursales", Toast.LENGTH_LONG).show();
            Intent sucursalesPantalla = new Intent(this, MainActivity4.class);
            startActivity(sucursalesPantalla);
        }

        return super.onOptionsItemSelected(item);
    }
}