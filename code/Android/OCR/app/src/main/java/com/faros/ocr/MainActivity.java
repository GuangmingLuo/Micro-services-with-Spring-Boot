package com.faros.ocr;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.faros.ocr.model.Food;
import com.faros.ocr.util.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader = new ArrayList<>();
    HashMap<String, List<Food>> listDataChild = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // load data
        loadWeatherData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // setting list adapter
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setTranscriptMode(ExpandableListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
    }

    private void loadWeatherData(){
        String restaurantId = "1";
        new FetchMenuTask().execute(restaurantId);
    }
    /*
     * Preparing the list data
     */
    private void prepareListData(String jstring) throws JSONException {
        JSONArray arr = new JSONArray(jstring);
        for (int i = 0; i < arr.length(); i++) { // Walk through the Array.
            JSONObject obj1 = arr.getJSONObject(i);
            listDataHeader.add(obj1.getString("name"));
            List<Food> foods = new ArrayList<>();
            JSONArray arr2 = obj1.getJSONArray("foods");
            for (int j = 0; j < arr2.length(); j++){
                JSONObject obj2 = arr2.getJSONObject(j);
                foods.add(new Food(obj2.getString("name"),(float)obj2.getDouble("price"),(float)obj2.getDouble("discount")));
            }
            listDataChild.put(obj1.getString("name"),foods);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class FetchMenuTask extends AsyncTask<String, Void, String[]>{

        @Override
        protected String[] doInBackground(String... params) {
            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String menuQuery = params[0];
            URL menuSearchUrl = NetworkUtils.buildUrl(menuQuery);

            try {
                String menuSearchResults = NetworkUtils.getResponseFromHttpUrl(menuSearchUrl);
                String[] result = new String[1];
                result[0] = menuSearchResults;
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] resultData) {
            if (resultData != null) {
                try {
                    prepareListData(resultData[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
