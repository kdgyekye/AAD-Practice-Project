package com.example.Leaderboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.Leaderboard.fragments.LearningLeaderFragment;
import com.example.Leaderboard.fragments.SkillLeaderFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {
    @Override
    public int getCount() {
        return 2;
    }

    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return LearningLeaderFragment.newInstance();
        else
            return SkillLeaderFragment.newInstance();

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0)
            return "Learning Leaders";
        else
            return "Skill IQ Leaders";

    }


}
