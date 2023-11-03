package com.example.prueba.Models;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba.R;

import java.util.List;

public class PermisoAdapter extends RecyclerView.Adapter<PermisoAdapter.ViewHolder>{
    List<Permiso> lista;

    Activity activity;
    public PermisoAdapter(List<Permiso> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PermisoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itempermiso,parent,false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull PermisoAdapter.ViewHolder holder, int position) {
        Permiso permiso = lista.get(position);
        holder.setData(permiso);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreper;
        Switch switch1;
        Permiso pc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreper = itemView.findViewById(R.id.nombreper);
            switch1 = itemView.findViewById(R.id.switch1);

            switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked==true) {
                    if (ContextCompat.checkSelfPermission(activity, pc.getNombre())
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity,
                                new String[] { pc.getNombre() },
                                1);
                    }

                }
                else {
                    // Intent to App Settings
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                    intent.setData(uri);
                    activity.startActivity(intent);
                }
            });

        }

        public void setData(Permiso permiso) {
            pc = permiso;
            nombreper.setText(pc.getDescripcion());
            if (ContextCompat.checkSelfPermission(activity, pc.getNombre())
                    == PackageManager.PERMISSION_GRANTED) {
                switch1.setChecked(true);
            } else {
                switch1.setChecked(false);
            }
        }
    }

}

