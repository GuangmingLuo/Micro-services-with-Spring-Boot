package com.faros.ocr;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.faros.ocr.util.NetworkUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.faros.ocr.R.id.restaurants;
import static com.faros.ocr.R.layout.restaurant;


/**
 * Created by guang on 2017/5/5.
 */

public class MainActivity extends Activity {

    List<String> restaurantNames = new ArrayList<>();
    List<String> restaurantIds = new ArrayList<>();
    boolean finished = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        loadData();
        //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.restaurants);
//        for(String name:restaurantNames){
//            LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//            TextView tv=new TextView(this);
//            tv.setLayoutParams(lparams);
//            tv.setText(name);
//            int id = 1;
//            tv.setId(id);
//            linearLayout.addView(tv);
//        }
        while(!finished){
            int i = 0;
            i++;i--;
        }
        //Text redirect.
        final TextView[] restaurants = new TextView[3];
        restaurants[0] = (TextView)findViewById(R.id.restaurant1);
        restaurants[0].setText(restaurantNames.get(0));
        restaurants[1] = (TextView)findViewById(R.id.restaurant2);
        restaurants[1].setText(restaurantNames.get(1));
        restaurants[2] = (TextView)findViewById(R.id.restaurant3);
        restaurants[2].setText(restaurantNames.get(2));
        for(int i=0;i<restaurantNames.size();i++){
            //final TextView restaurant=(TextView)findViewById(i);
            final int finalI = i;
            restaurants[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v1) {
                    Intent launchActivity1= new Intent(MainActivity.this,MenuActivity.class);
                    //Create a bundle object
                    Bundle b = new Bundle();
                    b.putString("id", restaurantIds.get(finalI));
                    //Add the bundle to the intent.
                    launchActivity1.putExtras(b);
                    startActivity(launchActivity1);
                }
            });
        }

    }

    private void loadData(){
        new FetchRestsTask().execute("myTask");
    }
    private void prepareRestsData(String jstring)throws JSONException{
        JSONArray arr = new JSONArray(jstring);
        for (int i = 0; i < arr.length(); i++) { // Walk through the Array.
            JSONObject obj = arr.getJSONObject(i);
            restaurantNames.add(obj.getString("name"));
            restaurantIds.add(obj.getString("id"));
        }
        finished = true;
    }

    public class FetchRestsTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {

            URL restsSearchUrl = NetworkUtils.buildUrlForRests();

            try {
                String restSearchResults = NetworkUtils.getResponseFromHttpUrl(restsSearchUrl);
                Log.d("SearchResults",restSearchResults);
                prepareRestsData(restSearchResults);
                String[] result = new String[1];
                result[0] = restSearchResults;
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] resultData) {
            Log.d("onPost","come here");
            if (resultData != null) {
                try {
                    prepareRestsData(resultData[0]);
                    Log.d("SearchResults onPost",resultData[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
