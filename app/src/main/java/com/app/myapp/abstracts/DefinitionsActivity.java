package com.app.myapp.abstracts;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

import static android.view.View.VISIBLE;
import static com.app.myapp.abstracts.DefinitionsActivity.PPTLastChosen.PERSON;
import static com.app.myapp.abstracts.DefinitionsActivity.PPTLastChosen.PLACE;
import static com.app.myapp.abstracts.DefinitionsActivity.PPTLastChosen.THING;

public class DefinitionsActivity extends AppCompatActivity {
    TextView textViewWhatIsAPerson;
    TextView textViewWhatIsAPlace;
    TextView textViewWhatIsAThing;
    TextView textViewDef;
    TextView textViewTeamsAreGuessingA;
    ImageView imgPersonTokenLight;
    ImageView imgPlaceTokenLight;
    ImageView imgThingTokenLight;
    ImageView imgPersonTokenDark;
    ImageView imgPlaceTokenDark;
    ImageView imgThingTokenDark;
    Button btnDefToMain, btnWeenieSoundFX;
    RadioGroup radioGroupPPT;
    Button btnTeam1GotIt;
    Button btnTeam2GotIt;
    Button btnUndo;

    Random random = new Random();

    boolean weenieButtonIsOnStart = true;
    int teamThatHadLastTurn;
    enum PPTLastChosen {
        PERSON, PLACE, THING
    }
    PPTLastChosen pptLastChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_definitions);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View dialogView = inflater.inflate(com.app.myapp.abstracts.R.layout.dialog_what_is_a____, null);

        textViewWhatIsAPerson  = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewWhatIsAPerson);
        textViewWhatIsAPlace = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewWhatIsAPlace);
        textViewWhatIsAThing = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewWhatIsAThing);
        textViewTeamsAreGuessingA = (TextView) findViewById(R.id.textViewTeamsAreGuessingA);
        textViewDef = (TextView) dialogView.findViewById(com.app.myapp.abstracts.R.id.textViewDefinition);
        imgPersonTokenLight = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgPersonTokenLight);
        imgPlaceTokenLight = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgPlaceTokenLight);
        imgThingTokenLight = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgThingTokenLight);
        imgPersonTokenDark = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgPersonTokenDark);
        imgPlaceTokenDark = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgPlaceTokenDark);
        imgThingTokenDark = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgThingTokenDark);
        btnDefToMain = (Button) findViewById(com.app.myapp.abstracts.R.id.btnDefToMain);
        btnWeenieSoundFX = (Button) findViewById(R.id.btnWeenieSoundEffect);
        radioGroupPPT = (RadioGroup) findViewById(com.app.myapp.abstracts.R.id.radioGroupPPT);
        btnTeam1GotIt = (Button) findViewById(com.app.myapp.abstracts.R.id.btnTeam1GotIt);
        btnTeam2GotIt = (Button) findViewById(com.app.myapp.abstracts.R.id.btnTeam2GotIt);
        btnUndo = (Button) findViewById(R.id.btnUndo);

        btnTeam1GotIt.setText(CreateTeams.teamOneName + " GOT IT!");
        btnTeam2GotIt.setText(CreateTeams.teamTwoName + " GOT IT!");

        final MediaPlayer mediaPlayerWeenie = MediaPlayer.create(DefinitionsActivity.this, R.raw.weenie);
        final MediaPlayer mediaPlayerYouAreAWeenie = MediaPlayer.create(DefinitionsActivity.this, R.raw.youareaweenie);
        final MediaPlayer mediaPlayerWeenieSong = MediaPlayer.create(DefinitionsActivity.this, R.raw.weeniesong);

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(dialogView);
        Button btnOk = (Button) dialogView.findViewById(com.app.myapp.abstracts.R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnDefToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DefinitionsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnWeenieSoundFX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int weenieRandom = random.nextInt(3);
                if(weenieRandom == 0){
                    mediaPlayerWeenie.start();
                }
                else if (weenieRandom == 1){
                    mediaPlayerWeenieSong.start();
                }
                else{
                    mediaPlayerYouAreAWeenie.start();
                }
            }
        });
        textViewWhatIsAPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                textViewDef.setText(com.app.myapp.abstracts.R.string.person_def);
            }
        });
        textViewWhatIsAPlace.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dialog.show();
                 textViewDef.setText(com.app.myapp.abstracts.R.string.place_def);
             }
         });
        textViewWhatIsAThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                textViewDef.setText(com.app.myapp.abstracts.R.string.thing_def);
            }
        });
        btnTeam1GotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewTeamsAreGuessingA.setText(R.string.teams_are_guessing_a);
                teamThatHadLastTurn = 1;
                int selectedId = radioGroupPPT.getCheckedRadioButtonId();
                if(selectedId == -1) {
                    textViewTeamsAreGuessingA.setText(R.string.specify_ppt);
                    return;
                }
                btnUndo.setVisibility(VISIBLE);
                switch (selectedId) {
                    case com.app.myapp.abstracts.R.id.radioButtonPerson:
                        pptLastChosen = PERSON;
                        imgPersonTokenLight.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                        break;
                    case com.app.myapp.abstracts.R.id.radioButtonPlace:
                        pptLastChosen = PLACE;
                        imgPlaceTokenLight.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                        break;
                    case com.app.myapp.abstracts.R.id.radioButtonThing:
                        pptLastChosen = THING;
                        imgThingTokenLight.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                }
                radioGroupPPT.clearCheck();
            }
        });
        btnTeam2GotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewTeamsAreGuessingA.setText(R.string.teams_are_guessing_a);
                teamThatHadLastTurn = 2;
                int selectedId = radioGroupPPT.getCheckedRadioButtonId();
                if(selectedId == -1) {
                    textViewTeamsAreGuessingA.setText(R.string.specify_ppt);
                    return;
                }
                btnUndo.setVisibility(VISIBLE);
                switch (selectedId) {
                    case com.app.myapp.abstracts.R.id.radioButtonPerson:
                        pptLastChosen = PERSON;
                        imgPersonTokenDark.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                        break;
                    case com.app.myapp.abstracts.R.id.radioButtonPlace:
                        pptLastChosen = PLACE;
                        imgPlaceTokenDark.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                        break;
                    case com.app.myapp.abstracts.R.id.radioButtonThing:
                        pptLastChosen = THING;
                        imgThingTokenDark.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                }
                radioGroupPPT.clearCheck();
            }
        });
        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(teamThatHadLastTurn == 1) {
                    switch(pptLastChosen) {
                        case PERSON:
                            imgPersonTokenLight.setImageResource(R.drawable.person_token_light);
                            break;
                        case PLACE:
                            imgPlaceTokenLight.setImageResource(R.drawable.place_token_light);
                            break;
                        case THING:
                            imgThingTokenLight.setImageResource(R.drawable.thing_token_light);
                    }
                } else {
                    switch(pptLastChosen) {
                        case PERSON:
                            imgPersonTokenDark.setImageResource(R.drawable.person_token_dark);
                            break;
                        case PLACE:
                            imgPlaceTokenDark.setImageResource(R.drawable.place_token_dark);
                            break;
                        case THING:
                            imgThingTokenDark.setImageResource(R.drawable.thing_token_dark);
                    }
                }
            }
        });
        }

}