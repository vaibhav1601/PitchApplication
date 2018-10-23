package z_aksys.solutions.pitchapplication.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import z_aksys.solutions.pitchapplication.Fragment.NewsFragment;
import z_aksys.solutions.pitchapplication.Fragment.ShareFragment;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                NewsFragment tab1 = new NewsFragment();
                return tab1;
            case 1:
                ShareFragment tab2 = new ShareFragment();
                return tab2;
            case 2:
                ShareFragment tab3 = new ShareFragment();
                return tab3;
            case 3:
                ShareFragment tab4 = new ShareFragment();
                return tab4;

            case 4:
                ShareFragment tab5 = new ShareFragment();
                return tab5;

            case 5:
                ShareFragment tab6 = new ShareFragment();
                return tab6;

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
