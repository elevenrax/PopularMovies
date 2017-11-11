package elevenrax.com.popularmovies;

import android.graphics.Bitmap;

/**
 * Created by nathanlakes on 11/11/17.
 */

public class Movie {

    private String mTitle;
    private Bitmap mPoster;
    private String mSynopsis;
    private String mUserRating;
    private String mReleaseDate;

    public Movie(String title, Bitmap poster, String synopsis, String userRating, String releaseDate) {
        this.mTitle = title;
        this.mPoster = poster;
        this.mSynopsis = synopsis;
        this.mUserRating = userRating;
        this.mReleaseDate = releaseDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Bitmap getPoster() {
        return mPoster;
    }

    public void setPoster(Bitmap mPoster) {
        this.mPoster = mPoster;
    }

    public String getSynopsis() {
        return mSynopsis;
    }

    public void setSynopsis(String mSynopsis) {
        this.mSynopsis = mSynopsis;
    }

    public String getUserRating() {
        return mUserRating;
    }

    public void setUserRating(String mUserRating) {
        this.mUserRating = mUserRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }
}
