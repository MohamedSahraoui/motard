/**
 * Copyright 2017 Harish Sridharan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mintit.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import io.mintit.myapplication.Model.ItemPost;
import io.mintit.myapplication.R;


public class CardEventAdapter extends RecyclerView.Adapter<CardEventAdapter.ItemHolder> {
    private final Activity act;
    OnClickedListener onClickedListener;
    OnClickedListenerParticipate onClickedListenerParticipate;
    OnClickedListenerReserve onClickedListenerReserve;
    private ArrayList<ItemPost> mCards = new ArrayList<>();

    public CardEventAdapter(Activity mainActivity, ArrayList<ItemPost> mCards) {
        this.mCards = mCards;
        this.act = mainActivity;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup container, int viewType) {
        View root = LayoutInflater.from(container.getContext())
                .inflate(R.layout.layout_news_card
                        , container, false);

        return new ItemHolder(root);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(mCards.get(position));
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public void setCards(ArrayList<ItemPost> cards) {
        if (cards == null) {
            return;
        }

        mCards = cards;

    }


    public void addCard(ItemPost userItem) {
        mCards.add(userItem);
        notifyItemInserted(mCards.size() - 1);
    }

    public void clearList() {
        mCards.clear();
    }

    public void setOnClickedListener(OnClickedListener onClickedListenerGallery) {
        this.onClickedListener = onClickedListenerGallery;
    }

    public void notifyDataSetChanged(ArrayList<ItemPost> mCards) {
            this.mCards = mCards;
            notifyDataSetChanged();


    }

    public void notifyDataSetChanged(ItemPost itemPost, int i) {
        mCards.set(i,itemPost);
        notifyDataSetChanged();
    }


    public interface OnClickedListener {
        public void onClickedListener(int position,ItemPost itemPost);

    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleView;
        private TextView mDescView;
        private ImageView mThumbnailView;
        private TextView mSummaryView;
        private LinearLayout btn_participe;
        private LinearLayout btn_reserve;
        private TextView nbr_participe;
        private ImageView img_participe;
        private TextView tv_month;
        private TextView tv_day;
        private TextView tv_participer;
        private ImageView img_reserve;
        private TextView tv_reserver;
        private TextView tv_time_event;
        private String time_event;
        private String day_event;
        private String month_event;


        private ItemHolder(View itemView) {
            super(itemView);

            mDescView = itemView.findViewById(R.id.tv_description_event);
            mTitleView = itemView.findViewById(R.id.tv_title_event);
            mSummaryView = itemView.findViewById(R.id.tv_date_event);
            mThumbnailView = itemView.findViewById(R.id.card_image);
            tv_day = itemView.findViewById(R.id.tv_day);
            tv_month = itemView.findViewById(R.id.tv_month);
            btn_reserve= itemView.findViewById(R.id.btn_reserve);
            btn_participe=itemView.findViewById(R.id.btn_participe);
            nbr_participe=itemView.findViewById(R.id.nbr_participe);
            img_participe=itemView.findViewById(R.id.img_participe);
            tv_participer=itemView.findViewById(R.id.tv_participer);
            tv_reserver=itemView.findViewById(R.id.tv_reserver);
            img_reserve=itemView.findViewById(R.id.img_reserve);
            tv_time_event=itemView.findViewById(R.id.tv_time_event);
            itemView.setOnClickListener(this);
            btn_participe.setOnClickListener(this);
            btn_reserve.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        public void bind(ItemPost card) {

            if (!TextUtils.isEmpty(card.getDateEnd())) {
                tv_time_event.setText("De " + card.getDateStart() + " À " + card.getDateEnd());
            }else {
                tv_time_event.setText(card.getDateStart());
            }

            tv_day.setText(card.getDatejj());
            tv_month.setText(card.getDatemm());

            mDescView.setText(card.getDescription());
            mTitleView.setText(card.getTitre());
            if (!TextUtils.isEmpty(card.getDateStart())) {
                mSummaryView.setText(card.getDateStart());
            }else {
                mSummaryView.setText("");
            }

            Glide.with(act)
                    .load(card.getPhoto())
                    .placeholder(act.getResources().getDrawable(R.drawable.img1))
                    .into(mThumbnailView);
            //mThumbnailView.setImageDrawable(act.getResources().getDrawable(R.drawable.img1));

            if (card.isParticipate()){
                img_participe.setImageDrawable(act.getResources().getDrawable(R.drawable.participer_actif));
                tv_participer.setText(act.getString(R.string.tv_i_participate));
                tv_participer.setTextColor(act.getResources().getColor(R.color.colorPrimaryDark));
            }
            else {
                img_participe.setImageDrawable(act.getResources().getDrawable(R.drawable.participer_inactif));
                tv_participer.setText(act.getString(R.string.tv_participer));
                tv_participer.setTextColor(act.getResources().getColor(R.color.blue_dark));
            }
            nbr_participe.setText(String.format("%d %s", card.getNbpart(), act.getString(R.string.participants)));

        switch (card.getReservation()){
            case 0:
                tv_reserver.setText(act.getString(R.string.tv_book));
                img_reserve.setImageDrawable(act.getResources().getDrawable(R.drawable.reserver_state_one));
                break;
            case 1:
                tv_reserver.setText(act.getString(R.string.tv_waiting));
                img_reserve.setImageDrawable(act.getResources().getDrawable(R.drawable.reserver_state_two));
                break;
            case 2:
                tv_reserver.setText(act.getString(R.string.tv_accepted));
                img_reserve.setImageDrawable(act.getResources().getDrawable(R.drawable.reserver_state_three));
                break;
            }
        }
        @Override
        public void onClick(View v) {
            if (v.getId() == itemView.getId()) {
                if (onClickedListener != null) {
                    onClickedListener.onClickedListener(getPosition(),mCards.get(getPosition()));}
            }
            if (v.getId() == btn_participe.getId()) {
                if (onClickedListenerParticipate != null) {
                    onClickedListenerParticipate.onClickedListenerParticipate(getPosition(),mCards.get(getPosition()));}
            }
            if (v.getId()== btn_reserve.getId()){
                if (onClickedListenerReserve != null){
                    onClickedListenerReserve.onClickedListenerReserve(getPosition(),mCards.get(getPosition()));}
                }
            }
        }




    public void setOnClickedListenerParticipate(OnClickedListenerParticipate onClickedListenerGallery) {
        this.onClickedListenerParticipate = onClickedListenerGallery;
    }

    public interface OnClickedListenerParticipate {
        public void onClickedListenerParticipate(int position, ItemPost itemPost);

    }

    public void setOnClickedListenerReserve(OnClickedListenerReserve onClickedListenerGallery) {
        this.onClickedListenerReserve = onClickedListenerGallery;
    }

    public interface OnClickedListenerReserve {
        public void onClickedListenerReserve(int position, ItemPost itemPost);

    }





}
