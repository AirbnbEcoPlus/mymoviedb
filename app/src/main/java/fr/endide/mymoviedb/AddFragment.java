package fr.endide.mymoviedb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import fr.endide.mymoviedb.adapter.SearchListAdapter;
import fr.endide.mymoviedb.data.entity.Content;
import fr.endide.mymoviedb.data.entity.ContentType;
import fr.endide.mymoviedb.data.entity.SearchEntity;
import fr.endide.mymoviedb.data.externalApi.apiClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    SearchEntity currentSearch;

    apiClient apiClient = new apiClient();

    SearchView searchBar;

    ListView searchList;

    LinearLayout watchingLayout;

    LinearLayout reviewLayout;

    Switch switchBtn;

    Spinner contentType;

    EditText epField;

    EditText saisonField;

    EditText reviewField;

    SeekBar starBar;

    TextView starText;

    Button addButton;

    private ContentType contentTypeResult;

    private String contentName;

    private boolean contentFinish;

    private int starStatus;

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

        epField = getView().findViewById(R.id.epNumber);

        saisonField = getView().findViewById(R.id.saisonNumber);

        reviewField = getView().findViewById(R.id.editTextTextMultiLine);

        starBar = getView().findViewById(R.id.starBar);

        starText = getView().findViewById(R.id.Note);

        addButton = getView().findViewById(R.id.addBtn);

        String[] contentItems = new String[]{"Film", "Série", "Anime"};
        ArrayAdapter<String> contentAdapter = new ArrayAdapter<>(getView().getContext(), android.R.layout.simple_spinner_dropdown_item, contentItems);
        contentType.setAdapter(contentAdapter);
        contentType.setOnItemSelectedListener(this);

        reviewLayout.setVisibility(View.GONE);
        watchingLayout.setVisibility(View.GONE);

        SearchListAdapter searchListAdapter = new SearchListAdapter(getView().getContext());
        searchList.setAdapter(searchListAdapter);
        searchList.setTextFilterEnabled(true);
        searchList.setVisibility(View.GONE);


        contentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setEnabledLogic(contentFinish, contentType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SearchEntity content = (SearchEntity) adapterView.getItemAtPosition(i);
                searchBar.setQuery(content.name, false);
                searchList.setVisibility(View.GONE);
                watchingLayout.setVisibility(View.VISIBLE);
                reviewLayout.setVisibility(View.VISIBLE);
                currentSearch = content;
            }
        });
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.matches("")){
                    searchList.setVisibility(View.GONE);
                    watchingLayout.setVisibility(View.VISIBLE);
                    reviewLayout.setVisibility(View.VISIBLE);
                }else{
                    searchList.setVisibility(View.VISIBLE);
                    watchingLayout.setVisibility(View.GONE);
                    reviewLayout.setVisibility(View.GONE);
                }
                apiClient.searchTv(s, searchListAdapter);
                apiClient.searchMovie(s, searchListAdapter);
                searchListAdapter.getFilter().filter(s);

                contentName = s;
                return false;
            }
        });
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setEnabledLogic(b, contentType);
            }
        });
        starBar.setMax(20);
        starBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                starText.setText("Note : " + i);
                starStatus = i;

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertContent();
            }
        });
    }
    public void insertContent(){
        Content content = new Content();
        content.contentType = contentTypeResult;
        content.name = contentName;
        content.finish = contentFinish;
        if(!epField.getText().toString().isEmpty() || !saisonField.getText().toString().isEmpty()){
            content.ep = Integer.parseInt(epField.getText().toString());
            content.season = Integer.parseInt(saisonField.getText().toString());
        }
        content.review = reviewField.getText().toString();
        content.stars = starStatus;
        content.extId = currentSearch.extId;
        content.description = currentSearch.description;
        content.coverPath = currentSearch.cover_path;
        new AlertDialog.Builder(getContext())
                .setTitle("Enregistrement..")
                .setMessage("Votre Contenu a été enregistré !")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        searchBar.setQuery("", false);
                        searchBar.clearFocus();
                        switchBtn.setChecked(false);
                        contentType.setSelection(0);
                        starBar.setProgress(0);
                        reviewField.setText("");
                        Main.insertContent(content);
                        apiClient.getPoster(content);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                contentTypeResult = ContentType.FILM;
                watchingLayout.setVisibility(View.GONE);
                break;
            case 1:
                contentTypeResult = ContentType.SERIES;
                watchingLayout.setVisibility(View.VISIBLE);
                break;
            case 2:
                contentTypeResult = ContentType.ANIME;
                watchingLayout.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
    public void setEnabledLogic(boolean radio, Spinner contentType){

        //!film pas fini
        if(contentType.getSelectedItemPosition() != 0 && !radio){
            reviewLayout.setVisibility(View.GONE);
            watchingLayout.setVisibility(View.VISIBLE);

            contentFinish = false;
        }
        //fini
        if(radio){
            reviewLayout.setVisibility(View.VISIBLE);
            watchingLayout.setVisibility(View.GONE);

            contentFinish = true;
        }
        //pas fini film
        if(contentType.getSelectedItemPosition() == 0 && !radio){
            reviewLayout.setVisibility(View.GONE);
            watchingLayout.setVisibility(View.GONE);

            contentFinish = false;
        }
    }
}