package z_aksys.solutions.pitchapplication.Adapter;/*
package z_aksys.solutions.pitchapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

import z_aksys.solutions.pitchapplication.R;
import z_aksys.solutions.pitchapplication.model.Model;
import z_aksys.solutions.pitchapplication.model.News;
import z_aksys.solutions.pitchapplication.utils.onClickVideo;

import static android.content.ContentValues.TAG;
import static z_aksys.solutions.pitchapplication.utils.Constants.VideoKEY;

public class MultiViewTypeAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private ArrayList<News> dataSet;
    Context mContext;
    int total_types;
    MediaPlayer mPlayer;
    private boolean fabStateVolume = false;
    private onClickVideo onClickVideo;


    private String[] VideoID = {"P3mAtvs5Elc", "nCgQDjiotG0", "P3mAtvs5Elc"};




    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {


        public TextView title, count;
        public ImageView thumbnail, overflow;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title);
            this.count = (TextView) itemView.findViewById(R.id.count);
            this. thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.overflow = (ImageView) itemView.findViewById(R.id.overflow);

        }

    }

    public static class YouTubeTypeViewHolder extends RecyclerView.ViewHolder  {

        public YouTubeThumbnailView videoThumbnailImageView;
        public TextView videoTitle, videoDuration;
        public ImageView videoPlay;


        public YouTubeTypeViewHolder(View itemView) {
            super(itemView);
            this. videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
            this.  videoTitle = itemView.findViewById(R.id.title);
            this. videoPlay = itemView.findViewById(R.id.video_duration_label);
            this. videoDuration = itemView.findViewById(R.id.video_duration_label1);

        }



    }

    public MultiViewTypeAdapter1(ArrayList<News> data, Context context, Fragment fragment) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
       this.onClickVideo=((onClickVideo) fragment);


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {

            case Model.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
                return new ImageTypeViewHolder(view);

            case Model.YOUTUBE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtubelayout, parent, false);
                return new YouTubeTypeViewHolder(view);
        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Model.IMAGE_TYPE;
            case 1:
                return Model.YOUTUBE_TYPE;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final News object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {

                case Model.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).title.setText(object.getTitle());
                   // ((ImageTypeViewHolder) holder).count.setText(object.getThumbnail());

                    ((ImageTypeViewHolder) holder).overflow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showPopupMenu( ((ImageTypeViewHolder) holder).overflow);
                        }
                    });
                    break;

                case Model.YOUTUBE_TYPE:

                    if(object.getVideoId()!=null)
                    {

                    ((YouTubeTypeViewHolder) holder).videoTitle.setText(object.getTitle());
                    ((YouTubeTypeViewHolder) holder).videoDuration.setText(object.getDuration());

                        ((YouTubeTypeViewHolder) holder).videoPlay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    onClickVideo.VideoId(object.getVideoId());
                                } catch (ClassCastException exception) {
                                    exception.printStackTrace();
                                }



                            }
                        });

                    ((YouTubeTypeViewHolder) holder).videoThumbnailImageView.initialize(VideoKEY, new YouTubeThumbnailView.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                            //when initialization is sucess, set the video id to thumbnail to load
                            youTubeThumbnailLoader.setVideo(object.getVideoId());

                            youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                                @Override
                                public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                                    //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                                    youTubeThumbnailLoader.release();
                                }

                                @Override
                                public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                                    //print or show error when thumbnail load failed
                                    Log.e(TAG, "Youtube Thumbnail Error");
                                }
                            });
                        }

                        @Override
                        public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                            //print or show error when initialization failed
                            Log.e(TAG, "Youtube Initialization Failure");

                        }
                    });



                    break;

            }
            }

        }
    }

    private void showPopupMenu(ImageView overflow) {

        PopupMenu popup = new PopupMenu(mContext, overflow);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.news_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MultiViewTypeAdapter1.MyMenuItemClickListener());
        popup.show();
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "http://www.z-aksys.com");
                    mContext.startActivity(Intent.createChooser(shareIntent, "Share link using"));
                    return true;

                default:
            }
            return false;
        }
        }
    }



*/
