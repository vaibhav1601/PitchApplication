package z_aksys.solutions.pitchapplication.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import z_aksys.solutions.pitchapplication.Adapter.MultiViewTypeAdapter;
import z_aksys.solutions.pitchapplication.Adapter.NewsAdapter;
import z_aksys.solutions.pitchapplication.R;
import z_aksys.solutions.pitchapplication.model.Model;
import z_aksys.solutions.pitchapplication.model.News;
import z_aksys.solutions.pitchapplication.model.YoutubeVideoModel;

public class TwoFragment extends Fragment {


    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> albumList;
    private List<YoutubeVideoModel> youtubeVideoModelList;

    public Model youtubeVideoModel;

    public ArrayList<Model> youtubeVideoModelArrayList;


    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.pitchfragment, container, false);

        return view;


    }


}