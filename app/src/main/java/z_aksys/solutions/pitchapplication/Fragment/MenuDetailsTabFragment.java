package z_aksys.solutions.pitchapplication.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import z_aksys.solutions.pitchapplication.R;


/**
 * Created by root on 10/10/17.
 */

public class MenuDetailsTabFragment extends Fragment {

    ArrayList<String> indiListTemp= new ArrayList<>();
    private String pos;


    public MenuDetailsTabFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public MenuDetailsTabFragment(String pos) {

        this.pos=pos;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.custom_tab, container, false);
        initialiseResources();
        setData();
        return rootView;
    }

    private void setData() {



    }

    private void initialiseResources() {
    }


}
