package elevenrax.com.popularmovies;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanlakes on 17/11/17.
 */

public class FetchMoviesTask extends AsyncTask<String, Void, JSONArray> {

    private Context mContext;
    private AsyncTaskCompleteListener<List<Movie>> mListener;

    public FetchMoviesTask(Context context, AsyncTaskCompleteListener<List<Movie>> listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        // Sort option not specified
        if(params.length == 0) {
            return null;
        }

        String sort = params[0];
        URL movieRequest = NetworkUtils.buildUrl(sort);

        try {
            String jsonMovieResponse = NetworkUtils
                    .getResponseFromHttpUrl(movieRequest);

            JSONObject response = new JSONObject(jsonMovieResponse);
            JSONArray movies = response.getJSONArray("results");

            return movies;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONArray movieArray) {
        List<Movie> movieList = new ArrayList<>();

        for (int i=0; i < movieArray.length(); i++) {
            try {
                JSONObject movie = movieArray.getJSONObject(i);

                String title = movie.getString("title");
                String poster = movie.getString("poster_path");
                String synopsis = movie.getString("overview");
                String userRating = movie.getString("vote_average");
                String releaseDate = movie.getString("release_date");

                Movie mov = new Movie(title, poster, synopsis, userRating, releaseDate);
                movieList.add( mov );

            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }

        mListener.onTaskComplete(movieList);
    }
}
