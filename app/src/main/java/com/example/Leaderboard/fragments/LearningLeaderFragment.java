package com.example.Leaderboard.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Leaderboard.api.LearningApiUtil;
import com.example.Leaderboard.LearningLeadersAdapter;
import com.example.Leaderboard.R;
import com.example.Leaderboard.model.LeaderBoard;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearningLeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningLeaderFragment extends Fragment {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private DialogFragment errorDialogFragment;
    View v;

    public LearningLeaderFragment() {
        // Required empty public constructor
    }


    public static LearningLeaderFragment newInstance() {
        LearningLeaderFragment fragment = new LearningLeaderFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        errorDialogFragment = OkDialogFragment.newInstance(getString(R.string.network_error));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_learning_leader, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mProgressBar = (ProgressBar) v.findViewById(R.id.queryLoading);

        URL url = LearningApiUtil.buildLearningUrl();
        try{
            LearningLeaderFragment.LeaderBoardQueryThread leaderBoardQuery = new LearningLeaderFragment.LeaderBoardQueryThread();
            leaderBoardQuery.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error: " , Objects.requireNonNull(e.getMessage()));
        }

        LinearLayoutManager leadersLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(leadersLayout);

        return v;
    }

    @SuppressLint("StaticFieldLeak")
    public class LeaderBoardQueryThread extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String result = null;
            try{
                result = LearningApiUtil.getJson(searchUrl);
            }
            catch (Exception e){
                Log.e("Error", Objects.requireNonNull(e.getMessage()));
            }
            return result;
        }
        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            TextView emptyView = (TextView) v.findViewById(R.id.empty);
            mProgressBar.setVisibility(View.GONE);
            if(result == null){
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) v.findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("Could not retrieve information");

                Toast toast = new Toast(getContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
            else {
                ArrayList<LeaderBoard> leader = LearningApiUtil.getLearningLeaderboardFromJson(result);
                LearningLeadersAdapter leadersAdapter = new LearningLeadersAdapter(leader);
                mRecyclerView.setAdapter(leadersAdapter);
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

}
