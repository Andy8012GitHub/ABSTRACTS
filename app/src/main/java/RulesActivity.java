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

public class RulesActivity extends AppCompatActivity {
    Button btnRulesToMain;
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
        imgBtnPlayPause.setVisibility(View.GONE);

        final MediaPlayer mediaPlayer = MediaPlayer.create(RulesActivity.this, com.app.myapp.abstracts.R.raw.audio_rules);

        btnRulesToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
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
                        textViewRulesContent.setText(com.app.myapp.abstracts.R.string.large_text);
                        break;
                    case com.app.myapp.abstracts.R.id.radioBtnHearRules:
                        textViewRulesContent.setText("");
                        imgBtnPlayPause.setVisibility(View.VISIBLE);
                        imgBtnPlayPause.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(isPlay) {
                                    imgBtnPlayPause.setImageResource(com.app.myapp.abstracts.R.drawable.pause);
                                    mediaPlayer.seekTo(length);
                                    mediaPlayer.start();
                                    isPlay = false;
                                }
                                else {
                                    imgBtnPlayPause.setImageResource(com.app.myapp.abstracts.R.drawable.play_arrow);
                                    mediaPlayer.pause();
                                    length = mediaPlayer.getCurrentPosition();
                                    isPlay = true;
                                }
                            }
                        });
                }
            }
        });

    }

}
