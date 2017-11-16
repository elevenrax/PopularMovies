package elevenrax.com.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanlakes on 11/11/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private List<Movie> mMovieData;
    private final MovieAdapterOnClickHandler mClickHandler;
    private Context mContext;


    public MovieAdapter(MovieAdapterOnClickHandler handler, Context context) {
        this.mClickHandler = handler;
        mContext = context;
        mMovieData = new ArrayList<>();
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie movie);
    }


    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView mImageView;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_movie_data);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Movie selectedMovie = mMovieData.get(adapterPosition);
            mClickHandler.onClick(selectedMovie);
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        Movie movie = mMovieData.get(position);
        Picasso.with(mContext).load(movie.getLargePoster()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovieData.size();
    }

    public void setMovieData(List<Movie> movieList) {
        mMovieData = movieList;
        notifyDataSetChanged();
    }


}
