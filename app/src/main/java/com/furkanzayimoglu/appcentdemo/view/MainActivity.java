package com.furkanzayimoglu.appcentdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.furkanzayimoglu.appcentdemo.R;
import com.furkanzayimoglu.appcentdemo.adapter.PhotoAdapter;
import com.furkanzayimoglu.appcentdemo.api.ApiEndPoint;
import com.furkanzayimoglu.appcentdemo.api.ApiService;
import com.furkanzayimoglu.appcentdemo.api.PhotoApi;
import com.furkanzayimoglu.appcentdemo.model.ImageResponseModel;
import com.furkanzayimoglu.appcentdemo.model.PhotoModel;
import com.furkanzayimoglu.appcentdemo.utils.OnItemClickListener;
import com.furkanzayimoglu.appcentdemo.utils.PaginationScrollListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

   public  RecyclerView recyclerView;
   public static ArrayList<PhotoModel> urlList = new ArrayList<>();
    public PhotoAdapter adapter;
   private static final int PAGE_START = 1;
   private boolean isLastPage = false;
   private boolean isLoading = false;
   private int TOTAL_PAGES = 5;
   private int currentPage = PAGE_START;
   GridLayoutManager layoutManager;
   SwipeRefreshLayout swipeRefreshLayout;
   ProgressBar progressBar;
   ArrayList<PhotoModel> photoList = new ArrayList<>();
    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        progressBar =  findViewById(R.id.main_progress);
        adapter = new PhotoAdapter(this);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        apiService = PhotoApi.getClient().create(ApiService.class);

        getPhotos();
        scrool();

    }

    public void scrool(){
        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {

            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                },5000); // her istekte 20 fotograf geldiğini gosterebilmek adına bilerek gecikmeyi fazladan verdim .
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    public void getPhotos() {

        Call<ImageResponseModel> call = apiService.getRecent(ApiEndPoint.getMethod(), ApiEndPoint.getApiKey(), ApiEndPoint.getExtraSmallUrl(),
                ApiEndPoint.getPERPAGE(), ApiEndPoint.getPAGE(), ApiEndPoint.getFORMAT(), ApiEndPoint.getNoJsonCallback());

        call.enqueue(new Callback<ImageResponseModel>() {
            @Override
            public void onResponse(Call<ImageResponseModel> call, Response<ImageResponseModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        ImageResponseModel responseModel = response.body();
                        photoList = responseModel.getPhotos().getPhotoModels();
                        progressBar.setVisibility(View.GONE);
                        adapter.addAll(photoList);

                        if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                        else isLastPage = true;
                    }
                }
            }
            @Override
            public void onFailure(Call<ImageResponseModel> call, Throwable t) {

            }
        });
    }

    private void loadNextPage(){

        Call<ImageResponseModel> call = apiService.getRecent(ApiEndPoint.getMethod(), ApiEndPoint.getApiKey(), ApiEndPoint.getExtraSmallUrl(),
                ApiEndPoint.getPERPAGE(), ApiEndPoint.getPAGE(), ApiEndPoint.getFORMAT(), ApiEndPoint.getNoJsonCallback());

        call.enqueue(new Callback<ImageResponseModel>() {
            @Override
            public void onResponse(Call<ImageResponseModel> call, Response<ImageResponseModel> response) {

                adapter.removeLoadingFooter();
                isLoading = false;
                swipeRefreshLayout.setRefreshing(false);


                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ImageResponseModel responseModel = response.body();
                        photoList = responseModel.getPhotos().getPhotoModels();
                        adapter.addAll(photoList);

                        if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                        else isLastPage = true;
                    }
                }
            }
            @Override
            public void onFailure(Call<ImageResponseModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRefresh() {
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        getPhotos();
    }

    @Override
    public void onItemClick(String imageUrl) {
        Intent intent = new Intent(this,DetailScreen.class);
        intent.putExtra("url", imageUrl);
        startActivity(intent);
    }
}

