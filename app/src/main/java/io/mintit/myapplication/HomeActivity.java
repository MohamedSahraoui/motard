package io.mintit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private ImageView img_club;
    private ImageView img_event;
    private ImageView img_cours;
    private ImageView img_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        img_club=findViewById(R.id.img_club);
        img_event=findViewById(R.id.img_event);
        img_cours=findViewById(R.id.img_cours);
        img_profile=findViewById(R.id.img_profile);

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        img_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open page club
                Intent intent = new Intent(HomeActivity.this , ClubActivity.class);
                startActivity(intent);
            }
        });

        img_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open page event
                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        img_cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open page cours
                Intent intent = new Intent(HomeActivity.this,CoursActivity.class);
                startActivity(intent);
            }
        });

    }
}