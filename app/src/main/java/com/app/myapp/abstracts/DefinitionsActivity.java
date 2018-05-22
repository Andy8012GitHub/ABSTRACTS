package com.app.myapp.abstracts;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DefinitionsActivity extends AppCompatActivity {
    TextView textViewTeamName1;
    TextView textViewTeamName2;
    TextView textViewWhatIsAPerson;
    TextView textViewWhatIsAPlace;
    TextView textViewWhatIsAThing;
    TextView textViewDef;
    ImageView imgPersonTokenLight;
    ImageView imgPlaceTokenLight;
    ImageView imgThingTokenLight;
    ImageView imgPersonTokenDark;
    ImageView imgPlaceTokenDark;
    ImageView imgThingTokenDark;
    Button btnDefToMain;
    RadioGroup radioGroupPPT;
    Button btnTeam1GotIt;
    Button btnTeam2GotIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_definitions);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View dialogView = inflater.inflate(com.app.myapp.abstracts.R.layout.dialog_what_is_a____, null);

        textViewTeamName1 = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewTeamName1);
        textViewTeamName2 = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewTeamName2);
        textViewWhatIsAPerson  = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewWhatIsAPerson);
        textViewWhatIsAPlace = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewWhatIsAPlace);
        textViewWhatIsAThing = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewWhatIsAThing);
        imgPersonTokenLight = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgPersonTokenLight);
        imgPlaceTokenLight = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgPlaceTokenLight);
        imgThingTokenLight = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgThingTokenLight);
        imgPersonTokenDark = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgPersonTokenDark);
        imgPlaceTokenDark = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgPlaceTokenDark);
        imgThingTokenDark = (ImageView) findViewById(com.app.myapp.abstracts.R.id.imgThingTokenDark);
        btnDefToMain = (Button) findViewById(com.app.myapp.abstracts.R.id.btnDefToMain);
        radioGroupPPT = (RadioGroup) findViewById(com.app.myapp.abstracts.R.id.radioGroupPPT);
        btnTeam1GotIt = (Button) findViewById(com.app.myapp.abstracts.R.id.btnTeam1GotIt);
        btnTeam2GotIt = (Button) findViewById(com.app.myapp.abstracts.R.id.btnTeam2GotIt);
        textViewTeamName1.setText(CreateTeams.teamOneName);
        textViewTeamName2.setText(CreateTeams.teamTwoName);
        btnTeam1GotIt.setText(CreateTeams.teamOneName + " GOT IT!");
        btnTeam2GotIt.setText(CreateTeams.teamTwoName + " GOT IT!");

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(dialogView);
        Button btnOk = (Button) dialogView.findViewById(com.app.myapp.abstracts.R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        textViewWhatIsAPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                textViewDef = (TextView) dialogView.findViewById(com.app.myapp.abstracts.R.id.textViewInstructionsForCorrectOrIncorrect);
                textViewDef.setText(com.app.myapp.abstracts.R.string.person_def);
            }
        });
        textViewWhatIsAPlace.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dialog.show();
                 textViewDef = (TextView) dialogView.findViewById(com.app.myapp.abstracts.R.id.textViewInstructionsForCorrectOrIncorrect);
                 textViewDef.setText(com.app.myapp.abstracts.R.string.place_def);
             }
         });
        textViewWhatIsAThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewDef = (TextView) dialogView.findViewById(com.app.myapp.abstracts.R.id.textViewInstructionsForCorrectOrIncorrect);
                dialog.show();
                textViewDef.setText(com.app.myapp.abstracts.R.string.thing_def);
            }
        });
        btnDefToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DefinitionsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnTeam1GotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupPPT.getCheckedRadioButtonId();
                switch (selectedId) {
                    case com.app.myapp.abstracts.R.id.radioButtonPerson:
                        imgPersonTokenLight.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                        break;
                    case com.app.myapp.abstracts.R.id.radioButtonPlace:
                        imgPlaceTokenLight.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                        break;
                    case com.app.myapp.abstracts.R.id.radioButtonThing:
                        imgThingTokenLight.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                }
                radioGroupPPT.clearCheck();
            }
        });
        btnTeam2GotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupPPT.getCheckedRadioButtonId();
                switch (selectedId) {
                    case com.app.myapp.abstracts.R.id.radioButtonPerson:
                        imgPersonTokenDark.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                        break;
                    case com.app.myapp.abstracts.R.id.radioButtonPlace:
                        imgPlaceTokenDark.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                        break;
                    case com.app.myapp.abstracts.R.id.radioButtonThing:
                        imgThingTokenDark.setImageResource(com.app.myapp.abstracts.R.drawable.check_mark);
                }
                radioGroupPPT.clearCheck();
            }
        });
    }

}