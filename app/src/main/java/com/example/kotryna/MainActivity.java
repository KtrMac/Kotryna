package com.example.kotryna;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edUserInput;
    TextView tvResults;
    Spinner spSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.tvMain = findViewById(R.id.tvMain);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        spSelection = (Spinner) findViewById(R.id.spSelection);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.selection_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSelection.setAdapter(adapter);

        this.edUserInput = findViewById(R.id.editUserInput);
        this.tvResults = findViewById(R.id.tvResults);
    }

    public void btnCountClick(View view) {
        String phrase = edUserInput.getText().toString();
        String charsCaption = getResources().getString(R.string.chars_caption);
        String errorMessage = getResources().getString(R.string.error_message);
        String emptyField = getResources().getString(R.string.empty_field);

        if(TextUtils.isEmpty(phrase)) {
            edUserInput.setError(errorMessage);
            Toast.makeText(this, emptyField, Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedOption = this.spSelection.getSelectedItem().toString();
        if (selectedOption.equalsIgnoreCase(charsCaption)){
            int charsCount = TextCounter.getCharsCount(phrase);
            this.tvResults.setText(String.valueOf(charsCount));
        }
        else {
            int wordsCount = TextCounter.getWordsCount(phrase);
            this.tvResults.setText(String.valueOf(wordsCount));
        }

    }
}