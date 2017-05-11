package app.com.faros.restest.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.com.faros.restest.R;

/**
 * Created by lenna on 2017-05-10.
 * screen slide fragment, currently not used
 */
public class ScreenSlidePageFragment extends Fragment {
    @Override



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.menus, container, false);

        return rootView;
    }
}
