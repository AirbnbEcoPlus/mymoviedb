package fr.endide.mymoviedb;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;
import java.util.stream.Collectors;

import fr.endide.mymoviedb.adapter.WatchingViewAdapter;
import fr.endide.mymoviedb.data.entity.Content;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WatchingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WatchingFragment extends Fragment {

    ListView personalContentWatching;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WatchingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WatchningFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WatchingFragment newInstance(String param1, String param2) {
        WatchingFragment fragment = new WatchingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watching, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        personalContentWatching = getView().findViewById(R.id.watchingListView);
        List<Content> personalContentNotViewed = Main.getPersonalContent().stream().filter(content -> !content.finish).collect(Collectors.toList());
        personalContentWatching.setAdapter(new WatchingViewAdapter(this.getContext(), personalContentNotViewed));
    }
}