package com.app.myapp.abstracts;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class GamePlay extends AppCompatActivity {
    Button btnToMain;
    TextView textViewTeamName;
    TextView personPlaceThingTextView;
    TextView clue1TextView;
    TextView clue2TextView;
    TextView clue3TextView;
    TextView clueChosenTextView;
    TextView teamOneScore, teamTwoScore;
    RadioButton personRadioButton,placeRadioButton,thingRadioButton;
    RadioGroup radioGroup;
    String person,place,thing;
    Button newSetPeoplePlaceThingButton,newSetClueButton, rightButton, wrongButton;
    TextView textViewWhoIsClueGiverOrWhoIsPlaying, textViewClueGiverName,weenieTextView;
    Button btnNewClueGiver, btnFirst, btnSecond, btnDone,weenieButton;

    String otherTeamName = "";
    int team1Color = getResources().getColor(R.color.white);
    int team2Color = getResources().getColor(R.color.black);
    ImageView teamOnePersonToken, teamOnePlaceToken, teamOneThingToken, teamTwoPersonToken, teamTwoPlaceToken, teamTwoThingToken, personImage, placeImage, thingImage;
    Boolean teamOnePersonTokenEnabled = false, teamOnePlaceTokenEnabled = false, teamOneThingTokenEnabled = false, teamTwoPersonTokenEnabled = false, teamTwoPlaceTokenEnabled = false, teamTwoThingTokenEnabled = false;

    ArrayList<String> teamOnePlayers = CreateTeams.teamOnePlayers;
    ArrayList<String> teamTwoPlayers = CreateTeams.teamTwoPlayers;
    ConstraintLayout backgroundColor;

    Context context;
    String whichTeamHadTheLastTurn = "";
    int setCounter = 0;
    int randint = 0;
    int personPlaceThingNumber = 0; //person = 1 place = 2 thing = 3, helps with the tokens
    Random random = new Random();

    Rotate3dAnimation animation;
    Dialog dialog;
    LayoutInflater inflater;
    View dialogView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_game_play);
        backgroundColor = (ConstraintLayout)findViewById(com.app.myapp.abstracts.R.id.container);
        dialog = new Dialog(this);
        inflater = LayoutInflater.from(this);
        dialogView = inflater.inflate(com.app.myapp.abstracts.R.layout.dialog_select_clue_giver, null);

        backgroundColor.setBackgroundResource(com.app.myapp.abstracts.R.color.colorPrimary);//changes color layout

        final ABSTRACTSFileRead abstractsFileRead = new ABSTRACTSFileRead(this, PickPPTListActivity.fileNameOfListChosen);
        abstractsFileRead.newFileLine();




        btnToMain = (Button) findViewById(com.app.myapp.abstracts.R.id.btnToMain);
        textViewTeamName = (TextView)findViewById(com.app.myapp.abstracts.R.id.TeamName);
        personPlaceThingTextView = (TextView)findViewById(com.app.myapp.abstracts.R.id.personPlaceThingTextView);
        clue1TextView = (TextView)findViewById(com.app.myapp.abstracts.R.id.clue1TextView);
        clue2TextView = (TextView)findViewById(com.app.myapp.abstracts.R.id.clue2TextView);
        clue3TextView = (TextView)findViewById(com.app.myapp.abstracts.R.id.clue3TextView);
        teamOneScore = (TextView)findViewById(com.app.myapp.abstracts.R.id.teamOneScore);
        teamTwoScore = (TextView)findViewById(com.app.myapp.abstracts.R.id.teamTwoScore);
        clueChosenTextView = (TextView)findViewById(com.app.myapp.abstracts.R.id.clueChosenTextView);
        personRadioButton = (RadioButton)findViewById(com.app.myapp.abstracts.R.id.personRadioButton);
        placeRadioButton = (RadioButton)findViewById(com.app.myapp.abstracts.R.id.placeRadioButton);
        thingRadioButton = (RadioButton)findViewById(com.app.myapp.abstracts.R.id.thingRadioButton);
        radioGroup = (RadioGroup)findViewById(com.app.myapp.abstracts.R.id.radioGroup);
        newSetPeoplePlaceThingButton = (Button)findViewById(com.app.myapp.abstracts.R.id.newSetPeoplePlaceThingButton);
        newSetClueButton = (Button)findViewById(com.app.myapp.abstracts.R.id.getAClueButton);
        rightButton = (Button)findViewById(com.app.myapp.abstracts.R.id.rightButton);
        wrongButton = (Button)findViewById(com.app.myapp.abstracts.R.id.wrongButton);
        textViewWhoIsClueGiverOrWhoIsPlaying = (TextView) dialogView.findViewById(com.app.myapp.abstracts.R.id.textViewWhoIsClueGiverOrWhoIsPlaying);
        textViewClueGiverName = (TextView) dialogView.findViewById(com.app.myapp.abstracts.R.id.textViewClueGiverName);
        weenieTextView = (TextView) dialogView.findViewById(com.app.myapp.abstracts.R.id.weenieTextView);
        btnNewClueGiver = (Button) dialogView.findViewById(com.app.myapp.abstracts.R.id.btnNewClueGiver);
        btnFirst = (Button) dialogView.findViewById(com.app.myapp.abstracts.R.id.btnFirst);
        btnSecond = (Button) dialogView.findViewById(com.app.myapp.abstracts.R.id.btnSecond);
        btnDone = (Button) dialogView.findViewById(com.app.myapp.abstracts.R.id.btnDone);
        weenieButton = (Button)dialogView.findViewById(com.app.myapp.abstracts.R.id.weenieButton);
        teamOnePersonToken = (ImageView)findViewById(com.app.myapp.abstracts.R.id.teamOnePersonToken);
        teamOnePlaceToken = (ImageView)findViewById(com.app.myapp.abstracts.R.id.teamOnePlaceToken);
        teamOneThingToken = (ImageView)findViewById(com.app.myapp.abstracts.R.id.teamOneThingToken);
        teamTwoPersonToken = (ImageView)findViewById(com.app.myapp.abstracts.R.id.teamTwoPersonToken);
        teamTwoPlaceToken = (ImageView)findViewById(com.app.myapp.abstracts.R.id.teamTwoPlaceToken);
        teamTwoThingToken = (ImageView)findViewById(com.app.myapp.abstracts.R.id.teamTwoThingToken);
        personImage = (ImageView)findViewById(com.app.myapp.abstracts.R.id.personImage);
        placeImage = (ImageView)findViewById(com.app.myapp.abstracts.R.id.placeImage);
        thingImage = (ImageView)findViewById(com.app.myapp.abstracts.R.id.thingImage);

        person = abstractsFileRead.getPerson();
        place = abstractsFileRead.getPlace();
        thing = abstractsFileRead.getThing();


        btnDone.setVisibility(GONE);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        showSelectClueGiverDialog(dialog, dialogView);

        personPlaceThingTextView.setVisibility(View.GONE);
        textViewTeamName.setText(CreateTeams.teamOneName);
        whichTeamHadTheLastTurn = CreateTeams.teamOneName;
        clue1TextView.setVisibility(View.GONE);
        clue2TextView.setVisibility(View.GONE);
        clue3TextView.setVisibility(View.GONE);
        newSetClueButton.setVisibility(View.GONE);
        clueChosenTextView.setVisibility(View.GONE);
        rightButton.setVisibility(View.GONE);
        wrongButton.setVisibility(View.GONE);
        teamOnePersonToken.setVisibility(GONE);
        teamOnePlaceToken.setVisibility(GONE);
        teamOneThingToken.setVisibility(GONE);
        teamTwoPersonToken.setVisibility(GONE);
        teamTwoPlaceToken.setVisibility(GONE);
        teamTwoThingToken.setVisibility(GONE);
        teamOneScore.setText(CreateTeams.teamOneName + " Score");
        teamTwoScore.setText(CreateTeams.teamTwoName + " Score");
        newSetPeoplePlaceThingButton.setText("PPT Start");

        context = this;

        while (randint <= 1){
            randint = random.nextInt(10);
        }

        newSetPeoplePlaceThingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSetPeoplePlaceThingButton.setText("New PPT");
                setCounter++;
                if (setCounter == randint){
                    weenieDialog(dialog,dialogView);
                    randint = 0;
                    setCounter = 0;
                    while (randint <= 1){
                        randint = random.nextInt(10);
                    }
                    whichTeamHadTheLastTurn = textViewTeamName.getText().toString();
                    if (whichTeamHadTheLastTurn.matches(CreateTeams.teamOneName)){
                        textViewTeamName.setText(CreateTeams.teamTwoName);
                    }else {
                        textViewTeamName.setText(CreateTeams.teamOneName);
                    }
                }else {
                    abstractsFileRead.newFileLine();
                    person = abstractsFileRead.getPerson();
                    place = abstractsFileRead.getPlace();
                    thing = abstractsFileRead.getThing();
                    personRadioButton.setText("  " + person);
                    placeRadioButton.setText("  " + place);
                    thingRadioButton.setText("  " + thing);
                }
            }
        });

        personRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                radioButtonSetVisibilities();
                personPlaceThingTextView.setText(person);
                personPlaceThingNumber = 1;
                showingClues();
                showWhoseTurnIsItDialog(dialog, dialogView);
                setCounter = 0;
            }
        });
        placeRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                radioButtonSetVisibilities();
                personPlaceThingTextView.setText(place);
                personPlaceThingNumber = 2;
                showingClues();
                showWhoseTurnIsItDialog(dialog, dialogView);
                setCounter = 0;
            }
        });
        thingRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                radioButtonSetVisibilities();
                personPlaceThingTextView.setText(thing);
                personPlaceThingNumber = 3;
                showingClues();
                showWhoseTurnIsItDialog(dialog, dialogView);
                setCounter = 0;
            }
        });


        newSetClueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCounter++;
                if (setCounter == randint){
                    weenieDialog(dialog,dialogView);
                    randint = 0;
                    setCounter = 0;
                    while (randint <= 1){
                        randint = random.nextInt(10);
                    }
                    whichTeamHadTheLastTurn = textViewTeamName.getText().toString();
                    if (whichTeamHadTheLastTurn.matches(CreateTeams.teamOneName)){
                        textViewTeamName.setText(CreateTeams.teamTwoName);
                    }else {
                        textViewTeamName.setText(CreateTeams.teamOneName);
                    }
                }else {
                    showingClues();
                }
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randint = 0;
                while (randint <= 1){
                    randint = random.nextInt(10);
                }
                if (teamOnePersonTokenEnabled){
                    teamOnePersonToken.setVisibility(VISIBLE);
                }
                if (teamOnePlaceTokenEnabled){
                    teamOnePlaceToken.setVisibility(VISIBLE);
                }
                if (teamOneThingTokenEnabled){
                    teamOneThingToken.setVisibility(VISIBLE);
                }
                if (teamTwoPersonTokenEnabled){
                    teamTwoPersonToken.setVisibility(VISIBLE);
                }
                if (teamTwoPlaceTokenEnabled){
                    teamTwoPlaceToken.setVisibility(VISIBLE);
                }
                if (teamTwoThingTokenEnabled){
                    teamTwoThingToken.setVisibility(VISIBLE);
                }
                if (textViewTeamName.getText().equals(CreateTeams.teamOneName)){
                    if (personPlaceThingNumber == 1 || teamOnePersonTokenEnabled){
                        if(teamOnePersonTokenEnabled) {
                            teamOnePersonToken.setVisibility(VISIBLE);
                        }else {
                            teamOnePersonToken.setVisibility(VISIBLE);
                            animateCoin(teamOnePersonToken, com.app.myapp.abstracts.R.drawable.person_token_light, com.app.myapp.abstracts.R.drawable.back_of_light);
                        }
                        teamOnePersonTokenEnabled = true;
                    }
                    if (personPlaceThingNumber == 2 || teamOnePlaceTokenEnabled){
                        if (teamOnePlaceTokenEnabled) {
                            teamOnePlaceToken.setVisibility(VISIBLE);
                        }
                        else {
                            teamOnePlaceToken.setVisibility(VISIBLE);
                            animateCoin(teamOnePlaceToken, com.app.myapp.abstracts.R.drawable.place_token_light, com.app.myapp.abstracts.R.drawable.back_of_light);
                        }
                        teamOnePlaceTokenEnabled = true;
                    }
                    if (personPlaceThingNumber == 3 || teamOneThingTokenEnabled){
                        if (teamOneThingTokenEnabled){
                            teamOneThingToken.setVisibility(VISIBLE);
                        }
                        else {
                            teamOneThingToken.setVisibility(VISIBLE);
                            animateCoin(teamOneThingToken, com.app.myapp.abstracts.R.drawable.thing_token_light, com.app.myapp.abstracts.R.drawable.back_of_light);
                        }
                        teamOneThingTokenEnabled = true;
                    }
                }
                if(textViewTeamName.getText().equals(CreateTeams.teamTwoName)){
                    if(personPlaceThingNumber == 1 || teamTwoPersonTokenEnabled){
                        if (teamTwoPersonTokenEnabled) {
                            teamTwoPersonToken.setVisibility(VISIBLE);
                        }
                        else {
                            teamTwoPersonToken.setVisibility(VISIBLE);
                            animateCoin(teamTwoPersonToken, com.app.myapp.abstracts.R.drawable.person_token_dark, com.app.myapp.abstracts.R.drawable.back_of_dark);
                        }
                        teamTwoPersonTokenEnabled = true;
                    }
                    if (personPlaceThingNumber == 2 || teamTwoPlaceTokenEnabled){
                        if (teamTwoPlaceTokenEnabled) {
                            teamTwoPlaceToken.setVisibility(VISIBLE);
                        }
                        else {
                            teamTwoPlaceToken.setVisibility(VISIBLE);
                            animateCoin(teamTwoPlaceToken, com.app.myapp.abstracts.R.drawable.place_token_dark, com.app.myapp.abstracts.R.drawable.back_of_dark);
                        }
                        teamTwoPlaceTokenEnabled = true;
                    }
                    if (personPlaceThingNumber == 3 || teamTwoThingTokenEnabled){
                        if (teamTwoThingTokenEnabled) {
                            teamTwoThingToken.setVisibility(VISIBLE);
                        }
                        else {
                            teamTwoThingToken.setVisibility(VISIBLE);
                            animateCoin(teamTwoThingToken, com.app.myapp.abstracts.R.drawable.thing_token_dark, com.app.myapp.abstracts.R.drawable.back_of_dark);
                        }
                        teamTwoThingTokenEnabled = true;
                    }
                }
                if (teamOnePersonTokenEnabled && teamOnePlaceTokenEnabled && teamOneThingTokenEnabled){
                    animateCoin(teamOnePersonToken, com.app.myapp.abstracts.R.drawable.person_token_light, com.app.myapp.abstracts.R.drawable.back_of_light);
                    animateCoin(teamOnePlaceToken, com.app.myapp.abstracts.R.drawable.place_token_light, com.app.myapp.abstracts.R.drawable.back_of_light);
                    animateCoin(teamOneThingToken, com.app.myapp.abstracts.R.drawable.thing_token_light, com.app.myapp.abstracts.R.drawable.back_of_light);
                }
                if (teamTwoThingTokenEnabled && teamTwoPlaceTokenEnabled && teamTwoPersonTokenEnabled){
                    animateCoin(teamTwoPersonToken, com.app.myapp.abstracts.R.drawable.person_token_dark, com.app.myapp.abstracts.R.drawable.back_of_dark);
                    animateCoin(teamTwoPlaceToken, com.app.myapp.abstracts.R.drawable.place_token_dark, com.app.myapp.abstracts.R.drawable.back_of_dark);
                    animateCoin(teamTwoThingToken, com.app.myapp.abstracts.R.drawable.thing_token_dark, com.app.myapp.abstracts.R.drawable.back_of_dark);
                }
                radioGroup.clearCheck();
                newSetClueButton.setVisibility(GONE);
                clue1TextView.setVisibility(GONE);
                clue2TextView.setVisibility(GONE);
                clue3TextView.setVisibility(GONE);
                clueChosenTextView.setVisibility(GONE);
                personPlaceThingTextView.setVisibility(GONE);
                rightButton.setVisibility(GONE);
                wrongButton.setVisibility(GONE);
                radioGroup.setVisibility(VISIBLE);
                newSetPeoplePlaceThingButton.setVisibility(VISIBLE);
                teamOneScore.setVisibility(VISIBLE);
                teamTwoScore.setVisibility(VISIBLE);
                personImage.setVisibility(VISIBLE);
                placeImage.setVisibility(VISIBLE);
                thingImage.setVisibility(VISIBLE);
                personRadioButton.setText(person);
                placeRadioButton.setText(place);
                thingRadioButton.setText(thing);
                whichTeamHadTheLastTurn = textViewTeamName.getText().toString();

            }
        });

        wrongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randint = 0;
                setCounter = 0;
                while (randint <= 1){
                    randint = random.nextInt(10);
                }
                switchTeamNameInTextView();
                showingClues();
            }
        });

        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GamePlay.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void switchTeamNameInTextView() {
        whichTeamHadTheLastTurn = textViewTeamName.getText().toString();
        if(whichTeamHadTheLastTurn.equals(CreateTeams.teamOneName)) {
            otherTeamName = CreateTeams.teamTwoName;
            textViewTeamName.setTextColor(team2Color);
        }
        else {
            otherTeamName = CreateTeams.teamOneName;
            textViewTeamName.setTextColor(team1Color);
        }
        textViewTeamName.setText(otherTeamName);
    }

    private void animateCoin(final ImageView imageView, final int drawableFile, int darkOrLightBackground) {

        animation = new Rotate3dAnimation(imageView, drawableFile, darkOrLightBackground, 0, 180, 0, 0, 0, 0);

        animation.setRepeatCount(5); // must be odd (5+1 = 6 flips so the side will stay the same)

        animation.setDuration(110);
        animation.setInterpolator(new LinearInterpolator());

        imageView.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (teamOnePersonTokenEnabled && teamOnePlaceTokenEnabled && teamOneThingTokenEnabled) {
                    textViewTeamName.setTextColor(team1Color);
                    textViewTeamName.setText(CreateTeams.teamOneName + " Wins!!!");
                    radioGroup.setVisibility(GONE);
                    newSetPeoplePlaceThingButton.setVisibility(GONE);
                    teamOneScore.setVisibility(GONE);
                    placeImage.setVisibility(GONE);
                    personImage.setVisibility(GONE);
                    thingImage.setVisibility(GONE);
                    teamTwoScore.setVisibility(GONE);
                    teamTwoPersonToken.setVisibility(GONE);
                    teamTwoPlaceToken.setVisibility(GONE);
                    teamTwoThingToken.setVisibility(GONE);
                }else if (teamTwoThingTokenEnabled && teamTwoPlaceTokenEnabled && teamTwoPersonTokenEnabled) {
                    textViewTeamName.setTextColor(team2Color);
                    textViewTeamName.setText(CreateTeams.teamTwoName + " Wins!!!");
                    radioGroup.setVisibility(GONE);
                    newSetPeoplePlaceThingButton.setVisibility(GONE);
                    teamOneScore.setVisibility(GONE);
                    placeImage.setVisibility(GONE);
                    personImage.setVisibility(GONE);
                    thingImage.setVisibility(GONE);
                    teamTwoScore.setVisibility(GONE);
                    teamOnePersonToken.setVisibility(GONE);
                    teamOnePlaceToken.setVisibility(GONE);
                    teamOneThingToken.setVisibility(GONE);
                }
                else {
                    imageView.setImageResource(drawableFile);
                    showSelectClueGiverDialog(dialog, dialogView);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });




    }

    public void radioButtonSetVisibilities(){
        personImage.setVisibility(GONE);
        placeImage.setVisibility(GONE);
        thingImage.setVisibility(GONE);
        teamOneScore.setVisibility(GONE);
        teamTwoScore.setVisibility(GONE);
        radioGroup.setVisibility(View.GONE);
        newSetPeoplePlaceThingButton.setVisibility(View.GONE);
        personPlaceThingTextView.setVisibility(VISIBLE);
        teamOnePersonToken.setVisibility(GONE);
        teamOnePlaceToken.setVisibility(GONE);
        teamOneThingToken.setVisibility(GONE);
        teamTwoPersonToken.setVisibility(GONE);
        teamTwoPlaceToken.setVisibility(GONE);
        teamTwoThingToken.setVisibility(GONE);
        rightButton.setVisibility(VISIBLE);
        wrongButton.setVisibility(VISIBLE);
        newSetClueButton.setVisibility(VISIBLE);
        radioGroup.clearCheck();
    }

    public void showSelectClueGiverDialog(final Dialog dialog, View dialogView) {
        dialog.setContentView(dialogView);
        weenieTextView.setVisibility(GONE);
        weenieButton.setVisibility(GONE);
        btnDone.setVisibility(GONE);
        btnFirst.setVisibility(GONE);
        btnSecond.setVisibility(GONE);
        textViewClueGiverName.setVisibility(VISIBLE);
        textViewWhoIsClueGiverOrWhoIsPlaying.setText(com.app.myapp.abstracts.R.string.new_clue_giver);
        textViewClueGiverName.setText("");
        if (!CreateTeams.skippedCreatingTeams) {
            btnNewClueGiver.setVisibility(VISIBLE);
            btnNewClueGiver.setText("Spin for " + CreateTeams.teamOneName);
            btnNewClueGiver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //call animation?
                    int random = (int) (Math.random() * 10);
                    while (random >= teamOnePlayers.size()) {
                        random = (int) (Math.random() * 10);
                    }
                    textViewClueGiverName.setText(teamOnePlayers.get(random));
                    btnNewClueGiver.setText("Spin for " + CreateTeams.teamTwoName);
                    btnNewClueGiver.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //call same animation?
                            int random = (int) (Math.random() * 10);
                            while (random >= teamTwoPlayers.size()) {
                                random = (int) (Math.random() * 10);
                            }
                            textViewClueGiverName.setText(teamTwoPlayers.get(random));
                            btnDone.setVisibility(VISIBLE);
                            btnNewClueGiver.setVisibility(GONE);
                        }
                    });
                }
            });
        }

        else{
            btnNewClueGiver.setVisibility(GONE);
            btnDone.setVisibility(VISIBLE);
            textViewWhoIsClueGiverOrWhoIsPlaying.setText(R.string.now_choose_your_own_clue_givers);
        }
        dialog.show();
    }

    public void showWhoseTurnIsItDialog(final Dialog dialog, View dialogView) {
        dialog.setContentView(dialogView);
        weenieTextView.setVisibility(GONE);
        weenieButton.setVisibility(GONE);
        btnNewClueGiver.setVisibility(GONE);
        textViewClueGiverName.setVisibility(GONE);
        btnDone.setVisibility(GONE);
        textViewWhoIsClueGiverOrWhoIsPlaying.setVisibility(VISIBLE);
        btnFirst.setVisibility(VISIBLE);
        btnSecond.setVisibility(VISIBLE);
        switchTeamNameInTextView();
        textViewWhoIsClueGiverOrWhoIsPlaying.setText(otherTeamName + ", do you want to give clues first, or second?");
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewTeamName.setText(otherTeamName);
                dialog.dismiss();
            }
        });
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewTeamName.setText(whichTeamHadTheLastTurn);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void weenieDialog(final Dialog dialog, View dialogView){
        dialog.setContentView(dialogView);
        textViewWhoIsClueGiverOrWhoIsPlaying.setVisibility(GONE);
        btnNewClueGiver.setVisibility(GONE);
        btnDone.setVisibility(GONE);
        btnFirst.setVisibility(GONE);
        btnSecond.setVisibility(GONE);
        weenieButton.setVisibility(VISIBLE);
        textViewClueGiverName.setVisibility(VISIBLE);
        weenieTextView.setVisibility(VISIBLE);
        weenieTextView.setText(com.app.myapp.abstracts.R.string.weenie_message);
        textViewClueGiverName.setText(com.app.myapp.abstracts.R.string.weenie);
        weenieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void showingClues(){
        ABSTRACTSFileRead abstractsFileReadClues = new ABSTRACTSFileRead(this, "Clues");
        int wildCard = random.nextInt(5);
        if (wildCard == 1) {
            clueChosenTextView.setVisibility(GONE);
            clue1TextView.setVisibility(VISIBLE);
            clue2TextView.setVisibility(VISIBLE);
            clue3TextView.setVisibility(VISIBLE);
            clue1TextView.setText("WILD CARD!\n\nIf you were a " + abstractsFileReadClues.getClueOrClues() + ", what would you be?");
            clue2TextView.setText("If you were a " + abstractsFileReadClues.getClueOrClues() + ", what would you be?");
            clue3TextView.setText("If you were a " + abstractsFileReadClues.getClueOrClues() + ", what would you be?");
        }else {
            clue1TextView.setVisibility(GONE);
            clue2TextView.setVisibility(GONE);
            clue3TextView.setVisibility(GONE);
            clueChosenTextView.setVisibility(VISIBLE);
            clueChosenTextView.setText("If you were a " + abstractsFileReadClues.getClueOrClues() + ", what would you be?");
        }
    }

}
