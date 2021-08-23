package com.deliverykreani.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;

import com.deliverykreani.R;
import com.deliverykreani.fragment.task.History;
import com.deliverykreani.fragment.task.Request;
import com.deliverykreani.fragment.task.Tasks;
import com.deliverykreani.fragment.task.adapter.HistoryFragmentPagerAdapter;

import java.util.Objects;

public class TaskFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private HistoryFragmentPagerAdapter adapter;
    private TabLayout tabLayout;

    private OnFragmentInteractionListener mListener;
    private ViewPager viewPager;

    public TaskFragment() {

    }

    public static TaskFragment newInstance(String param1, String param2) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        getHistoryList(view);
        return view;
    }

    private void getHistoryList(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        adapter = new HistoryFragmentPagerAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(new DashboardFargment(), "DASHBOARD");
        adapter.addFragment(new Tasks(), "TASK");
        adapter.addFragment(new History(), "HISTORY");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public interface Updateable {
        public void update();
    }
}
