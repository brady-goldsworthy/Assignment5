package com.example.assignment5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        databaseManager = new DatabaseManager(this);
    }

    public void insertItem(View view) {
        EditText nameET = findViewById(R.id.nameEditText);

        String nameStr = nameET.getText().toString();

        GroceryItem groceryItem = new GroceryItem(0, nameStr);

        databaseManager.insert(groceryItem);

        nameET.setText(""); //Clear text field

        Toast.makeText(InsertActivity.this, "Item was added to your list", Toast.LENGTH_SHORT);

    }

    public void goBack(View view) {
        finish();
    }

}
