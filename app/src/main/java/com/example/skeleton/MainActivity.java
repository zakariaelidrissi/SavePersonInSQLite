package com.example.skeleton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_FNAME = "com.example.skeleton.FNAME";
    public static final String EXTRA_LNAME = "com.example.skeleton.LNAME";

    private EditText mFname;
    private EditText mLname;
    private Button mButtonSave;
    private Button mButtonDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFname  = findViewById(R.id.main_fname);
        mLname  = findViewById(R.id.main_lname);
        mButtonSave = findViewById(R.id.main_button_save);
        mButtonDisplay = findViewById(R.id.main_button_display);

        // désactiver le bouton de démarrage de jeu au lancement de l'application
        mButtonSave.setEnabled(false);
        //mButtonDisplay.setEnabled(false);
        mLname.setEnabled(false);

        mFname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mLname.setEnabled(!editable.toString().isEmpty());
            }
        });

        mLname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mButtonSave.setEnabled(!editable.toString().isEmpty());
            }
        });

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fName = mFname.getText().toString();
                String lName = mLname.getText().toString();

                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.addPerson(fName.trim(), lName.trim());

                // permettent de lancer de nouvelles Activity grâce à la méthode startActivity().
                /*Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra(EXTRA_FNAME, fName);
                intent.putExtra(EXTRA_LNAME, lName);
                startActivity(intent);*/


            }
        });

        mButtonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);

                // permettent de lancer de nouvelles Activity grâce à la méthode startActivity().
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(intent);

            }
        });

    }
}