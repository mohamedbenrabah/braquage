package com.example.braquage2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class NumeroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String id;
    EditText numero1,numero2,numero3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numero);

        mAuth = FirebaseAuth.getInstance();
        numero1=findViewById(R.id.numero1);
        numero2=findViewById(R.id.numero2);
        numero3=findViewById(R.id.numero3);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent intent=new Intent(this,ConnexionActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.deconnexion){
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(this,ConnexionActivity.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.home){
            Intent intent=new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.parametres){
            Intent intent=new Intent(this,ParametresActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void ok(View view) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String email=currentUser.getEmail();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Database").child("User");
        Query query=databaseReference.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        User user=dataSnapshot.getValue(User.class);
                        id=user.getId();
                    }
                    String num1String=numero1.getText().toString();
                    String num2String=numero2.getText().toString();
                    String num3String=numero3.getText().toString();
                    if(num1String.length()!=8){
                        numero1.setError("numero de 8 chiffres");
                        return;
                    }
                    if(num2String.length()!=8){
                        numero2.setError("numero de 8 chiffres");
                        return;
                    }
                    if(num3String.length()!=8){
                        numero3.setError("numero de 8 chiffres");
                        return;
                    }
                    //FirebaseDatabase.getInstance().getReference().child("Database").child("User").child(id).child("numeros").child("numero1").setValue(num1String);

                    HashMap hashMap=new HashMap();
                    hashMap.put("numero1",num1String);
                    hashMap.put("numero2",num2String);
                    hashMap.put("numero3",num3String);
                    FirebaseDatabase.getInstance().getReference().child("Database").child("User").child(id).child("numeros").setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(NumeroActivity.this, "Ajout réussie", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    //Toast.makeText(NumeroActivity.this, "id : "+id, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}