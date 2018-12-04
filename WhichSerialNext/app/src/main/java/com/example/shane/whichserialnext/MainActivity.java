package com.example.shane.whichserialnext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    SerialList serien = new SerialList();
    private final int REQUEST_CODE = 20;
    int index; //für Delete Methode

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();

        //Per Adapter wird ListView mit ArrayList verknüpft und angezeigt
        ArrayAdapter<SerialList> itemsAdapter = new ArrayAdapter<SerialList>(this, android.R.layout.simple_list_item_1, serien.getSerialList());
        final ListView listView = findViewById(R.id.ListView_SerialList);
        listView.setAdapter(itemsAdapter);

        //setze OnItemClickListener auf die ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View view, int position, long id){
                Context context = getApplicationContext();
                CharSequence text = "Bitte alles ausfüllen";
                int duration = Toast.LENGTH_LONG;
                Toast.makeText(context, text, duration).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                return false;
            }
        });

        registerForContextMenu(listView);

        //setze OnClickListener auf den Button Erstelle neue Serie
        Button buttonname = findViewById(R.id.button);
        buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(i, 20);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_bearbeiten:
                Toast.makeText(this, "Bearbeiten", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_loeschen:
                serien.deleteSerial(index);
                saveData();
                finish();
                Intent i = getIntent();
                startActivity(i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    //Auslesen der Variablen von SecondActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE ist in Instanzvariable konfiguriert
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {

            // Ziehe die Daten aus SecondActivity
            String neueBezeichnung = data.getStringExtra("Serienbezeichnung");
            int neueStaffel = data.getIntExtra("Staffel", 999);
            int neueEpisode = data.getIntExtra("Episode", 999);
            Serial serie = new Serial(neueBezeichnung, neueStaffel, neueEpisode);
            serien.addSerial(serie);

            //Per Adapter wird ListView mit ArrayList verknüpft und angezeigt
            ArrayAdapter<SerialList> itemsAdapter = new ArrayAdapter<SerialList>(this, android.R.layout.simple_list_item_1, serien.getSerialList());
            ListView listView = (ListView) findViewById(R.id.ListView_SerialList);
            listView.setAdapter(itemsAdapter);

            saveData();
        }
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(serien);
        editor.putString("Meine Serien", json);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Meine Serien", null);
        Type type = new TypeToken<SerialList>() {}.getType();
        serien = gson.fromJson(json, type);

        if(serien == null){
            serien = new SerialList();
        }
    }
}