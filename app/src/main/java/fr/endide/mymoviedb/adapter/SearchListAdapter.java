package fr.endide.mymoviedb.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import fr.endide.mymoviedb.Main;
import fr.endide.mymoviedb.R;
import fr.endide.mymoviedb.data.entity.SearchEntity;
import fr.endide.mymoviedb.data.entity.moviedbEntity;
import fr.endide.mymoviedb.data.externalApi.apiClient;

public class SearchListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;


    public SearchListAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return Main.externalContent.size();
    }

    @Override
    public Object getItem(int i) {
        return Main.externalContent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.add_list_layout, null);
        SearchEntity currentEntity = Main.externalContent.get(i);



        TextView name = view.findViewById(R.id.addListName);
        name.setText(currentEntity.name);

        TextView description = view.findViewById(R.id.addlistDescription);
        description.setText(currentEntity.description);


        return view;
    }
    public void refreshResultList(){
        notifyDataSetChanged();
    }
}
