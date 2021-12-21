package com.example.fast1.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fast1.R;
import com.example.fast1.datos.DBHelper;
import com.example.fast1.modelo.SucursalAdapter;
import com.example.fast1.modelo.Sucursales;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SucursalesFragment extends Fragment{

    private GridView gridView;
    private DBHelper dbHelper;
    private ArrayList<Sucursales> sucursales= new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_sucursales, container,false);

        try {
            gridView = (GridView) root.findViewById(R.id.gridViewSucursales);
            SucursalAdapter sucursalAdapter = new SucursalAdapter(root.getContext(), sucursales);
            gridView.setAdapter(sucursalAdapter);
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
            Log.w("Error ->>>", e.toString());
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
