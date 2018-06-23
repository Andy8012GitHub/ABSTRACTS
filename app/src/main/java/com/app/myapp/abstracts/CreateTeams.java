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
    EditText editTextEnterTeamName;
    static String teamOneName = "Team 1";
    static String teamTwoName = "Team 2";
    TextView textViewTeamName, textViewTooManyCharacters;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_create_teams);

        textViewTeamName = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewTeamName);
        editTextEnterTeamName = (EditText) findViewById(R.id.enterTeamName);
        textViewTooManyCharacters = (TextView) findViewById(R.id.textViewTooManyCharacters);
        btnNext = (Button) findViewById(com.app.myapp.abstracts.R.id.btnNext);

        textViewTooManyCharacters.setVisibility(GONE);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewTooManyCharacters.setVisibility(GONE);
                teamOneName = editTextEnterTeamName.getText().toString();
                if(teamOneName.length() > 12) {
                    textViewTooManyCharacters.setVisibility(VISIBLE);
                    return;
                }
                textViewTeamName.setText(teamOneName);
                editTextEnterTeamName.setText("");
                btnNext.setText(R.string.submit);
                textViewTeamName.setText(R.string.team2);
                textViewTeamName.setTextColor(getResources().getColor(R.color.black));
                editTextEnterTeamName.setHint(R.string.enter_team_name);
                btnNext.setVisibility(GONE);
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        textViewTooManyCharacters.setVisibility(GONE);
                        teamTwoName = editTextEnterTeamName.getText().toString();
                        if(teamTwoName.length() > 12) {
                            textViewTooManyCharacters.setVisibility(VISIBLE);
                            return;
                        }
                        textViewTeamName.setText(teamTwoName);
                        editTextEnterTeamName.setText("");
                        btnNext.setText(R.string.play);
                        btnNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(CreateTeams.this, GamePlay.class));
                            }
                        });
                    }
                });
            }
        });
    }
}