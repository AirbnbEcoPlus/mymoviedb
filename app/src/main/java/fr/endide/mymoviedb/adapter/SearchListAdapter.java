package fr.endide.mymoviedb.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import fr.endide.mymoviedb.Main;
import fr.endide.mymoviedb.R;
import fr.endide.mymoviedb.data.entity.SearchEntity;
import fr.endide.mymoviedb.data.entity.moviedbEntity;
import fr.endide.mymoviedb.data.externalApi.apiClient;

public class SearchListAdapter extends BaseAdapter implements Filterable {

    private LayoutInflater layoutInflater;
    private Context context;

    private List<SearchEntity> mDataFiltered;


    public SearchListAdapter(Context context){
        this.context = context;
        mDataFiltered = Main.externalContent;
        this.layoutInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return mDataFiltered.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataFiltered.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.add_list_layout, null);
        SearchEntity currentEntity = mDataFiltered.get(i);



        TextView name = view.findViewById(R.id.addListName);
        name.setText(currentEntity.name);

        TextView description = view.findViewById(R.id.addlistDescription);
        description.setText(currentEntity.description);


        return view;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    //no constraint given, just return all the data. (no search)
                    results.count = Main.externalContent.size();
                    results.values = Main.externalContent;
                } else {//do the search
                    List<SearchEntity> resultsData = new ArrayList<>();
                    String searchStr = constraint.toString().toUpperCase();
                    for (SearchEntity o : Main.externalContent)
                        if (o.name.toUpperCase().startsWith(searchStr)) resultsData.add(o);
                    results.count = resultsData.size();
                    results.values = resultsData;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFiltered = (ArrayList<SearchEntity>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
