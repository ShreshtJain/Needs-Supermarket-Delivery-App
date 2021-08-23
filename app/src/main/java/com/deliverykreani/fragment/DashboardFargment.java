package com.deliverykreani.fragment;


import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.deliverykreani.R;
import com.deliverykreani.fragment.task.adapter.HistoryFragmentPagerAdapter;
import com.deliverykreani.utils.network.VolleySingleton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.deliverykreani.utils.jkeys.Keys.CommonResources.CONNECTION_URL_DEMAND;
import static com.deliverykreani.utils.jkeys.Keys.LoginKeys.TOKEN;
import static com.deliverykreani.utils.jkeys.Keys.LoginKeys.USER_ID;
import static com.deliverykreani.utils.jkeys.Keys.SiteShotRequest.DATA_PATH_ON_DEMAND_SUPPLY_ACCEPTED;


public class DashboardFargment extends Fragment {

    private View view;

    private TextView allotedOrders,completedOrders,totalOrders,acceptedOrders;
    private TabLayout tabLayout;
    private SimpleDateFormat dayformat;
    private SimpleDateFormat monthformat;
    private SimpleDateFormat yearformat;
    private SimpleDateFormat weekformat;

    private SharedPreferences sharedPreferenceStatus;
    private static final String loginStatus = "loginStatus";

    private String daystring;
    private String monthstring;
    private String yearstring;
    private String daystring2;
    private String monthstring2;
    private String yearstring2;
    private String weekstring;
    private String weekstring2;

    public String url=CONNECTION_URL_DEMAND + "api/v3/delivery/orders/count/";

    public DashboardFargment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_dashboard_fargment, container, false);

        sharedPreferenceStatus = getActivity().getSharedPreferences(loginStatus, Context.MODE_PRIVATE);
        String userId = sharedPreferenceStatus.getString(USER_ID, "");
        final int uId = (int) Float.parseFloat(userId);


        allotedOrders=view.findViewById(R.id.alloted_orders);
        completedOrders=view.findViewById(R.id.rejected_orders);
        totalOrders=view.findViewById(R.id.delivered_orders);
        acceptedOrders=view.findViewById(R.id.accepted_orders);

        dayformat = new SimpleDateFormat("dd");
        monthformat =new  SimpleDateFormat("MMM");
        yearformat = new SimpleDateFormat("yyyy");
        weekformat = new SimpleDateFormat("EEE");

        Calendar calendar = Calendar.getInstance();

        daystring2=dayformat.format(calendar.getTime());
        monthstring2=monthformat.format(calendar.getTime());
        yearstring2=yearformat.format(calendar.getTime());
        weekstring2=weekformat.format(calendar.getTime());

        daystring=dayformat.format(calendar.getTime());
        monthstring=monthformat.format(calendar.getTime());
        yearstring=yearformat.format(calendar.getTime());
        weekstring=weekformat.format(calendar.getTime());

        String startdate="?startDate="+weekstring+"%20"+monthstring+"%20"+daystring+"%20"+yearstring;
        String enddate="%2000:00:00&endDate="+weekstring2+"%20"+monthstring2+"%20"+daystring2+"%20"+yearstring2+"%2023:59:59";
        String url0=url;
        url0 += uId ;
        url0+= startdate+enddate;
        getData(url0);


        Log.i("task",url0);

        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        tabLayout.addTab(tabLayout.newTab().setText("Week"));
        tabLayout.addTab(tabLayout.newTab().setText("Month"));
        tabLayout.addTab(tabLayout.newTab().setText("Custom"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tabLayout.getSelectedTabPosition()==0)
                {
                    Calendar calendar = Calendar.getInstance();

                    daystring2=dayformat.format(calendar.getTime());
                    monthstring2=monthformat.format(calendar.getTime());
                    yearstring2=yearformat.format(calendar.getTime());
                    weekstring2=weekformat.format(calendar.getTime());

                    daystring=dayformat.format(calendar.getTime());
                    monthstring=monthformat.format(calendar.getTime());
                    yearstring=yearformat.format(calendar.getTime());
                    weekstring=weekformat.format(calendar.getTime());

                    String startdate="?startDate="+weekstring+"%20"+monthstring+"%20"+daystring+"%20"+yearstring;
                    String enddate="%2000:00:00&endDate="+weekstring2+"%20"+monthstring2+"%20"+daystring2+"%20"+yearstring2+"%2023:59:59";
                    String url0=url;
                    url0 += uId ;
                    url0+= startdate+enddate;

                    getData(url0);

                }
                else if(tabLayout.getSelectedTabPosition()==1)
                {
                    Calendar calendar = Calendar.getInstance();

                    daystring2=dayformat.format(calendar.getTime());
                    monthstring2=monthformat.format(calendar.getTime());
                    yearstring2=yearformat.format(calendar.getTime());
                    weekstring2=weekformat.format(calendar.getTime());

                    Calendar calendar2=Calendar.getInstance();
                    calendar2.add(Calendar.DAY_OF_WEEK, -7);

                    daystring=dayformat.format(calendar2.getTime());
                    monthstring=monthformat.format(calendar2.getTime());
                    yearstring=yearformat.format(calendar2.getTime());
                    weekstring=weekformat.format(calendar2.getTime());

                    String startdate="?startDate="+weekstring+"%20"+monthstring+"%20"+daystring+"%20"+yearstring;
                    String enddate="%2000:00:00&endDate="+weekstring2+"%20"+monthstring2+"%20"+daystring2+"%20"+yearstring2+"%2023:59:59";
                    String url0=url;
                    url0 += uId ;
                    url0+= startdate+enddate;

                    getData(url0);

                }
                else if(tabLayout.getSelectedTabPosition()==2)
                {
                    Calendar calendar = Calendar.getInstance();

                    daystring2=dayformat.format(calendar.getTime());
                    monthstring2=monthformat.format(calendar.getTime());
                    yearstring2=yearformat.format(calendar.getTime());
                    weekstring2=weekformat.format(calendar.getTime());

                    Calendar calendar2=Calendar.getInstance();
                    calendar2.add(Calendar.MONTH, -1);

                    daystring=dayformat.format(calendar2.getTime());
                    monthstring=monthformat.format(calendar2.getTime());
                    yearstring=yearformat.format(calendar2.getTime());
                    weekstring=weekformat.format(calendar2.getTime());

                    String startdate="?startDate="+weekstring+"%20"+monthstring+"%20"+daystring+"%20"+yearstring;
                    String enddate="%2000:00:00&endDate="+weekstring2+"%20"+monthstring2+"%20"+daystring2+"%20"+yearstring2+"%2023:59:59";
                    String url0=url;
                    url0 += uId ;
                    url0+= startdate+enddate;

                    getData(url0);



                }
                else if(tabLayout.getSelectedTabPosition()==3) {
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
    private void getData(String url) {

        Log.v("TASK URL", "" + url);
        final RequestQueue requestQueue = VolleySingleton.getsInstance().getMyRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            allotedOrders.setText(jsonObject.getString("alloted"));
                            completedOrders.setText(jsonObject.getString("rejected"));
                            totalOrders.setText(jsonObject.getString("delivered"));
                            acceptedOrders.setText(jsonObject.getString("accepted"));
                        }
                        catch (JSONException e)
                        {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(),"Some Error Occurred",Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("x-correlation-id", "true");
                headers.put("Authorization", "Bearer " + sharedPreferenceStatus.getString(TOKEN, ""));
                return headers;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String json = new String(
                            response.data,
                            "UTF-8"
                    );
                    if (json.length() == 0) {
                        return Response.success(
                                null,
                                HttpHeaderParser.parseCacheHeaders(response)
                        );
                    } else {

                        return super.parseNetworkResponse(response);
                    }
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }
}
