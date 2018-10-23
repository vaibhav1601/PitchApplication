package z_aksys.solutions.pitchapplication.Fragment;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import z_aksys.solutions.pitchapplication.API.RestAPI;
import z_aksys.solutions.pitchapplication.Activity.MainActivity;
import z_aksys.solutions.pitchapplication.Activity.NewsViewer;
import z_aksys.solutions.pitchapplication.Activity.YoutubePlayerActivity;
import z_aksys.solutions.pitchapplication.Adapter.MultiViewTypeAdapter;
import z_aksys.solutions.pitchapplication.Adapter.NewsAdapter;
import z_aksys.solutions.pitchapplication.R;
import z_aksys.solutions.pitchapplication.Request.NewsRequest;
import z_aksys.solutions.pitchapplication.Response.NewsResponse;
import z_aksys.solutions.pitchapplication.model.Model;
import z_aksys.solutions.pitchapplication.model.News;
import z_aksys.solutions.pitchapplication.utils.Constants;
import z_aksys.solutions.pitchapplication.utils.onClickVideo;

import static z_aksys.solutions.pitchapplication.utils.Constants.baseUrl;

public class NewsFragment extends Fragment implements onClickVideo {

    private RecyclerView recyclerView;
    private TextView txt_date;
    private MultiViewTypeAdapter adapter;
    private List<News> albumList;
    private ArrayList<NewsResponse.News> newsResponseList;
    private String VideoId,newsUrl;
    private ProgressDialog progressDialog;


    public NewsFragment() {
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
        final View view = inflater.inflate(R.layout.newxfragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        txt_date = (TextView) view.findViewById(R.id.txtDate);
        Calendar c = new GregorianCalendar();

        Date dt = new Date(c.getTimeInMillis());
        DateFormat dtformat;
        dtformat = DateFormat.getDateInstance(DateFormat.FULL);

        getNews();

       /* txt_date.setText("" + dtformat.format(dt));


        albumList = new ArrayList<>();
        News album = new News();

        album.setTitle("vaibhav");
        album.setSource("AngelBroking.com");
        album.setThumbnail(2);
        album.setType(Constants.IMAGE_TYPE);
        album.setVideoId(" ");
        album.setDuration("00");
        album.setText("ewoiru");

        albumList.add(album);


        News album2 = new News();
        album2.setVideoId("CqhpNxI8qYw");
        album2.setTitle("android");
        album2.setDuration("3.34");
        album2.setType(Model.YOUTUBE_TYPE);
        album2.setText("Hiiiiiii");
        album2.setData(1);
        albumList.add(album2);


        News album3 = new News();
        album3.setVideoId("P3mAtvs5Elc");
        album3.setTitle("android");
        album3.setDuration("2.56");
        album3.setType(Model.YOUTUBE_TYPE);
        album3.setText("Hiiiiiii");
        album3.setData(1);
        albumList.add(album3);

        News album4 = new News();
        album4.setVideoId("nCgQDjiotG0");
        album4.setTitle("android");
        album4.setDuration("2.56");
        album4.setType(Model.YOUTUBE_TYPE);
        album4.setText("Hiiiiiii");
        album4.setData(1);
        albumList.add(album4);


        News album5 = new News();
        album5.setVideoId("P3mAtvs5Elc");
        album5.setTitle("android");
        album5.setDuration("2.56");
        album5.setType(Model.YOUTUBE_TYPE);
        album5.setText("Hiiiiiii");
        album5.setData(1);
        albumList.add(album5);
*/

 /*       adapter = new MultiViewTypeAdapter((ArrayList<News>) albumList, getContext(), this);
        adapter.notifyDataSetChanged();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new NewsFragment.GridSpacingItemDecoration(3, dpToPx(60), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);*/


        return view;
    }


    private void getNews() {

        showProgress();

        NewsRequest newsRequest = new NewsRequest();
        newsRequest.setEmp_no("test");
        newsRequest.setStart("1");
        newsRequest.setLimit("10");

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);  // <-- this is the important line!


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();


        RestAPI restAPI = retrofit.create(RestAPI.class);

        Call<NewsResponse> myResponseCall = restAPI.getNews("application/json","80w4k8ogow8k4g4ks80sg08o4kcsc04scg48kks4", "42d34bd99093094714e0257e391a810c", "test","1","10");


        myResponseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {



                System.out.println("RequestParameter"+response);

               // Toast.makeText(getContext(), "sucess" + response.toString(), Toast.LENGTH_LONG).show();

                newsResponseList= new ArrayList<>();
                newsResponseList=response.body().getNews();
                callAdapoter();
                hideProgress();


            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();

                System.out.println("RequestParameter"+t.getMessage());
                hideProgress();
            }
        });


    }

    private void callAdapoter() {

        adapter = new MultiViewTypeAdapter(newsResponseList, getContext(), this);
        adapter.notifyDataSetChanged();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(60), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void VideoId(String Id) {

        VideoId = Id;


        //start youtube player activity by passing selected video id via intent
        startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                .putExtra("video_id", VideoId));

    }

    @Override
    public void newsUrl(String url) {
        newsUrl=url;

        startActivity(new Intent(getActivity(), NewsViewer.class)
                .putExtra("newsUrl", newsUrl));

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

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public void showProgress() {
        if (isAdded() && (progressDialog == null || (progressDialog != null && progressDialog.isShowing() == false))) {
            progressDialog = ProgressDialog.show(getActivity(), "", "Please wait.", true);
        }
    }

    public void hideProgress() {
        if (isAdded() && progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        } else {
            return;
        }
    }
}