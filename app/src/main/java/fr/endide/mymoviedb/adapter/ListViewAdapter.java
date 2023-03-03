package fr.endide.mymoviedb.adapter;

import android.content.Context;
import android.content.Intent;
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
import fr.endide.mymoviedb.ViewActivity;
import fr.endide.mymoviedb.data.entity.Content;

public class ListViewAdapter extends BaseAdapter {

    private List<Content> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    private FragmentManager fragmentManager;

    public ListViewAdapter(Context context, List<Content> contents, FragmentManager fragmentManager){
        this.context = context;
        this.listData = contents;
        this.layoutInflater = LayoutInflater.from(context);
        this.fragmentManager = fragmentManager;

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


        view = layoutInflater.inflate(R.layout.list_layout, null);

        Content currentContent = (Content) getItem(i);

        TextView contentTitle = view.findViewById(R.id.contentTitle);
        contentTitle.setText(currentContent.name);
        contentTitle.setTypeface(null, Typeface.BOLD);

        TextView contentDesc = view.findViewById(R.id.contentDescription);
        contentDesc.setText(currentContent.description);

        TextView contentStar = view.findViewById(R.id.contentStars);
        contentStar.setText(currentContent.stars+"/20");

        //Image
        ImageView contentImage = view.findViewById(R.id.contentImage);
        File imageLocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyMovieDB/Covers" + File.separator + currentContent.extId + ".jpg");
        if(imageLocation.exists()){
            contentImage.setImageBitmap(BitmapFactory.decodeFile(imageLocation.getAbsolutePath()));
        }else{
            contentImage.setImageResource(context.getResources().getIdentifier("noimgcover", "drawable", context.getPackageName()));
        }


        //View Button
        Button viewButton = view.findViewById(R.id.contentViewBtn);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewActivity = new Intent(context, ViewActivity.class);
                viewActivity.putExtra("content", currentContent.uid);
                context.startActivity(viewActivity);
            }
        });
        return view;
    }
    private void replaceFragment(Fragment fragment, FragmentManager fragmentManager){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
