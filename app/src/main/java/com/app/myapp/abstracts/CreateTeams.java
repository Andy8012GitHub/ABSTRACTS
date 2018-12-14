package com.app.myapp.abstracts;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class CreateTeams extends AppCompatActivity {
    Button btnCreateTeamsToMain;
    EditText editTextEnterTeamName;
    static String teamOneName = "Team 1";
    static String teamTwoName = "Team 2";
    TextView textViewTeamName, textViewTooManyCharacters;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_create_teams);

        btnCreateTeamsToMain = (Button) findViewById(R.id.btnCreateTeamsToMain);
        textViewTeamName = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewTeamName);
        editTextEnterTeamName = (EditText) findViewById(R.id.enterTeamName);
        textViewTooManyCharacters = (TextView) findViewById(R.id.textViewTooManyCharacters);
        btnNext = (Button) findViewById(com.app.myapp.abstracts.R.id.btnNext);

        textViewTooManyCharacters.setVisibility(GONE);

        btnCreateTeamsToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateTeams.this, MainActivity.class));
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewTooManyCharacters.setVisibility(GONE);
                String editTextContent = editTextEnterTeamName.getText().toString();
                teamOneName = editTextContent.equals("") ? getString(R.string.team1) : editTextContent;
                if(teamOneName.length() > 12) {
                    textViewTooManyCharacters.setVisibility(VISIBLE);
                    return;
                }
                editTextEnterTeamName.setText("");
                btnNext.setText(R.string.play);
                textViewTeamName.setText(R.string.team2);
                textViewTeamName.setTextColor(getResources().getColor(R.color.darkGray));
                editTextEnterTeamName.setHint(R.string.team2);
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        textViewTooManyCharacters.setVisibility(GONE);
                        String editTextContent = editTextEnterTeamName.getText().toString();
                        teamTwoName = editTextContent.equals("") ? getString(R.string.team2) : editTextContent;
                        if(teamTwoName.length() > 12) {
                            textViewTooManyCharacters.setVisibility(VISIBLE);
                            return;
                        }
                        startActivity(new Intent(CreateTeams.this, GamePlay.class));
                    }
                });
            }
        });
    }
}