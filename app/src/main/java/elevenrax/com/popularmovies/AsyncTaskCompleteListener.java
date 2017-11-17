package elevenrax.com.popularmovies;

/*
Mimick Delegate/Protocol Pattern from iOS to take AsyncTask out of MainActivity.
All for neater code.
http://www.jameselsey.co.uk/blogs/techblog/extracting-out-your-asynctasks-into-separate-classes-makes-your-code-cleaner/
 */
public interface AsyncTaskCompleteListener<T> {

    public void onTaskComplete(T result);

}


