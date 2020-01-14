package com.furkanzayimoglu.appcentdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.furkanzayimoglu.appcentdemo.R;
import com.furkanzayimoglu.appcentdemo.model.PhotoModel;
import com.furkanzayimoglu.appcentdemo.utils.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    private OnItemClickListener listener;
    private ArrayList<PhotoModel> photoModelsList;

    public PhotoAdapter(Context context) {
        photoModelsList = new ArrayList<>();
    }

    public ArrayList<PhotoModel> getPhotoModelsList() {
        return photoModelsList;
    }

    public void setPhotoModelsList(ArrayList<PhotoModel> photoModelsList) {
        this.photoModelsList = photoModelsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_loading, parent, false);
                viewHolder = new ProgressHolder(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_design, parent, false);
        viewHolder = new MyViewHolder(v1);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case ITEM:
                MyViewHolder viewHolder= (MyViewHolder)holder;
                Picasso.get().load(photoModelsList.get(position).getUrl_s()).into(viewHolder.imageView);
            case LOADING:
                break;
        }

    }
    @Override
    public int getItemCount() {
        return photoModelsList == null ? 0 : photoModelsList.size();
    }

    public void setClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


    @Override
    public int getItemViewType(int position) {
        return (position == photoModelsList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;

    }

    public void add(PhotoModel photoModel) {
        photoModelsList.add(photoModel);
        notifyItemInserted(photoModelsList.size() - 1);
    }

    public void addAll(ArrayList<PhotoModel> photoModelArrayList){
        for (PhotoModel model : photoModelArrayList){
            add(model);
        }
    }

    public void remove(PhotoModel model) {
        int position = photoModelsList.indexOf(model);
        if (position > -1) {
            photoModelsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new PhotoModel());
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = photoModelsList.size() - 1;
        PhotoModel model = getItem(position);

        if (model != null) {
            photoModelsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public PhotoModel getItem(int position) {
        return photoModelsList.get(position);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagePhoto);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(photoModelsList.get(getPosition()).getUrl_s());

        }

    }

    public class ProgressHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        ProgressHolder(View itemView){
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress);
        }
    }
}
