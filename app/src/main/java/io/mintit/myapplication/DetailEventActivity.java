package io.mintit.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.mintit.myapplication.Model.ItemPost;

public class DetailEventActivity extends AppCompatActivity {
    private ItemPost itemPost ;
    private ImageView img_cover;
    private TextView tv_day;
    private TextView tv_month;
    private TextView tv_title_event;
    private TextView tv_time_event;
    private TextView nbr_participe;
    private ImageView img_participe;
    private TextView tv_participer;
    private ImageView img_reserve;
    private TextView tv_reserver;
    private TextView tv_description_event;
    private ImageView imageClos;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        img_cover=findViewById(R.id.img_cover);
        tv_day=findViewById(R.id.tv_day);
        tv_month=findViewById(R.id.tv_month);
        tv_title_event=findViewById(R.id.tv_title_event);
        tv_time_event=findViewById(R.id.tv_time_event);
        nbr_participe=findViewById(R.id.nbr_participe);
        img_participe=findViewById(R.id.img_participe);
        tv_participer=findViewById(R.id.tv_participer);
        img_reserve=findViewById(R.id.img_reserve);
        tv_reserver=findViewById(R.id.tv_reserver);
        tv_description_event=findViewById(R.id.tv_description_event);
        imageClos=findViewById(R.id.imageClos);

        imageClos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (getIntent().getExtras().getString("Event")!=null){
            itemPost=new Gson().fromJson(getIntent().getExtras().getString("Event"),ItemPost.class);
            tv_day.setText(itemPost.getDatejj());
            tv_month.setText(itemPost.getDatemm());
            tv_title_event.setText(itemPost.getTitre());
            tv_time_event.setText("De " + itemPost.getDateStart() + " À " + itemPost.getDateEnd());
            nbr_participe.setText(String.format("%d %s", itemPost.getNbpart(), getString(R.string.participants)));
            tv_description_event.setText(itemPost.getDescription());
            Glide.with(this)
                    .load(itemPost.getPhoto())
                    .placeholder(getResources().getDrawable(R.drawable.img1))
                    .into(img_cover);

            if (itemPost.isParticipate()){
                img_participe.setImageDrawable(getResources().getDrawable(R.drawable.participer_actif));
                tv_participer.setText(getString(R.string.tv_i_participate));
                tv_participer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            else {
                img_participe.setImageDrawable(getResources().getDrawable(R.drawable.participer_inactif));
                tv_participer.setText(getString(R.string.tv_participer));
                tv_participer.setTextColor(getResources().getColor(R.color.blue_dark));
            }

            switch (itemPost.getReservation()){
                case 0:
                    tv_reserver.setText(getString(R.string.tv_book));
                    img_reserve.setImageDrawable(getResources().getDrawable(R.drawable.reserver_state_one));
                    break;
                case 1:
                    tv_reserver.setText(getString(R.string.tv_waiting));
                    img_reserve.setImageDrawable(getResources().getDrawable(R.drawable.reserver_state_two));
                    break;
                case 2:
                    tv_reserver.setText(getString(R.string.tv_accepted));
                    img_reserve.setImageDrawable(getResources().getDrawable(R.drawable.reserver_state_three));
                    break;
            }

        }

    }
}