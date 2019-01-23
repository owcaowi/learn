package com.example.owi.contentprovider;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        myDataBase db = new myDataBase(this);

        if(  db.getAllDogs().isEmpty() ){

            db.addDog(new Dogs(1,"Goofy","Donald Duck"));
            db.addDog(new Dogs(2,"Scooby-Doo"," Scooby-Doo"));
            db.addDog(new Dogs(3,"Pluto","Donald Duck"));
            db.addDog(new Dogs(4,"Pongo","101 Dalmatians"));

        }

        TextView textView = (TextView) findViewById(R.id.textView);

        List<Dogs> allDogs = (LinkedList<Dogs>) db.getAllDogs();

       if( !allDogs.isEmpty() ){

           textView.setText(allDogs.get(0).getName() + "\n"+
                   allDogs.get(1).getName() +"\n"+
                           allDogs.get(2).getName()
           );

       }

    }

}
