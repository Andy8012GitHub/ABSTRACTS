package com.app.myapp.abstracts;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CreateTeams extends AppCompatActivity {
    EditText enterTeamOrPlayerName;
    TextView playerList;
    static String teamOneName = "Team 1";
    static String teamTwoName = "Team 2";
    TextView textViewTeamName;
    Button nextTeamButton,startButton, btnYes, btnNo, submitButton;
    TextView textViewChooseYourOwnClueGiver;
    int i = 0;
    static ArrayList<String> teamOnePlayers = new ArrayList<>();
    static ArrayList<String> teamTwoPlayers = new ArrayList<>();
    static boolean skippedCreatingTeams = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_create_teams);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View dialogView = inflater.inflate(com.app.myapp.abstracts.R.layout.dialog_skip_create_teams, null);

        playerList = (TextView) findViewById(com.app.myapp.abstracts.R.id.playerList);
        textViewTeamName = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewTeamName);
        enterTeamOrPlayerName = (EditText) findViewById(R.id.enterTeamOrPlayerName);
        submitButton = (Button) findViewById(com.app.myapp.abstracts.R.id.submitButton);
        nextTeamButton = (Button) findViewById(com.app.myapp.abstracts.R.id.nextTeamButton);
        startButton = (Button) findViewById(com.app.myapp.abstracts.R.id.startButton);
        nextTeamButton.setVisibility(View.GONE);
        startButton.setVisibility(View.GONE);
        textViewTeamName.setText("Team 1");

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(dialogView);
        textViewChooseYourOwnClueGiver = (TextView) dialogView.findViewById(com.app.myapp.abstracts.R.id.textViewChooseYourOwnClueGivers);
        btnYes = (Button) dialogView.findViewById(com.app.myapp.abstracts.R.id.btnYes);
        btnNo = (Button) dialogView.findViewById(com.app.myapp.abstracts.R.id.btnNo);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skippedCreatingTeams = true;
                dialog.dismiss();
                Intent intent = new Intent(CreateTeams.this, GamePlay.class);
                startActivity(intent);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamOneName = enterTeamOrPlayerName.getText().toString();
                textViewTeamName.setText(teamOneName);
                enterTeamOrPlayerName.setText("");
                enterTeamOrPlayerName.setHint(R.string.enter_player_name);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        teamOnePlayers.add(enterTeamOrPlayerName.getText().toString());
                        playerList.append(enterTeamOrPlayerName.getText().toString() + "   ");
                        enterTeamOrPlayerName.setText("");
                        if (teamOnePlayers.size() > 1) {
                            nextTeamButton.setVisibility(View.VISIBLE);
                        } else nextTeamButton.setVisibility(View.GONE);
                    }
                });
            }
        });
    }


    public void nextTeamButtonHandler (View view){
        textViewTeamName.setText("Team 2");
        textViewTeamName.setTextColor(getResources().getColor(R.color.black));
        playerList.setText("");
        enterTeamOrPlayerName.setHint(R.string.enter_team_name);
        nextTeamButton.setVisibility(View.GONE);
        i = 1;

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamTwoName = enterTeamOrPlayerName.getText().toString();
                textViewTeamName.setText(teamTwoName);
                enterTeamOrPlayerName.setText("");
                enterTeamOrPlayerName.setHint(R.string.enter_player_name);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        teamTwoPlayers.add(enterTeamOrPlayerName.getText().toString());
                        playerList.setTextColor(getResources().getColor(R.color.black));
                        playerList.append(enterTeamOrPlayerName.getText().toString() + "   ");
                        enterTeamOrPlayerName.setText("");
                        if (teamTwoPlayers.size() > 1) {
                            startButton.setVisibility(View.VISIBLE);
                        } else startButton.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
    public void startButtonHandler(View view){
        Intent intent = new Intent(CreateTeams.this, GamePlay.class);
        startActivity(intent);
    }

}