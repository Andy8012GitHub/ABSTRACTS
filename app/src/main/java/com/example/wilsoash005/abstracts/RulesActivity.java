package com.example.wilsoash005.abstracts;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
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
        setContentView(R.layout.activity_rules);
        radioBtnSeeRules = (RadioButton) findViewById(R.id.radioBtnSeeRules);
        radioBtnHearRules = (RadioButton) findViewById(R.id.radioBtnHearRules);
        btnRulesToMain = (Button) findViewById(R.id.btnRulesToMain);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        textViewRulesContent = (TextView) findViewById(R.id.textViewRulesContent);
        imgBtnPlayPause = (ImageButton) findViewById(R.id.imgBtnPlayPause);
        imgBtnPlayPause.setVisibility(View.GONE);

        final MediaPlayer mediaPlayer = MediaPlayer.create(RulesActivity.this, R.raw.audio_rules);

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
                    case R.id.radioBtnSeeRules:
                        textViewRulesContent.setText(R.string.large_text);
                        break;
                    case R.id.radioBtnHearRules:
                        textViewRulesContent.setText("");
                        imgBtnPlayPause.setVisibility(View.VISIBLE);
                        imgBtnPlayPause.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(isPlay) {
                                    imgBtnPlayPause.setImageResource(R.drawable.pause);
                                    mediaPlayer.seekTo(length);
                                    mediaPlayer.start();
                                    isPlay = false;
                                }
                                else {
                                    imgBtnPlayPause.setImageResource(R.drawable.play_arrow);
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
