package fr.endide.mymoviedb.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.util.List;

import fr.endide.mymoviedb.Main;
import fr.endide.mymoviedb.R;
import fr.endide.mymoviedb.data.entity.Content;
import fr.endide.mymoviedb.data.entity.ContentType;

public class WatchingViewAdapter extends BaseAdapter {


    private List<Content> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public WatchingViewAdapter(Context context, List<Content> contents) {
        this.context = context;
        this.listData = contents;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.watching_layout, null);

        Content currentContent = (Content) getItem(i);

        TextView contentTitle = view.findViewById(R.id.contentTitle);
        contentTitle.setText(currentContent.name);
        contentTitle.setTypeface(null, Typeface.BOLD);

        TextView contentDesc = view.findViewById(R.id.contentDescription);
        contentDesc.setText(currentContent.description);

        TextView contentSeason = view.findViewById(R.id.saisonText);
        contentSeason.setText("Saison " + currentContent.season);

        TextView contentEp = view.findViewById(R.id.epText);
        contentEp.setText("Episode " + currentContent.ep);

        Button incrementSeason = view.findViewById(R.id.incrementSaison);
        incrementSeason.setOnClickListener(view1 -> {
            currentContent.ep = 0;
            currentContent.season += 1;
            Main.updateContent(currentContent);
            notifyDataSetChanged();
        });

        Button incrementEp = view.findViewById(R.id.incrementEpisode);
        incrementEp.setOnClickListener(view1 -> {
            currentContent.ep += 1;
            Main.updateContent(currentContent);
            notifyDataSetChanged();
        });

        if(currentContent.contentType == ContentType.FILM){
            contentSeason.setVisibility(View.GONE);
            contentEp.setVisibility(View.GONE);
            incrementEp.setVisibility(View.GONE);
            incrementSeason.setVisibility(View.GONE);
        }

        Button finishBtn = view.findViewById(R.id.finishButton);
        finishBtn.setOnClickListener(view1 -> {
            currentContent.ep = 0;
            currentContent.season = 0;
            currentContent.finish = true;
            Main.updateContent(currentContent);
            notifyDataSetChanged();
        });


        //Image
        ImageView contentImage = view.findViewById(R.id.watchingImage);
        File imageLocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyMovieDB/Covers" + File.separator + currentContent.extId + ".jpg");
        if(imageLocation.exists()){
            contentImage.setImageBitmap(BitmapFactory.decodeFile(imageLocation.getAbsolutePath()));
        }else{
            contentImage.setImageResource(context.getResources().getIdentifier("noimgcover", "drawable", context.getPackageName()));
        }

        //View Button

        return view;
    }

}
