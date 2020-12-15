package com.cristianmorales.gridview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public  class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> names;

    public MyAdapter (Context context, int layout, List<String> names){
        this.context = context;
        this.layout = layout;
        this.names  = names;
    }
    //Cuantas veces tiene que iterar osea los numeros de item
    //que se dibujan
    @Override
    public int getCount() {
        return this.names.size();
    }

    //simplemente es para obteer el item
    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    // obtener el ID de la coleccion
    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //View holder Paterns
        ViewHolder holder;
        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout, null);

            holder = new ViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        // Nos treamos el valor actual dependiente de la posicion
         String currentName = names.get(position);
        //currentName = (String) getItem(position);

        //Referecniamos el elemento

        holder.nameTextView.setText(currentName);

        // Debolbemos inflada y modificada
        return convertView;


    }
     static class ViewHolder{
        private TextView nameTextView;

    }


}

