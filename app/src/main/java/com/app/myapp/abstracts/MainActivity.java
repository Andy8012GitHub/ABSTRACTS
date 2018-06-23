package com.app.myapp.abstracts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_main);
    }

    public void rulesButtonHandler (View view){
        Intent intent = new Intent(MainActivity.this, RulesActivity.class);
        startActivity(intent);
    }
    public void playButtonHandler (View view) {
        Intent intent = new Intent(MainActivity.this, CreateTeams.class);
        startActivity(intent);
    }

    public void scoreKeeperButtonHandler(View view) {
        Intent intent = new Intent(MainActivity.this, DefinitionsActivity.class);
        startActivity(intent);
    }
}