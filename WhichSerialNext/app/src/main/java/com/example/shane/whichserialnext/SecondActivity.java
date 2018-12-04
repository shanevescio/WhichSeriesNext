package com.example.shane.whichserialnext;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        //setze OnClickListener auf den Button
        Button buttonname2 = (Button) findViewById(R.id.button2);
        buttonname2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText editSerienbezeichnung = (TextInputEditText) findViewById(R.id.serienbezeichnung);
                String resultSerienbezeichnung = editSerienbezeichnung.getText().toString();

                TextInputEditText editStaffel = (TextInputEditText) findViewById(R.id.staffel);
                String resultStaffelText = editStaffel.getText().toString();

                TextInputEditText editEpisode = (TextInputEditText) findViewById(R.id.episode);
                String resultEpisodeText = editEpisode.getText().toString();

                if(resultSerienbezeichnung.isEmpty() || resultStaffelText.isEmpty() || resultEpisodeText.isEmpty()){
                    //Info Text alles ausfüllen
                    Context context = getApplicationContext();
                    CharSequence text = "Bitte alles ausfüllen";
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, text, duration).show();
                }
                else if(resultStaffelText.equals("0") || resultEpisodeText.equals("0")) {
                    //Info Text nicht 0 eintragen
                    Context context = getApplicationContext();
                    CharSequence text = "Wert 0 ist ungültig. Bitte verwende eine höhere Zahl";
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, text, duration).show();
                }
                else{
                    int resultStaffel = Integer.parseInt(resultStaffelText);
                    int resultEpisode = Integer.parseInt(resultEpisodeText);

                    //Bereite Daten für Übergabe an MainActivity vor
                    Intent data = new Intent();
                    data.putExtra("Serienbezeichnung", resultSerienbezeichnung);
                    data.putExtra("Staffel", resultStaffel);
                    data.putExtra("Episode", resultEpisode);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}
