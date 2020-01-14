package com.furkanzayimoglu.appcentdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.furkanzayimoglu.appcentdemo.R;
import com.furkanzayimoglu.appcentdemo.model.PhotoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailScreen extends AppCompatActivity {

    ImageView imageView;
    ArrayList<PhotoModel> arrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String largeUrl = getBigUrl(url);
        Picasso.get().load(largeUrl).into(imageView);  // bitmap factoryde kullanılabilir....
    }

    public String getBigUrl(String url){
        return url.substring(0,((url.length()-1)-5))+"_b.jpg";
    }
}
