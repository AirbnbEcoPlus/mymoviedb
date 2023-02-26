package fr.endide.mymoviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.endide.mymoviedb.Main;
import fr.endide.mymoviedb.R;
import fr.endide.mymoviedb.data.entity.Content;

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

        TextView contentDesc = view.findViewById(R.id.contentDescription);
        contentDesc.setText(currentContent.description);

        TextView contentSeason = view.findViewById(R.id.textSaison);
        contentSeason.setText("Saison " + currentContent.season);

        TextView contentEp = view.findViewById(R.id.textEp);
        contentEp.setText("Episode " + currentContent.ep);

        Button incrementSeason = view.findViewById(R.id.incrementSaison);
        incrementSeason.setOnClickListener(view1 -> {
            currentContent.ep = 0;
            currentContent.season += 1;
            Main.insertContent(currentContent);
        });

        //Image
        ImageView contentImage = view.findViewById(R.id.contentImage);
        contentImage.setImageResource(context.getResources().getIdentifier("noimgcover", "drawable", context.getPackageName()));

        //View Button

        return view;
    }
}
