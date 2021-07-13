package com.example.braquage2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    Button suivantBtn;
    FragmentManager fragmentManager;
    PaperOnboardingFragment paperOnboardingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        suivantBtn=findViewById(R.id.suivantBtn);
        suivantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fragmentManager=getSupportFragmentManager();
        paperOnboardingFragment=PaperOnboardingFragment.newInstance(getDataForOnBoarding());
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentOne,paperOnboardingFragment);
        fragmentTransaction.commit();

    }

    private ArrayList<PaperOnboardingPage> getDataForOnBoarding() {
        PaperOnboardingPage src1=new PaperOnboardingPage("First","one", Color.parseColor("#ffffff"),R.drawable.group3331,R.drawable.group222);
        PaperOnboardingPage src2=new PaperOnboardingPage("First","one", Color.parseColor("#ffffff"),R.drawable.group222,R.drawable.group3331);
        ArrayList<PaperOnboardingPage> arrayList=new ArrayList<>();
        arrayList.add(src1);
        arrayList.add(src2);
        return arrayList;
    }
}