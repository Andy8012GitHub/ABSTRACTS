package com.app.myapp.abstracts;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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

import java.util.Random;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.app.myapp.abstracts.GamePlay.PPTLastChosen.PERSON;
import static com.app.myapp.abstracts.GamePlay.PPTLastChosen.PLACE;
import static com.app.myapp.abstracts.GamePlay.PPTLastChosen.THING;

public class GamePlay extends AppCompatActivity {
    Dialog dialog;
    LayoutInflater inflater;
    View dialogView;

    Button btnToMain, btnWeenieSoundFX;
    TextView textViewTeamName, personPlaceThingChosenTextView, clue1TextView, clue2TextView, clue3TextView, clueChosenTextView, teamOneScore, teamTwoScore, textViewWhoIsPlaying, textViewBigTitle, textViewSubtitle;
    RadioButton personRadioButton,placeRadioButton,thingRadioButton;
    RadioGroup radioGroup;
    Button newSetPeoplePlaceThingButton, btnChangePPT, btnGetNewClue, btnGotIt, btnMissedIt, btnUndoLastScore, btnFirst, btnSecond, btnContinue;
    ImageView teamOnePersonToken, teamOnePlaceToken, teamOneThingToken, teamTwoPersonToken, teamTwoPlaceToken, teamTwoThingToken, personImage, placeImage, thingImage;
    Boolean teamOnePersonTokenEnabled = false, teamOnePlaceTokenEnabled = false, teamOneThingTokenEnabled = false, teamTwoPersonTokenEnabled = false, teamTwoPlaceTokenEnabled = false, teamTwoThingTokenEnabled = false;

    ConstraintLayout backgroundColor;
    Context context;
    int setCounter = 0;
    int randint = 0;
    Random random = new Random();
    String otherTeamName = "", whichTeamHadTheLastTurn = "", person, place, thing, team1Name = CreateTeams.teamOneName, team2Name = CreateTeams.teamTwoName;
    enum PPTLastChosen {
        PERSON, PLACE, THING
    }
    PPTLastChosen whichPPTLastChosen;
    int team1Color;
    int team2Color;
    Rotate3dAnimation animation;
    boolean weenieButtonIsOnStart = true;

    MediaPlayer mediaPlayerClapping = MediaPlayer.create(GamePlay.this, R.raw.zapsplat_multimedia_male_voice_processed_says_you_win_002_21573);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_game_play);
        dialog = new Dialog(this);
        inflater = LayoutInflater.from(this);
        dialogView = inflater.inflate(com.app.myapp.abstracts.R.layout.dialog_all_game_play_dialogs, null);

        btnToMain = (Button) findViewById(R.id.btnToMain);
        btnWeenieSoundFX = (Button) findViewById(R.id.btnWeenieSoundEffect);
        textViewTeamName = (TextView)findViewById(R.id.TeamName);
        personPlaceThingChosenTextView = (TextView)findViewById(R.id.personPlaceThingChosenTextView);
        clue1TextView = (TextView)findViewById(R.id.clue1TextView);
        clue2TextView = (TextView)findViewById(R.id.clue2TextView);
        clue3TextView = (TextView)findViewById(R.id.clue3TextView);
        teamOneScore = (TextView)findViewById(R.id.teamOneScore);
        teamTwoScore = (TextView)findViewById(R.id.teamTwoScore);
        clueChosenTextView = (TextView)findViewById(R.id.clueChosenTextView);
        personRadioButton = (RadioButton)findViewById(R.id.personRadioButton);
        placeRadioButton = (RadioButton)findViewById(R.id.placeRadioButton);
        thingRadioButton = (RadioButton)findViewById(R.id.thingRadioButton);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        newSetPeoplePlaceThingButton = (Button)findViewById(R.id.btnNewSetPPTs);
        btnGetNewClue = (Button)findViewById(R.id.btnGetNewClue);
        btnChangePPT = (Button) findViewById(R.id.btnChangePPT);
        btnGotIt = (Button)findViewById(R.id.btnGotIt);
        btnMissedIt = (Button)findViewById(R.id.btnMissedIt);
        btnUndoLastScore = (Button) findViewById(R.id.btnUndoLastScore);
        textViewWhoIsPlaying = (TextView) dialogView.findViewById(R.id.textViewWhoIsPlaying);
        textViewBigTitle = (TextView) dialogView.findViewById(R.id.textViewBigTitle);
        textViewSubtitle = (TextView) dialogView.findViewById(R.id.textViewSubtitle);
        btnFirst = (Button) dialogView.findViewById(R.id.btnFirst);
        btnSecond = (Button) dialogView.findViewById(R.id.btnSecond);
        btnContinue = (Button) dialogView.findViewById(R.id.btnContinue);
        teamOnePersonToken = (ImageView)findViewById(R.id.teamOnePersonToken);
        teamOnePlaceToken = (ImageView)findViewById(R.id.teamOnePlaceToken);
        teamOneThingToken = (ImageView)findViewById(R.id.teamOneThingToken);
        teamTwoPersonToken = (ImageView)findViewById(R.id.teamTwoPersonToken);
        teamTwoPlaceToken = (ImageView)findViewById(R.id.teamTwoPlaceToken);
        teamTwoThingToken = (ImageView)findViewById(R.id.teamTwoThingToken);
        personImage = (ImageView)findViewById(R.id.personImage);
        placeImage = (ImageView)findViewById(R.id.placeImage);
        thingImage = (ImageView)findViewById(R.id.thingImage);

        personPlaceThingChosenTextView.setVisibility(GONE);
        clue1TextView.setVisibility(View.GONE);
        clue2TextView.setVisibility(View.GONE);
        clue3TextView.setVisibility(View.GONE);
        btnGetNewClue.setVisibility(View.GONE);
        btnChangePPT.setVisibility(View.GONE);
        clueChosenTextView.setVisibility(GONE);
        textViewBigTitle.setVisibility(GONE);
        textViewSubtitle.setVisibility(GONE);
        btnGotIt.setVisibility(View.GONE);
        btnMissedIt.setVisibility(View.GONE);
        btnUndoLastScore.setVisibility(GONE);
        teamOnePersonToken.setVisibility(GONE);
        teamOnePlaceToken.setVisibility(GONE);
        teamOneThingToken.setVisibility(GONE);
        teamTwoPersonToken.setVisibility(GONE);
        teamTwoPlaceToken.setVisibility(GONE);
        teamTwoThingToken.setVisibility(GONE);

        textViewTeamName.setText(team1Name);
        whichTeamHadTheLastTurn = team1Name;
        teamOneScore.setText(team1Name + " Score");
        teamTwoScore.setText(team2Name + " Score");
        newSetPeoplePlaceThingButton.setText("PPT Start");

        backgroundColor = (ConstraintLayout)findViewById(R.id.container);
        backgroundColor.setBackgroundResource(com.app.myapp.abstracts.R.color.colorPrimary);//changes color layout
        team1Color = getResources().getColor(R.color.white);
        team2Color = getResources().getColor(R.color.black);

        final ABSTRACTSFileRead abstractsFileRead = new ABSTRACTSFileRead(this, PickPPTListActivity.fileNameOfListChosen);
        final ABSTRACTSFileRead abstractsFileReadClues = new ABSTRACTSFileRead(this, "Clues");
        final MediaPlayer mediaPlayer = MediaPlayer.create(GamePlay.this, R.raw.old_weenie_sounds);

        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GamePlay.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnWeenieSoundFX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(weenieButtonIsOnStart) {
                    mediaPlayer.start();
                    btnWeenieSoundFX.setText(R.string.stop_weenie_sound);
                    weenieButtonIsOnStart = false;
                }
                else {
                    mediaPlayer.stop();
                    mediaPlayer.prepareAsync();
                    btnWeenieSoundFX.setText(R.string.weenie_sound);
                    weenieButtonIsOnStart = true;
                }
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

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
                    switchTeamNameInTextView();
                } else {
                    abstractsFileRead.newFileLine();
                    person = abstractsFileRead.getPerson();
                    place = abstractsFileRead.getPlace();
                    thing = abstractsFileRead.getThing();
                    personRadioButton.setText(person);
                    placeRadioButton.setText(place);
                    thingRadioButton.setText(thing);
                }
            }
        });

        personRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                btnUndoLastScore.setVisibility(GONE);
                btnChangePPT.setVisibility(VISIBLE);
                radioButtonSetVisibilities();
                personPlaceThingChosenTextView.setText(person);
                whichPPTLastChosen = PERSON;
                showingClues(abstractsFileReadClues);
                showGoFirstOrSecondDialog(dialog, dialogView);
                setCounter = 0;
            }
        });
        placeRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                btnUndoLastScore.setVisibility(GONE);
                btnChangePPT.setVisibility(VISIBLE);
                radioButtonSetVisibilities();
                personPlaceThingChosenTextView.setText(place);
                whichPPTLastChosen = PLACE;
                showingClues(abstractsFileReadClues);
                showGoFirstOrSecondDialog(dialog, dialogView);
                setCounter = 0;
            }
        });
        thingRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                btnUndoLastScore.setVisibility(GONE);
                btnChangePPT.setVisibility(VISIBLE);
                radioButtonSetVisibilities();
                personPlaceThingChosenTextView.setText(thing);
                whichPPTLastChosen = THING;
                showingClues(abstractsFileReadClues);
                showGoFirstOrSecondDialog(dialog, dialogView);
                setCounter = 0;
            }
        });


        btnGetNewClue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCounter++;
                if (setCounter == randint){
                    weenieDialog(dialog, dialogView);
                    randint = 0;
                    setCounter = 0;
                    while (randint <= 1){
                        randint = random.nextInt(10);
                    }
                    switchTeamNameInTextView();
                }else {
                    showingClues(abstractsFileReadClues);
                }
            }
        });

        btnChangePPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personPlaceThingChosenTextView.setVisibility(GONE);
                clue1TextView.setVisibility(View.GONE);
                clue2TextView.setVisibility(View.GONE);
                clue3TextView.setVisibility(View.GONE);
                btnGetNewClue.setVisibility(View.GONE);
                btnChangePPT.setVisibility(View.GONE);
                clueChosenTextView.setVisibility(View.GONE);
                btnGotIt.setVisibility(View.GONE);
                btnMissedIt.setVisibility(View.GONE);
                teamOnePersonToken.setVisibility(GONE);
                teamOnePlaceToken.setVisibility(GONE);
                teamOneThingToken.setVisibility(GONE);
                teamTwoPersonToken.setVisibility(GONE);
                teamTwoPlaceToken.setVisibility(GONE);
                teamTwoThingToken.setVisibility(GONE);
                personImage.setVisibility(VISIBLE);
                placeImage.setVisibility(VISIBLE);
                thingImage.setVisibility(VISIBLE);
                radioGroup.setVisibility(VISIBLE);
                newSetPeoplePlaceThingButton.setVisibility(VISIBLE);
            }
        });

        btnGotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCongratsAndNewClueGiversDialog(dialog, dialogView);
            }
        });

        btnMissedIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randint = 0;
                setCounter = 0;
                while (randint <= 1){
                    randint = random.nextInt(10);
                }
                switchTeamNameInTextView();
                showingClues(abstractsFileReadClues);
            }
        });
        
        btnUndoLastScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(whichTeamHadTheLastTurn.equals(team1Name)) {
                    switch(whichPPTLastChosen) {
                        case PERSON:
                            teamOnePersonToken.setVisibility(GONE);
                            teamOnePersonTokenEnabled = false;
                            break;
                        case PLACE:
                            teamOnePlaceToken.setVisibility(GONE);
                            teamOnePlaceTokenEnabled = false;
                            break;
                        case THING:
                            teamOneThingToken.setVisibility(GONE);
                            teamOneThingTokenEnabled = false;
                    }
                } else {
                    switch(whichPPTLastChosen) {
                        case PERSON:
                            teamTwoPersonToken.setVisibility(GONE);
                            teamTwoPersonTokenEnabled = false;
                            break;
                        case PLACE:
                            teamTwoPlaceToken.setVisibility(GONE);
                            teamTwoPlaceTokenEnabled = false;
                            break;
                        case THING:
                            teamTwoThingToken.setVisibility(GONE);
                            teamTwoThingTokenEnabled = false;
                    }
                }
            }
        });
    }

    private void switchTeamNameInTextView() {
        whichTeamHadTheLastTurn = textViewTeamName.getText().toString();
        if(whichTeamHadTheLastTurn.equals(team1Name)) {
            otherTeamName = team2Name;
            textViewTeamName.setTextColor(team2Color);
        }
        else {
            otherTeamName = team1Name;
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
                    textViewTeamName.setText(getString(R.string.won_with_placeholder, team1Name));
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
                } else if (teamTwoThingTokenEnabled && teamTwoPlaceTokenEnabled && teamTwoPersonTokenEnabled) {
                    textViewTeamName.setTextColor(team2Color);
                    textViewTeamName.setText(getString(R.string.won_with_placeholder, team2Name));
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
        personPlaceThingChosenTextView.setVisibility(VISIBLE);
        teamOnePersonToken.setVisibility(GONE);
        teamOnePlaceToken.setVisibility(GONE);
        teamOneThingToken.setVisibility(GONE);
        teamTwoPersonToken.setVisibility(GONE);
        teamTwoPlaceToken.setVisibility(GONE);
        teamTwoThingToken.setVisibility(GONE);
        btnGotIt.setVisibility(VISIBLE);
        btnMissedIt.setVisibility(VISIBLE);
        btnGetNewClue.setVisibility(VISIBLE);
        radioGroup.clearCheck();
    }

    public void showGoFirstOrSecondDialog(final Dialog dialog, View dialogView) {
        dialog.setContentView(dialogView);
        textViewBigTitle.setVisibility(GONE);
        textViewSubtitle.setVisibility(GONE);
        btnContinue.setVisibility(GONE);
        textViewWhoIsPlaying.setVisibility(VISIBLE);
        btnFirst.setVisibility(VISIBLE);
        btnSecond.setVisibility(VISIBLE);
        switchTeamNameInTextView();
        textViewWhoIsPlaying.setText(getString(R.string.first_or_second_with_placeholder, otherTeamName));
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFirst.setVisibility(GONE);
                btnSecond.setVisibility(GONE);
                textViewWhoIsPlaying.setVisibility(GONE);
                dialog.dismiss();
            }
        });
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTeamNameInTextView();
                btnFirst.setVisibility(GONE);
                btnSecond.setVisibility(GONE);
                textViewWhoIsPlaying.setVisibility(GONE);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showCongratsAndNewClueGiversDialog(final Dialog dialog, View dialogView) {
        dialog.setContentView(dialogView);
        textViewSubtitle.setVisibility(VISIBLE);
        textViewSubtitle.setVisibility(VISIBLE);
        btnContinue.setVisibility(VISIBLE);
        textViewBigTitle.setText(getString(R.string.congrats_with_placeholder, whichTeamHadTheLastTurn));
        textViewSubtitle.setText(R.string.new_cg);
        dialog.show();
        btnContinue.setOnClickListener(new View.OnClickListener() {
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
                if (textViewTeamName.getText().equals(team1Name)){
                    if (whichPPTLastChosen == PERSON || teamOnePersonTokenEnabled){
                        if(teamOnePersonTokenEnabled) {
                            teamOnePersonToken.setVisibility(VISIBLE);
                        }else {
                            teamOnePersonToken.setVisibility(VISIBLE);
                            animateCoin(teamOnePersonToken, com.app.myapp.abstracts.R.drawable.person_token_light, com.app.myapp.abstracts.R.drawable.back_of_light);
                            mediaPlayerClapping.start();
                        }
                        teamOnePersonTokenEnabled = true;
                    }
                    if (whichPPTLastChosen == PLACE || teamOnePlaceTokenEnabled){
                        if (teamOnePlaceTokenEnabled) {
                            teamOnePlaceToken.setVisibility(VISIBLE);
                        }
                        else {
                            teamOnePlaceToken.setVisibility(VISIBLE);
                            animateCoin(teamOnePlaceToken, com.app.myapp.abstracts.R.drawable.place_token_light, com.app.myapp.abstracts.R.drawable.back_of_light);
                        }
                        teamOnePlaceTokenEnabled = true;
                    }
                    if (whichPPTLastChosen == THING || teamOneThingTokenEnabled){
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
                if(textViewTeamName.getText().equals(team2Name)){
                    if(whichPPTLastChosen == PERSON || teamTwoPersonTokenEnabled){
                        if (teamTwoPersonTokenEnabled) {
                            teamTwoPersonToken.setVisibility(VISIBLE);
                        }
                        else {
                            teamTwoPersonToken.setVisibility(VISIBLE);
                            animateCoin(teamTwoPersonToken, com.app.myapp.abstracts.R.drawable.person_token_dark, com.app.myapp.abstracts.R.drawable.back_of_dark);
                        }
                        teamTwoPersonTokenEnabled = true;
                    }
                    if (whichPPTLastChosen == PLACE || teamTwoPlaceTokenEnabled){
                        if (teamTwoPlaceTokenEnabled) {
                            teamTwoPlaceToken.setVisibility(VISIBLE);
                        }
                        else {
                            teamTwoPlaceToken.setVisibility(VISIBLE);
                            animateCoin(teamTwoPlaceToken, com.app.myapp.abstracts.R.drawable.place_token_dark, com.app.myapp.abstracts.R.drawable.back_of_dark);
                        }
                        teamTwoPlaceTokenEnabled = true;
                    }
                    if (whichPPTLastChosen == THING || teamTwoThingTokenEnabled){
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
                btnGetNewClue.setVisibility(GONE);
                btnChangePPT.setVisibility(GONE);
                clue1TextView.setVisibility(GONE);
                clue2TextView.setVisibility(GONE);
                clue3TextView.setVisibility(GONE);
                clueChosenTextView.setVisibility(GONE);
                personPlaceThingChosenTextView.setVisibility(GONE);
                btnGotIt.setVisibility(GONE);
                btnMissedIt.setVisibility(GONE);
                radioGroup.setVisibility(VISIBLE);
                newSetPeoplePlaceThingButton.setVisibility(VISIBLE);
                btnUndoLastScore.setVisibility(VISIBLE);
                teamOneScore.setVisibility(VISIBLE);
                teamTwoScore.setVisibility(VISIBLE);
                personImage.setVisibility(VISIBLE);
                placeImage.setVisibility(VISIBLE);
                thingImage.setVisibility(VISIBLE);
                personRadioButton.setText(R.string.person);
                placeRadioButton.setText(R.string.place);
                thingRadioButton.setText(R.string.thing);
                whichTeamHadTheLastTurn = textViewTeamName.getText().toString();

                dialog.dismiss();
            }
        });
    }

    public void weenieDialog(final Dialog dialog, View dialogView){
        dialog.setContentView(dialogView);
        btnContinue.setVisibility(VISIBLE);
        textViewBigTitle.setVisibility(VISIBLE);
        textViewSubtitle.setVisibility(VISIBLE);
        textViewBigTitle.setText(R.string.weenie);
        textViewSubtitle.setText(R.string.weenie_message);
        dialog.show();
    }
    public void showingClues(ABSTRACTSFileRead abstractsFileReadClues){
        int wildCard = random.nextInt(5);
        if (wildCard == 1) {
            clueChosenTextView.setVisibility(GONE);
            clue1TextView.setVisibility(VISIBLE);
            clue2TextView.setVisibility(VISIBLE);
            clue3TextView.setVisibility(VISIBLE);
            String clue1 = abstractsFileReadClues.getClueOrClues();
            String clue2 = abstractsFileReadClues.getClueOrClues();
            String clue3 = abstractsFileReadClues.getClueOrClues();

            clue1TextView.setText(getString(R.string.clue_with_placeholder, clue1));
            clue2TextView.setText(getString(R.string.clue_with_placeholder, clue2));
            clue3TextView.setText(getString(R.string.clue_with_placeholder, clue3));
        }else {
            clue1TextView.setVisibility(VISIBLE);
            clue2TextView.setVisibility(VISIBLE);
            clue3TextView.setVisibility(VISIBLE);
            //clueChosenTextView.setVisibility(VISIBLE);
            String clueChosen = abstractsFileReadClues.getClueOrClues();
            //clueChosenTextView.setText(getString(R.string.clue_with_placeholder, clueChosen));
            clue1TextView.setText(R.string.if_you_were_a);
            clue2TextView.setText(clueChosen);
            clue3TextView.setText(R.string.I_would_be);
        }
    }

}
