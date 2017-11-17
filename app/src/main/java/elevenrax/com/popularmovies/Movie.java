package elevenrax.com.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nathanlakes on 11/11/17.
 * Make Parcelable per preferred Android approach
 * https://guides.codepath.com/android/using-parcelable
 */

public class Movie implements Parcelable {

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


    protected Movie(Parcel in) {
        mTitle = in.readString();
        mPoster = in.readString();
        mSynopsis = in.readString();
        mUserRating = in.readString();
        mReleaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mPoster);
        parcel.writeString(mSynopsis);
        parcel.writeString(mUserRating);
        parcel.writeString(mReleaseDate);
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
