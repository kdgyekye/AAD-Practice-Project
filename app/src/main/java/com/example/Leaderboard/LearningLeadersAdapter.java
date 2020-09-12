package com.example.Leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Leaderboard.model.LeaderBoard;

import java.util.ArrayList;

public class LearningLeadersAdapter extends RecyclerView.Adapter<LearningLeadersAdapter.LearningLeadersViewHolder> {
    ArrayList<LeaderBoard> mLeaders;
    TextView mName;
    TextView mDetails;

    public LearningLeadersAdapter(ArrayList<LeaderBoard> leaders) {
        this.mLeaders = leaders;
    }

    @NonNull
    @Override
    public LearningLeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.learning_leaders_list_row, parent, false);
        return new LearningLeadersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLeadersViewHolder holder, int position) {
        LeaderBoard leader = mLeaders.get(position);
        holder.bind(leader);

    }

    @Override
    public int getItemCount() {
        return mLeaders.size();
    }

    public class LearningLeadersViewHolder extends RecyclerView.ViewHolder{

        public LearningLeadersViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.leaderName);
            mDetails = itemView.findViewById(R.id.leaderDetails);
        }
        public void bind(LeaderBoard leader){
            mName.setText(leader.mName);
            StringBuilder details = new StringBuilder();
            details.append(leader.mHours);
            details.append(" learning hours,");
            details.append(leader.mCountry);
            mDetails.setText(details);
        }
    }
}

