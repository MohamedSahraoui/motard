package io.mintit.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.mintit.myapplication.Adapter.CardEventAdapter;
import io.mintit.myapplication.Model.ItemPost;

public class MainActivity extends AppCompatActivity {

    private ImageView imageClos;
    private CardEventAdapter mAdapter;
    private RecyclerView myRecyclerView;
    private ArrayList<ItemPost> mCards = new ArrayList<>();
    private Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageClos=findViewById(R.id.imageClos);

        imageClos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        myRecyclerView= findViewById(R.id.recycler_view);
        mCards.add(new ItemPost("event1","offres","desc","https://img.ulule.com/display/f84a3f218e114fd634e3583c4becc0b4d2d4790f/thumbnail/640x360/presales/4/5/3/72354/page-1c.LJNfAWizhzEI.jpg","10:30","18:00",50,false,0,"event1","30","SEP"));
        mCards.add(new ItemPost("event2","offres","desc","https://4h10.com/wp-content/uploads/2012/06/2P8C2105-low.jpg","08:30","14:00",50,false,1,"event2","12","OCT"));
        mCards.add(new ItemPost("event3","offres","desc","https://moto-station.com/wp-content/uploads/2017/09/triumph-con-the-distinguished-gentlemans-ride-2014-1.jpg","10:00","14:00",50,false,2,"event3","26","OCT"));
        mCards.add(new ItemPost("event4","offres","desc","https://www.speedway.fr/img/contentpage/10dbf_image_original.jpg","10:30","18:00",50,false,0,"event4","7","NOV"));
        mCards.add(new ItemPost("event5","offres","desc","https://www.speedway.fr/img/contentpage/e2134_image_original.jpg","09:30","15:00",50,false,0,"event5","22","NOV"));
        mCards.add(new ItemPost("event6","offres","desc","https://www.moto-magazine.net/files/2014/12/motard-sunset-503x300.jpg","10:00","14:00",50,false,0,"event6","4","DEC"));
        mCards.add(new ItemPost("event7","offres","desc","https://cdn.shopify.com/s/files/1/0018/0659/8233/articles/top_823dd4a4-a83c-449e-951e-6bd51192bb64_300x300.jpg?v=1593837042","10:30","18:00",50,false,0,"event7","26","DEC"));
        mCards.add(new ItemPost("event8","offres","desc","http://logosolusa.com/wp-content/uploads/parser/moto-journal-Logo-1.jpg","07:30","15:00",50,false,0,"event8","04","JAN"));
        mCards.add(new ItemPost("event9","offres","desc","https://dcassetcdn.com/design_img/3873443/977344/24935170/qcxttyfc74q2btafhd76qhsx33_image.jpg","10:00","13:00",50,false,0,"event9","28","JAN"));
        initRecylerView();
    }

    public void initRecylerView() {

        mAdapter = new CardEventAdapter(this, mCards);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setNestedScrollingEnabled(false);
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickedListener((position, itemPost) -> {
            //TODO go to detail event
            String eventDetails=gson.toJson(itemPost);
            Bundle bundle = new Bundle();
            bundle.putString("Event",eventDetails);
            Intent intent = new Intent(MainActivity.this,DetailEventActivity.class);
            intent.putExtra("Event",eventDetails);
            startActivities(new Intent[]{intent},bundle);
        });

        mAdapter.setOnClickedListenerParticipate((position, itemPost) -> {
            if (itemPost.isParticipate())
                mCards.get(position).setNbpart(itemPost.getNbpart() - 1);
            else
                mCards.get(position).setNbpart(itemPost.getNbpart() + 1);
            mCards.get(position).setParticipate(!itemPost.isParticipate());
            mAdapter.notifyItemChanged(position);
        });
    }

}