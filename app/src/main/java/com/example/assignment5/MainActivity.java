package com.example.assignment5;

//Brady Goldsworthy
//Justin Dupre
//Assignment 5

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;

    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private Button insertBTN, deleteBTN, clearBTN;
    private TextView listTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseManager = new DatabaseManager(this);
        updateView();

    }

    @Override
    protected void onResume() {
        super.onResume();

        updateView(); //Update list onResume

    }

    public void updateView() {
        ArrayList<GroceryItem> groceryItems = databaseManager.selectAll();

        //Check if there is info in the database
        if (true) {
            ScrollView scrollView = new ScrollView(this); //Holds gridlayout

            GridLayout gridLayout = new GridLayout(this); //Holds list

            gridLayout.setRowCount(groceryItems.size()); //Number of items in list

            gridLayout.setColumnCount(2); //checkbox, name

            TextView[] nameArray = new TextView[groceryItems.size()];

            int sub = 0;

            //Filling gridlayout with grocery items
            for (GroceryItem groceryItem : groceryItems) {
                //Create textview for name
                nameArray[sub] = new TextView(this);
                nameArray[sub].setGravity(Gravity.CENTER);
                nameArray[sub].setText("" + groceryItem.getName());



                gridLayout.addView(new CheckBox(this), 300, ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(nameArray[sub], 300, ViewGroup.LayoutParams.WRAP_CONTENT);

                sub++;
            }

            linearLayout = new LinearLayout(this);
            insertBTN = new Button(this);
            deleteBTN = new Button(this);
            clearBTN = new Button(this);
            listTV = new TextView(this);

            //Starts InsertActivivty
            insertBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, InsertActivity.class);

                    startActivity(intent);
                }
            });

            insertBTN.setText("Insert Items");

            //Starts DeleteActivity
            deleteBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, DeleteActivity.class);

                    startActivity(intent);
                }
            });

            deleteBTN.setText("Delete Items");

            //Clear the database table and refresh activity
            clearBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseManager.clearDatabase();
                    finish();
                    startActivity(getIntent());
                }
            });

            clearBTN.setText("Clear List");

            listTV.setText("Your List: ");


            //Creating Layout
            scrollView.addView(gridLayout);

            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(insertBTN);
            linearLayout.addView(deleteBTN);
            linearLayout.addView(clearBTN);
            linearLayout.addView(listTV);
            linearLayout.addView(scrollView);

            setContentView(linearLayout);
        }
    }

}
