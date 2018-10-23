package z_aksys.solutions.pitchapplication.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import z_aksys.solutions.pitchapplication.R;

public class FaqFragment extends Fragment {

    private RecyclerView recyclerView;


    public FaqFragment() {
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
        final View view = inflater.inflate(R.layout.faq_frgament, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_faq);

        getFaq();

        return view;
    }

    private void getFaq() {
    }

}
