package app.com.faros.restest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.graphics.Color.rgb;

public class MainActivity extends AppCompatActivity {

    private TextView text1;
    private LinearLayout restaurantOverview;
    private Button requestButton;
    private Button clearButton;
    private LinearLayout jsonLayout;
    private boolean show=false;
    private JSONArray res=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.text1);
        restaurantOverview = (LinearLayout) findViewById(R.id.restaurantOverview);
        jsonLayout = (LinearLayout) findViewById(R.id.jsonLayout);

        requestButton = (Button) findViewById(R.id.requestButton);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(show){
                    jsonLayout.removeAllViews();
                    show=false;
                }else{
                    if(res!=null){
                        TextView temp = new TextView(getApplicationContext());
                        temp.setText(res.toString());
                        temp.setTextColor(rgb(240,240,240));
                        jsonLayout.addView(temp);
                        show=true;
                    }

                }
            }
        });

        clearButton=(Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                populateRestaurantList();
            }
        });
        populateRestaurantList();

    }


    private void populateRestaurantList(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getString(R.string.server_ip)+":81/api/restaurants",null, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                text1.setText(" Trying to connect.");
                restaurantOverview.removeAllViews();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"
                text1.setText(" success");
                res=response;
                for (int i=0;i<response.length();i++){
                    JSONObject test = new JSONObject();
                    try {
                        test = (JSONObject) response.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        final String name = test.getString("name");
                        final int id = test.getInt("id");
                        createButton(name, id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                text1.append(" Failed " + statusCode);
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                text1.append(" Retrying " + retryNo + "st time.");
            }
        });
    }



    private void createButton(final String name, final int id) {
        Button myButton = new Button(getApplicationContext());
        myButton.setText(name);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewRestaurant(name,id);
            }
        });
        restaurantOverview.addView(myButton);
    }

    private void viewRestaurant(String name, int id) {
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("RESTAURANT_NAME", name);
        intent.putExtra("RESTAURANT_ID", id);
        startActivity(intent);

    }
}
