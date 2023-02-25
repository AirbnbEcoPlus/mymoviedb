package fr.endide.mymoviedb;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import fr.endide.mymoviedb.data.entity.Content;
import fr.endide.mymoviedb.data.entity.ContentType;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    SearchView searchBar;

    ListView searchList;

    LinearLayout watchingLayout;

    LinearLayout reviewLayout;

    Switch switchBtn;

    Spinner contentType;

    ArrayAdapter<String> externalContentAdapter;

    private ContentType contentTypeResult;

    private String contentName;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        return inflater.inflate(R.layout.fragment_add, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        searchBar = getView().findViewById(R.id.searchBar);

        searchList = getView().findViewById(R.id.searchList);

        reviewLayout = getView().findViewById(R.id.reviewLayout);

        watchingLayout = getView().findViewById(R.id.watchingLayout);

        switchBtn = getView().findViewById(R.id.endContent);

        contentType = getView().findViewById(R.id.contentType);

        String[] contentItems = new String[]{"Film", "Série", "Anime"};
        ArrayAdapter<String> contentAdapter = new ArrayAdapter<>(getView().getContext(), android.R.layout.simple_spinner_dropdown_item, contentItems);
        contentType.setAdapter(contentAdapter);
        contentType.setOnItemSelectedListener(this);

        reviewLayout.setVisibility(View.GONE);
        watchingLayout.setVisibility(View.VISIBLE);

        externalContentAdapter = new ArrayAdapter<>(getView().getContext(), android.R.layout.simple_list_item_1, Main.externalContent);
        searchList.setAdapter(externalContentAdapter);
        searchList.setVisibility(View.GONE);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.matches("")){
                    searchList.setVisibility(View.GONE);
                }else{
                    searchList.setVisibility(View.VISIBLE);
                }
                contentName = s;
                externalContentAdapter.getFilter().filter(s);
                return false;
            }
        });

        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    reviewLayout.setVisibility(View.VISIBLE);
                    watchingLayout.setVisibility(View.GONE);
                }else{
                    reviewLayout.setVisibility(View.GONE);
                    watchingLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void onClickAdd(View v){
        Content content = new Content();
        content.contentType = contentTypeResult;
        content.name = contentName;


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                contentTypeResult = ContentType.FILM;
                break;
            case 1:
                contentTypeResult = ContentType.SERIES;
                break;
            case 2:
                contentTypeResult = ContentType.ANIME;
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}