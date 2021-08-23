package com.deliverykreani.site.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deliverykreani.R;
import com.deliverykreani.site.SiteDetailActivity;
import com.deliverykreani.site.fragment.entity.SiteEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by Pushpendra on 16-09-2019.
 */
public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.HistoryViewHolder> {

    private Context context;
    private List<SiteEntity> data;


    public FavouritesAdapter(Context context, List<SiteEntity> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_site_manager, parent, false);
        HistoryViewHolder holder = new HistoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        SiteEntity current = data.get(position);
        holder.textViewSiteName.setText(current.getName());
        holder.textViewDisplayType.setText(current.getSiteDisplayType());
        String imageUrl = current.getPhotosUrl();
        if (!imageUrl.equals("")) {
            Picasso.get().load(imageUrl).into(holder.imageViewSiteImage);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout linearLayoutSiteListing;
        private TextView textViewSiteName, textViewDisplayType;
        private ImageView imageViewSiteImage;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            linearLayoutSiteListing = itemView.findViewById(R.id.site_listing);
            textViewSiteName = itemView.findViewById(R.id.site_name);
            textViewDisplayType = itemView.findViewById(R.id.display_type);
            imageViewSiteImage = itemView.findViewById(R.id.site_image);
            linearLayoutSiteListing.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.site_listing:
                    SiteEntity siteEntity = data.get(getPosition());
                    intent = new Intent(context, SiteDetailActivity.class);
                    intent.putExtra("site_name", siteEntity.getName());
                    intent.putExtra("site_display_type", siteEntity.getSiteDisplayType());
                    intent.putExtra("site_image", siteEntity.getPhotosUrl());
                    intent.putExtra("site_id", siteEntity.getSiteId());
                    intent.putExtra("description", siteEntity.getDescription());
                    intent.putExtra("siteLocationType", siteEntity.getSiteLocationType());
                    intent.putExtra("siteIlluminationType", siteEntity.getSiteIlluminationType());
                    intent.putExtra("siteMediaType", siteEntity.getSiteMediaType());
                    intent.putExtra("siteType", siteEntity.getSiteType());
                    intent.putExtra("basePrice", siteEntity.getBasePrice());
                    intent.putExtra("status", siteEntity.getStatusType());
                    context.startActivity(intent);
                    break;
            }
        }
    }
}