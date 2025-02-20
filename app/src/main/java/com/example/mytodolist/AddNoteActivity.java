package com.example.mytodolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private RadioButton radioBtnLow;
    private RadioButton radioBtnMedium;
    private RadioButton radioBtnHigh;
    private Button btnSave;


    private AddNoteViewModel addNoteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addNoteViewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);

        addNoteViewModel.getShouldCloseScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldClose) {
                if (shouldClose)
                    finish();
            }
        });

        initViews();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void initViews() {
        editTextNote = findViewById(R.id.editTextNote);
        radioBtnLow = findViewById(R.id.radioBtnLow);
        radioBtnMedium = findViewById(R.id.radioBtnMedium);
        radioBtnHigh = findViewById(R.id.radioBtnHigh);
        btnSave = findViewById(R.id.btnSave);
    }

    private void saveNote() {
        String text = editTextNote.getText().toString().trim();
        int priority = getPriority();
        Note note = new Note(text, priority);
        addNoteViewModel.saveNote(note);
    }

    private int getPriority() {
        int priority;
        if (radioBtnLow.isChecked()) {
            priority = 0;
        } else if (radioBtnMedium.isChecked()) {
            priority = 1;
        } else {
            priority = 2;
        }
        return priority;

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);
    }
}