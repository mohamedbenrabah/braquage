package com.example.braquage2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    User user;
    EditText editNom,editEmail,editPassword,editConfirmPassword;
    RadioButton RadioButtonHomme,RadioButtonFemme;
    Button btnInscription,btnDateNaiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recupreration();


        /*FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("Database");
        user=new User("jihed","Homme","21-08-1997","jihed@gmail.com","azzezas","no-photo");
        myRef.child("User").push().setValue(user);*/

    }

    private void recupreration() {
        editNom=findViewById(R.id.editNom);
        editEmail=findViewById(R.id.editEmail);
        editPassword=findViewById(R.id.editPassword);
        editConfirmPassword=findViewById(R.id.editConfirmPassword);
        RadioButtonHomme=findViewById(R.id.hommeRadio);
        RadioButtonFemme=findViewById(R.id.femmeRadio);
        btnInscription=findViewById(R.id.BtnInscription);
        btnDateNaiss=findViewById(R.id.btnDateNaiss);
    }

    public void connexion(View view) {
        Toast.makeText(this, "pas encore", Toast.LENGTH_SHORT).show();
    }
}