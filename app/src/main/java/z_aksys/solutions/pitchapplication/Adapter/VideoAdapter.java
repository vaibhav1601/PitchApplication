package z_aksys.solutions.pitchapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

import z_aksys.solutions.pitchapplication.R;
import z_aksys.solutions.pitchapplication.model.Document;
import z_aksys.solutions.pitchapplication.model.News;
import z_aksys.solutions.pitchapplication.model.YoutubeVideoModel;
import z_aksys.solutions.pitchapplication.utils.onClickVideo;

import static android.content.ContentValues.TAG;
import static z_aksys.solutions.pitchapplication.utils.Constants.VideoKEY;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {


    private Context mContext;
    List<YoutubeVideoModel> videoModelArrayList;
    private onClickVideo onClickVideo;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public YouTubeThumbnailView videoThumbnailImageView;
        public TextView videoTitle, videoDuration;
        public ImageView videoPlay,shareVideo;


        public MyViewHolder(View view) {
            super(view);

            this.videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
            this.videoTitle = itemView.findViewById(R.id.title);
            this.videoPlay = itemView.findViewById(R.id.video_duration_label);
            this.shareVideo = itemView.findViewById(R.id.shareVideo);

        }
    }


    public VideoAdapter(Context mContext, List<YoutubeVideoModel> videoModelArrayList, Fragment fragment) {
        this.mContext = mContext;
        this.videoModelArrayList = videoModelArrayList;
        this.onClickVideo=((onClickVideo) fragment);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptor_video, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final YoutubeVideoModel object = videoModelArrayList.get(position);

        holder.videoTitle.setText(object.getTitle());
//        holder.videoDuration.setText(object.getDuration());


        holder.videoThumbnailImageView.initialize(VideoKEY, new YouTubeThumbnailView.OnInitializedListener() {
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


        holder.videoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickVideo.VideoId(object.getVideoId());
            }
        });


        holder.shareVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v="+object.getVideoId());
                mContext.startActivity(Intent.createChooser(shareIntent, "Youtube video"));
            }
        });


    }


    @Override
    public int getItemCount() {
        return videoModelArrayList.size();
    }
}
