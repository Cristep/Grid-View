package com.cristianmorales.gridview2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private GridView gridView;
    private List<String> names;
    private MyAdapter myAdapter;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_icon);



        gridView = (GridView) findViewById(R.id.gridView);

        //Datos a mostrar
        names = new ArrayList<String>();
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Ruben");
        names.add("Satiago");
        names.add("Mario");
        /*names.add("Bolo");
        names.add("Polo");
        names.add("Wini");
        names.add("Favo");
        names.add("Jamby");
        names.add("Hola");
        names.add("Peak");
        names.add("Ale");
        names.add("Li");
        names.add("Tagada");
        names.add("Kevo");*/

        //Adaptador la forma visual en la que mostraremos los datos
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item_1, names);

        //Enlazamos el adaptador con nuestro list view
        //listView.setApadapter(adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Clicked" + names.get(position), Toast.LENGTH_LONG).show();

            }
        });
         // aqui llamamos al adaptor nuestro adaptador personzalido
         myAdapter = new MyAdapter(this, R.layout.grid_item, names);
        gridView.setAdapter(myAdapter);

        // Para eliminar el elemento tenemos que registrarlo
        registerForContextMenu(gridView);
    }
     //Inflamos el layout de menu de opciones
    @Override



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        inflater.inflate(R.menu.next_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.add_item:
                //Anadimos nuevo nombe
                this.names.add("added n" + (++counter));

            case R.id.nextMenu:
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);


                // Notificamos al adapter
                this.myAdapter.notifyDataSetChanged();
                return true;

                default:
                return super.onOptionsItemSelected(item);

        }

    }


    //Inflamos osea mostramos el layout del context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(names.get(info.position));
        inflater.inflate(R.menu.context_menu, menu);

    }
    //Manejos eventos click en el context Menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

          AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.delte_item:
                //Eliminamso nuevo nombe
                this.names.remove(info.position);


                // Notificamos al adapter
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
               return super.onContextItemSelected(item);
        }
    }
}


