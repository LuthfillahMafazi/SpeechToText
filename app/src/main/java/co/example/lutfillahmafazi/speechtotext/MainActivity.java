package co.example.lutfillahmafazi.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView inputSuara;
    private Button tombolSuara;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    // deklarasi var atau object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputSuara = findViewById(R.id.inputSuara);
        tombolSuara = findViewById(R.id.tombolBicara);

        tombolSuara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanyaInputSuara();
            }
        });

    }
    // Untuk menampilkan Google Speech input dialog
    public void tanyaInputSuara(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Woi Ngomong Ngapa");

        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }
    // Untuk menerima inputan Speech dan menampilkan Text

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this,"Hasil Suara ditampilkan",Toast.LENGTH_SHORT).show();
            switch (requestCode){
                case REQ_CODE_SPEECH_INPUT: {
                    if (resultCode == RESULT_OK && null != data) {

                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        inputSuara.setText(result.get(0));
                    }
                    break;
                }
            }
    }
}
