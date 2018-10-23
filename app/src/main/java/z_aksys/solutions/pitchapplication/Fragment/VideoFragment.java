package z_aksys.solutions.pitchapplication.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import z_aksys.solutions.pitchapplication.Activity.YoutubePlayerActivity;
import z_aksys.solutions.pitchapplication.Adapter.DocumentAdapter;
import z_aksys.solutions.pitchapplication.Adapter.MultiViewTypeAdapter;
import z_aksys.solutions.pitchapplication.Adapter.VideoAdapter;
import z_aksys.solutions.pitchapplication.R;
import z_aksys.solutions.pitchapplication.model.Document;
import z_aksys.solutions.pitchapplication.model.Model;
import z_aksys.solutions.pitchapplication.model.News;
import z_aksys.solutions.pitchapplication.model.YoutubeVideoModel;
import z_aksys.solutions.pitchapplication.utils.onClickVideo;

public class VideoFragment extends Fragment implements onClickVideo {

    private RecyclerView recycler_view_video;
    private VideoAdapter videoAdapter;
    private ArrayList<YoutubeVideoModel> videoModelArrayList;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_video, container, false);

        recycler_view_video = view.findViewById(R.id.recycler_view_Video);
        videoModelArrayList= new ArrayList<>();
        YoutubeVideoModel youtubeVideoModel= new YoutubeVideoModel();

        youtubeVideoModel.setVideoId("nCgQDjiotG0");
        youtubeVideoModel.setTitle("android");
        youtubeVideoModel.setDuration("2.56");

        videoModelArrayList.add(youtubeVideoModel);


        YoutubeVideoModel youtubeVideoModel1= new YoutubeVideoModel();

        youtubeVideoModel1.setVideoId("P3mAtvs5Elc");
        youtubeVideoModel1.setTitle("android");
        youtubeVideoModel1.setDuration("2.56");

        videoModelArrayList.add(youtubeVideoModel1);

        YoutubeVideoModel youtubeVideoModel2= new YoutubeVideoModel();

        youtubeVideoModel2.setVideoId("C3nA5_8EaOs");
        youtubeVideoModel2.setTitle("android");
        youtubeVideoModel2.setDuration("2.56");

        videoModelArrayList.add(youtubeVideoModel2);


        YoutubeVideoModel youtubeVideoModel3= new YoutubeVideoModel();
        youtubeVideoModel3.setVideoId("BtlXZpkkW3o");
        youtubeVideoModel3.setTitle("android");
        youtubeVideoModel3.setDuration("2.56");
        videoModelArrayList.add(youtubeVideoModel3);





        videoAdapter = new VideoAdapter(getContext(), videoModelArrayList, this);
        videoAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recycler_view_video.setLayoutManager(mLayoutManager);
        recycler_view_video.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(60), true));
        recycler_view_video.setItemAnimator(new DefaultItemAnimator());
        recycler_view_video.setAdapter(videoAdapter);



        return view;
    }

    @Override
    public void VideoId(String Id) {


        //start youtube player activity by passing selected video id via intent
        startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                .putExtra("video_id", Id));

    }

    @Override
    public void newsUrl(String url) {

    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
