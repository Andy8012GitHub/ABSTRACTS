package com.example.wilsoash005.abstracts;
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
    EditText playersName, enterTeam;
    TextView playerList;
    static String teamOneName = "Team 1";
    static String teamTwoName = "Team 2";
    TextView teamTextView;
    Button submitTeam,submitButton,nextTeamButton,startButton;
    TextView textViewChooseYourOwnClueGiver;
    Button btnYes;
    Button btnNo;
    int i = 0;
    static ArrayList<String> teamOnePlayers = new ArrayList<>();
    static ArrayList<String> teamTwoPlayers = new ArrayList<>();
    static boolean skippedCreatingTeams = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teams);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View dialogView = inflater.inflate(R.layout.dialog_skip_create_teams, null);

        playersName = (EditText)findViewById(R.id.playersName);
        playerList = (TextView)findViewById(R.id.playerList);
        teamTextView = (TextView)findViewById(R.id.textViewTeamName);
        enterTeam = (EditText)findViewById(R.id.enterTeam);
        submitTeam = (Button)findViewById(R.id.submitTeam);
        submitButton = (Button)findViewById(R.id.submitButton);
        nextTeamButton = (Button)findViewById(R.id.nextTeamButton);
        startButton = (Button)findViewById(R.id.startButton);
        submitButton.setVisibility(View.GONE);
        playersName.setVisibility(View.GONE);
        nextTeamButton.setVisibility(View.GONE);
        startButton.setVisibility(View.GONE);
        teamTextView.setText("Team 1");

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(dialogView);
        textViewChooseYourOwnClueGiver = (TextView) dialogView.findViewById(R.id.textViewChooseYourOwnClueGivers);
        btnYes = (Button) dialogView.findViewById(R.id.btnYes);
        btnNo = (Button) dialogView.findViewById(R.id.btnNo);
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
    }


    public void submitButtonHandler (View view){
        if (i == 0) {
            teamOnePlayers.add(playersName.getText().toString());
            playerList.append(playersName.getText().toString() + "   ");
            playersName.setText("");
            if(teamOnePlayers.size() > 1){
                nextTeamButton.setVisibility(View.VISIBLE);
            }else nextTeamButton.setVisibility(View.GONE);
        }else{
            teamTwoPlayers.add(playersName.getText().toString());
            playerList.append(playersName.getText().toString() + "   ");
            playersName.setText("");
            if(teamTwoPlayers.size() > 1){
                startButton.setVisibility(View.VISIBLE);
            }else startButton.setVisibility(View.GONE);

        }
    }

    public void submitTeamButtonHandler (View view){
        if (i == 0) {
            teamOneName = enterTeam.getText().toString();
            teamTextView.setText(teamOneName);
            enterTeam.setText("");
            enterTeam.setVisibility(View.GONE);
            submitTeam.setVisibility(View.GONE);
            submitButton.setVisibility(View.VISIBLE);
            playersName.setVisibility(View.VISIBLE);
        } else {
            teamTwoName = enterTeam.getText().toString();
            teamTextView.setText(teamTwoName);
            enterTeam.setText("");
            enterTeam.setVisibility(View.GONE);
            submitTeam.setVisibility(View.GONE);
            submitButton.setVisibility(View.VISIBLE);
            playersName.setVisibility(View.VISIBLE);
        }
    }

    public void nextTeamButtonHandler (View view){
        teamTextView.setText("Team 2");
        playerList.setText("");
        playersName.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
        enterTeam.setVisibility(View.VISIBLE);
        submitTeam.setVisibility(View.VISIBLE);
        nextTeamButton.setVisibility(View.GONE);
        i = 1;
    }
    public void startButtonHandler(View view){
        Intent intent = new Intent(CreateTeams.this, GamePlay.class);
        startActivity(intent);
    }

}