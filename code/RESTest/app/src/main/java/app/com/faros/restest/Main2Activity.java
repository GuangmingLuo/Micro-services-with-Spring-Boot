package app.com.faros.restest;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static android.graphics.Color.rgb;

public class Main2Activity extends AppCompatActivity {

    private TextView restaurantName;
    private Button backButton;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private GridLayout menuLayout;
    private Button getMenu;
    private boolean show=false;
    private LinearLayout menuJson;
    private TextView text1;
    private JSONArray res=null;
    private GridLayout foodLayout;
    private ArrayList<JSONArray> menuItems= new ArrayList<>();
    private HashMap<Integer,JSONArray> items = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        menuLayout = (GridLayout) findViewById(R.id.menuLayout);
        menuJson = (LinearLayout) findViewById(R.id.menuJson);
        foodLayout = (GridLayout) findViewById(R.id.foodLayout);
        restaurantName = (TextView) findViewById(R.id.restaurantName);
        restaurantName.setText(getIntent().getStringExtra("RESTAURANT_NAME"));
        getSupportActionBar().setTitle(getIntent().getStringExtra("RESTAURANT_NAME"));

        text1 = (TextView) findViewById(R.id.text1);

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getMenu = (Button) findViewById(R.id.getMenu);
        getMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(show){
                    menuJson.removeAllViews();
                    show=false;
                }else{
                    if (res!=null){
                        TextView temp = new TextView(getApplicationContext());
                        temp.setText(res.toString());
                        temp.setTextColor(rgb(240,240,240));
                        menuJson.addView(temp);
                        show=true;
                    }

                }

            }
        });

        getMenuInformation();

//        mPager = (ViewPager) findViewById(R.id.pager);
//        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),3);
//        System.out.println("==========Testing " +mPagerAdapter.getPageTitle(0));
//        mPager.setAdapter(mPagerAdapter);

    }

    private void getMenuInformation() {
        AsyncHttpClient client = new AsyncHttpClient();
//        String url = "http://10.1.15.19:81/api/restaurantByName?name=" + getSupportActionBar().getTitle();
        final String url = getString(R.string.server_ip)+":82/api/menu?restaurantId=" + getIntent().getIntExtra("RESTAURANT_ID",1);
        client.get(url,null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                String add = "Trying to connect with url: "+url;
//                text1.setText(add);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"
                res=response;
//                menuItems.clear();
                items.clear();
                for(int i=0;i<=response.length();i++) {
                    menuItems.add(new JSONArray());
                }
//                restaurantName.append(Integer.toString(response.length()));
                for(int i=0;i<response.length();i++) {
                    JSONObject test = new JSONObject();
                    try {
                        test = (JSONObject) response.get(i);
                        int id = test.getInt("id");
//                        restaurantName.append("id="+id);
                        createButton(test.getString("name"),id);
                        getMenuItems(id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//                text1.append(" Failed " + statusCode);
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
//                text1.append(" Retrying " + retryNo + "st time.");
            }
        });
    }
    private void getMenuItems(final int id) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(getString(R.string.server_ip)+":83/api/food?menuId="+id,null, new JsonHttpResponseHandler(){
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"
//                menuItems.add(response);
                items.put(id,response);
                }


            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
               restaurantName.append(" Failed " + statusCode);
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
//                text1.append(" Retrying " + retryNo + "st time.");
            }
        });

    }

    private void createItem(final String name) {
        Button myButton = new Button(getApplicationContext());
        String[] partial = name.split(" ");
        String shortened = "";
        for(int j=0;j<partial.length;j++){
            shortened = shortened.concat(Character.toString(partial[j].charAt(0)));
        }
        myButton.setText(shortened);
//        myButton.setBackgroundColor(rgb(150,150,150));
        ViewGroup.LayoutParams params;
        int dps = 65;
        final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        params = new ViewGroup.LayoutParams(pixels,pixels);
        myButton.setLayoutParams(params);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                menuLayout(name,id);
            }
        });
        foodLayout.addView(myButton);

        myButton.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                restaurantName.setText(name);
                return true;
            }
        });
    }


    private void createButton(final String name, final int id) {
        Button myButton = new Button(getApplicationContext());
        myButton.setText(name);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                restaurantName.append("getting at Id="+id);

                for (int i=0;i<menuItems.size();i++) {
                    JSONArray items = menuItems.get(i);
//                    restaurantName.append("Showing list on position ==="+i);
//                    restaurantName.append("\n"+items.toString());
                }

                foodLayout.removeAllViews();
//                JSONArray items = menuItems.get(id);
                JSONArray itemss = items.get(id);
                for(int i=0;i<itemss.length();i++){
                    JSONObject test = new JSONObject();
                    try {
                        test = (JSONObject) itemss.get(i);
//                        restaurantName.append(test.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        final String food = test.getString("name");

                        createItem(food);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
//                menuLayout(name,id);
            }
        });
        menuLayout.addView(myButton);
    }

}
