package com.sid.firenotes;

import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sid.firenotes.databinding.ActivityAddNoteBinding;

import java.util.HashMap;
import java.util.Map;

public class addNote extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityAddNoteBinding binding;

    FirebaseFirestore fStore;
    EditText noteTitle,noteContent;
    ProgressBar progressBarSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        fStore = FirebaseFirestore.getInstance();
        noteContent = findViewById(R.id.addNoteContent);
        noteTitle = binding.addNoteTitle;

        progressBarSave = findViewById(R.id.progressBar);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nTitle = noteTitle.getText().toString();
                String nContent = noteContent.getText().toString();
                if (nTitle.isEmpty()||nContent.isEmpty()){
                    Toast.makeText(addNote.this, "Can't save with Empty fields...", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBarSave.setVisibility(View.VISIBLE);
//                Save to Firebase
//                Note's Collection >>

                DocumentReference docRef= fStore.collection("notes").document();
                Map<String,Object> note = new HashMap<>();
                note.put("title",nTitle);
                note.put("content",nContent);

                docRef.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(addNote.this, "The Note is Saved...", Toast.LENGTH_SHORT).show();
                        progressBarSave.setVisibility(View.INVISIBLE);
                        onBackPressed();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addNote.this, "Error!! Try Again...", Toast.LENGTH_SHORT).show();
                        progressBarSave.setVisibility(View.INVISIBLE);
                    }
                });



            }
        });
    }

/*
    @Override
    public boolean onSupportNavigateUp() {

        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
*/
}