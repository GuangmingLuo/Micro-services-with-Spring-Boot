package app.com.faros.restest.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by lenna on 2017-05-10.
 * screen slider adapter, currently not used
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_PAGES = 1;
    public ScreenSlidePagerAdapter(FragmentManager fm,int pages) {
            super(fm);
            NUM_PAGES=pages;
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
}
