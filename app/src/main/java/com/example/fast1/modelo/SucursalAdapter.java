package com.example.fast1.modelo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fast1.R;

import java.util.ArrayList;

public class SucursalAdapter extends BaseAdapter {

    Context context;

    ArrayList<Sucursales> sucursales;

    LayoutInflater inflater;

    public SucursalAdapter(Context context, ArrayList<Sucursales> sucursales) {
        this.context = context;
        this.sucursales = sucursales;
    }

    @Override
    public int getCount() {
        return sucursales.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView= inflater.inflate(R.layout.sucursal_item, null);
        }

        ImageView imageView= convertView.findViewById(R.id.imageItem);
        TextView id= convertView.findViewById(R.id.tvIdItem);
        TextView name= convertView.findViewById(R.id.tvNameItem);
        TextView description= convertView.findViewById(R.id.tvDescriptionItem);
        TextView localization= convertView.findViewById(R.id.tvLocalizationItem);

        Sucursales sucursal= sucursales.get(position);

        byte[] image= sucursal.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(image, 0, image.length);

        imageView.setImageBitmap(bitmap);
        id.setText(sucursal.getId());
        name.setText(sucursal.getName());
        description.setText(sucursal.getDescription());
        localization.setText(sucursal.getLocation());

        return convertView;
    }
}
