package com.example.Leaderboard.api;

import android.net.Uri;
import android.util.Log;

import com.example.Leaderboard.model.LeaderBoard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class LearningApiUtil {

    private LearningApiUtil(){

    }

    public static final String BASE_URL = "https://gadsapi.herokuapp.com/api";
    public static final String LEARNING = "hours";
    public static final String SKILLS = "skilliq";

    public static URL buildLearningUrl(){
        URL url = null;
        Uri.Builder uri = Uri.parse(BASE_URL).buildUpon().appendPath(LEARNING);
        try {
            url = new URL(uri.toString());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static URL buildSkillsUrl(){
        URL url = null;
        Uri.Builder uri = Uri.parse(BASE_URL).buildUpon().appendPath(SKILLS);
        try {
            url = new URL(uri.toString());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getJson(URL url) throws Exception{
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()){
                return scanner.next();
            }
            else return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }

    }

    public static ArrayList<LeaderBoard> getLearningLeaderboardFromJson(String json){
        ArrayList<LeaderBoard> leaderBoard = new ArrayList<>();

        final String NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY = "country";
        final  String BADGEURL = "badgeUrl";

        try {
            JSONArray jsonLeaders = new JSONArray(json);
            for(int i= 0; i<jsonLeaders.length(); i++){
                JSONObject learningLeader = jsonLeaders.getJSONObject(i);
                LeaderBoard leaders = new LeaderBoard(
                    learningLeader.getString(NAME),
                    learningLeader.getString(COUNTRY),
                    learningLeader.getString(HOURS),
                    learningLeader.getString(BADGEURL)
                );
                leaderBoard.add(leaders);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return leaderBoard;
    }

    public static ArrayList<LeaderBoard> getSkillLeaderboardFromJson(String json){
        ArrayList<LeaderBoard> leaderBoard = new ArrayList<>();

        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY = "country";
        final  String BADGEURL = "badgeUrl";

        try {
            JSONArray jsonLeaders = new JSONArray(json);
            for(int i= 0; i<jsonLeaders.length(); i++){
                JSONObject skillLeader = jsonLeaders.getJSONObject(i);
                LeaderBoard leaders = new LeaderBoard(
                        skillLeader.getString(NAME),
                        skillLeader.getString(COUNTRY),
                        skillLeader.getString(SCORE),
                        skillLeader.getString(BADGEURL)
                );
                leaderBoard.add(leaders);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return leaderBoard;
    }



}
