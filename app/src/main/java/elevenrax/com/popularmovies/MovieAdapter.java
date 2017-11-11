package elevenrax.com.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nathanlakes on 11/11/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private final MovieAdapterOnClickHandler handler;

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    public MovieAdapter(MovieAdapterOnClickHandler handler) {
        this.handler = handler;
    }


    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //TODO Data Members Holding Views

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            // TODO.. bind
        }

        @Override
        public void onClick(View view) {

        }
    }

    // TODO -- these three overridden methods from Adapter.
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
