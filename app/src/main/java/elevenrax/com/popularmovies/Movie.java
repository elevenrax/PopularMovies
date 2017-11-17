package elevenrax.com.popularmovies;

import java.io.Serializable;

/**
 * Created by nathanlakes on 11/11/17.
 */

public class Movie implements Serializable {

    private String mTitle;
    private String mPoster;
    private String mSynopsis;
    private String mUserRating;
    private String mReleaseDate;

    final private static String BASE_IMG_URL = "http://image.tmdb.org/t/p/";
    final private static String SMALL = "w342/";
    final private static String LARGE = "w780/";

    public Movie(String title, String poster, String synopsis, String userRating, String releaseDate) {
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

    public String getSmallPoster() {
        return BASE_IMG_URL + SMALL + mPoster;
    }

    public String getLargePoster() {
        return BASE_IMG_URL + LARGE + mPoster;
    }

    public void setPoster(String mPoster) {
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

    @Override
    public String toString() {
        return mTitle + ", " + mPoster + ", " + mSynopsis + ", " + mUserRating + ", " + mReleaseDate;
    }
}
