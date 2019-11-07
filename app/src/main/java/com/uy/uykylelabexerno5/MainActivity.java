package com.uy.uykylelabexerno5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] androids, versions, levels, dates, message;
    ListView list;
    int[] cLogo = {R.drawable.cupcake, R.drawable.donut, R.drawable.eclair, R.drawable.froyo, R.drawable.gingerbread,
            R.drawable.honeycomb, R.drawable.icecreamsandwich, R.drawable.jellybean, R.drawable.kitkat,  R.drawable.lollipop, R.drawable.marshmallow, R.drawable.nougat,
            R.drawable.oreo, R.drawable.pie, R.drawable.android10};

    ArrayList<Android> androidLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androids = getResources().getStringArray(R.array.androids);
        versions = getResources().getStringArray(R.array.versions);
        levels = getResources().getStringArray(R.array.levels);
        dates = getResources().getStringArray(R.array.dates);
        message = getResources().getStringArray(R.array.messages);
        list = findViewById(R.id.lvAndroids);
        for(int i = 0; i < androids.length; i++){
            androidLists.add(new Android(cLogo[i], androids[i], versions[i], levels[i], dates[i], message[i]));
        }
        list = findViewById(R.id.lvAndroids);
        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.item ,androidLists);
        list.setAdapter(adapter);
        //list.position
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        final String versionName, releaseDate;
        final File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "android.txt");
        try{
            FileOutputStream fos = new FileOutputStream(file);
            String choice = androidLists.get(i).getAndroid();
            fos.write(choice.getBytes());
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(androidLists.get(i).getLogo());
        dialog.setTitle(androidLists.get(i).getAndroid());
        dialog.setMessage(androidLists.get(i).getMessage());
        versionName = androidLists.get(i).getAndroid();
        releaseDate = androidLists.get(i).getDate();
        dialog.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override public void onClick (DialogInterface dialog, int which){
                dialog.dismiss();
                // folder.toString()
                Toast.makeText(MainActivity.this, "Android name: " + versionName + ".\n" + releaseDate, Toast.LENGTH_LONG).show();
            }
        });
        dialog.create().show();
    }
}
