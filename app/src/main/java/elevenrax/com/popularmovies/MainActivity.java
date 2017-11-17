package elevenrax.com.popularmovies;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private ProgressBar mLoadingIndicator;
    private MovieAdapter mMovieAdapter;

    private final int RV_COLUMN_COUNT_VERTICAL = 2;
    private final int RV_COLUMN_COUNT_HORIZONTAL = 4;

    enum Filter { POPULAR, TOP_RATED }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mMovieAdapter = new MovieAdapter(this, getApplicationContext() );

        int deviceOrientation = getResources().getConfiguration().orientation;
        GridLayoutManager layoutGridMan;
        if (deviceOrientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutGridMan = new GridLayoutManager(this, RV_COLUMN_COUNT_VERTICAL);
        }
        else {
            layoutGridMan = new GridLayoutManager(this, RV_COLUMN_COUNT_HORIZONTAL);
        }

        mRecyclerView.setLayoutManager(layoutGridMan);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMovieAdapter);

        loadMovieData(Filter.POPULAR);
    }

    private void loadMovieData(Filter sortOption) {
        if (sortOption == Filter.POPULAR) {
            new FetchMoviesTask().execute("popular");
        }
        else if (sortOption == Filter.TOP_RATED) {
            new FetchMoviesTask().execute("top_rated");
        }
    }

    @Override
    public void onClick(Movie movie) {
        Intent detailViewIntent = new Intent(MainActivity.this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", movie);
        detailViewIntent.putExtras(bundle);

        startActivity(detailViewIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {

            case R.id.sort_most_popular:
                loadMovieData(Filter.POPULAR);
                return true;

            case R.id.sort_top_rated:
                loadMovieData(Filter.TOP_RATED);
                return true;

            default:
                return false;
        }
    }

    // Task to get movies
    public class FetchMoviesTask extends AsyncTask<String, Void, JSONArray> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            // Sort option not specified
            if(params.length == 0) {
                return null;
            }

            String sort = params[0];
            URL movieRequest = NetworkUtils.buildUrl(sort);
            Log.v(TAG, "I build: " + movieRequest.toString());

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
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            List<Movie> movieList = new ArrayList<>();

            for (int i=0; i < movieArray.length(); i++) {
                try {
                    JSONObject movie = movieArray.getJSONObject(i);
                    Log.v(TAG, movie.toString());

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

            mMovieAdapter.setMovieData(movieList);
        }
    }

}
