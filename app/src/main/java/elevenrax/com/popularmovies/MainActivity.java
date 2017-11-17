package elevenrax.com.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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
        if (haveInternetConnection()) {
            if (sortOption == Filter.POPULAR) {
                mLoadingIndicator.setVisibility(View.VISIBLE);
                new FetchMoviesTask(this, new FetchMovieTaskCompleteListener()).execute("popular");
            }
            else if (sortOption == Filter.TOP_RATED) {
                mLoadingIndicator.setVisibility(View.VISIBLE);
                new FetchMoviesTask(this, new FetchMovieTaskCompleteListener()).execute("top_rated");
            }
        }
        else {
            Toast.makeText(this, "No Internet. Please try again.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(Movie movie) {
        Intent detailViewIntent = new Intent(MainActivity.this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
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

    public class FetchMovieTaskCompleteListener implements AsyncTaskCompleteListener<List<Movie>> {

        @Override
        public void onTaskComplete(List<Movie> result) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            mMovieAdapter.setMovieData(result);
        }
    }

    private boolean haveInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null && activeNetwork.isConnectedOrConnecting());
    }

}
