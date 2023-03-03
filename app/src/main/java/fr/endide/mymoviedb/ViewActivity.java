package fr.endide.mymoviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import fr.endide.mymoviedb.data.entity.Content;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        int contentId = (int) b.get("content");
        System.out.println(contentId);
        Content content = Main.getContentById(contentId);
        getSupportActionBar().setTitle(content.name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView contentDescription = findViewById(R.id.viewDescription);
        contentDescription.setText(content.description);

        ImageView contentImage = findViewById(R.id.viewCover);
        File imageLocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyMovieDB/Covers" + File.separator + content.extId + ".jpg");
        if(imageLocation.exists()){
            contentImage.setImageBitmap(BitmapFactory.decodeFile(imageLocation.getAbsolutePath()));
        }else{
            contentImage.setImageResource(getResources().getIdentifier("noimgcover", "drawable", getPackageName()));
        }

        EditText contentReview = findViewById(R.id.viewReviewField);
        contentReview.setText(content.review);

        TextView contentStars = findViewById(R.id.viewStarsText);
        contentStars.setText("Note : " + content.stars);

        Button saveBtn = findViewById(R.id.viewSaveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.review = contentReview.getText().toString();
                Main.updateContent(content);
                startActivity(new Intent(getApplicationContext(), Main.class));
            }
        });

        Button delBtn = findViewById(R.id.viewDeleteBtn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.deleteContent(content);
                startActivity(new Intent(getApplicationContext(), Main.class));

            }
        });


    }
}