package elevenrax.com.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private ImageView mPoster;
    private TextView mRating;
    private TextView mReleaseDate;
    private TextView mSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitle = (TextView) findViewById(R.id.tv_title);
        mPoster = (ImageView) findViewById(R.id.iv_poster);
        mRating = (TextView) findViewById(R.id.tv_rating);
        mReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mSynopsis = (TextView) findViewById(R.id.tv_synopsis);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            Movie m = (Movie) b.getParcelable("movie");
            mTitle.setText(m.getTitle());
            Picasso.with(this).load(m.getSmallPoster()).into(mPoster);
            mRating.setText(m.getUserRating());
            mReleaseDate.setText(m.getReleaseDate());
            mSynopsis.setText(m.getSynopsis());
        }

    }
}
