package z_aksys.solutions.pitchapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import z_aksys.solutions.pitchapplication.R;
import z_aksys.solutions.pitchapplication.model.Document;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.MyViewHolder> {


    private Context mContext;
    List<Document> documentList;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View view) {
            super(view);


        }
    }


    public FaqAdapter(Context mContext, List<Document> documentList) {
        this.mContext = mContext;
        this.documentList = documentList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptor_faq, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return documentList.size();
    }
}