package com.app.myapp.abstracts;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class RulesActivity extends AppCompatActivity {
    Button btnRulesToMain, btnRewind15Sec;
    RadioGroup radioGroup;
    RadioButton radioBtnSeeRules;
    RadioButton radioBtnHearRules;
    TextView textViewRulesContent;
    ImageButton imgBtnPlayPause;

    boolean isPlay = true;
    int length = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_rules);
        radioBtnSeeRules = (RadioButton) findViewById(com.app.myapp.abstracts.R.id.radioBtnSeeRules);
        radioBtnHearRules = (RadioButton) findViewById(com.app.myapp.abstracts.R.id.radioBtnHearRules);
        btnRulesToMain = (Button) findViewById(com.app.myapp.abstracts.R.id.btnRulesToMain);
        radioGroup = (RadioGroup) findViewById(com.app.myapp.abstracts.R.id.radioGroup);
        textViewRulesContent = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewRulesContent);
        imgBtnPlayPause = (ImageButton) findViewById(com.app.myapp.abstracts.R.id.imgBtnPlayPause);
        imgBtnPlayPause.setVisibility(GONE);
        btnRewind15Sec = (Button) findViewById(R.id.btnRewind15Sec);

        btnRewind15Sec.setVisibility(GONE);

        final MediaPlayer mediaPlayerRules = MediaPlayer.create(RulesActivity.this, R.raw.audio_rules);

        btnRulesToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayerRules.isPlaying()) {
                    mediaPlayerRules.stop();
                }
                Intent intent = new Intent(RulesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                switch (selectedId) {
                    case com.app.myapp.abstracts.R.id.radioBtnSeeRules:
                        textViewRulesContent.setText(R.string.large_text);
                        break;
                    case com.app.myapp.abstracts.R.id.radioBtnHearRules:
                        textViewRulesContent.setText("");
                        btnRewind15Sec.setVisibility(VISIBLE);
                        imgBtnPlayPause.setVisibility(VISIBLE);
                        imgBtnPlayPause.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(isPlay) {
                                    imgBtnPlayPause.setImageResource(com.app.myapp.abstracts.R.drawable.pause);
                                    mediaPlayerRules.seekTo(length);
                                    mediaPlayerRules.start();
                                    isPlay = false;
                                }
                                else {
                                    imgBtnPlayPause.setImageResource(com.app.myapp.abstracts.R.drawable.play_arrow);
                                    mediaPlayerRules.pause();
                                    length = mediaPlayerRules.getCurrentPosition();
                                    isPlay = true;
                                }
                            }
                        });
                }
            }
        });
        btnRewind15Sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                length = mediaPlayerRules.getCurrentPosition() - 15000;
                mediaPlayerRules.seekTo(length);
            }
        });

    }

}
