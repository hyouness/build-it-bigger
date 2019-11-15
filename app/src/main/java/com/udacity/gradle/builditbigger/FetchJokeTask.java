package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.os.Handler;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.lang.ref.WeakReference;

// Credit: https://rominirani.com/gradle-tutorial-part-10-consuming-endpoints-in-android-code-6f9aa8c80ce6
public class FetchJokeTask extends AsyncTask<Void, Void, String> {

    private final WeakReference<MainActivity> activityWeakReference;

    private static MyApi myApi = null;

    private boolean connectionTimedOut;

    FetchJokeTask(MainActivity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
        connectionTimedOut = false;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activityWeakReference.get().showProgressBar();
        if (myApi == null) {
            buildApi();
        }
    }

    private static void buildApi() {
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl(BuildConfig.URL)
                .setGoogleClientRequestInitializer(request -> request.setDisableGZipContent(true));
        myApi = builder.build();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            return myApi.tellAJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            connectionTimedOut = true;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);
        MainActivity mainActivity = activityWeakReference.get();
        if (connectionTimedOut) {
            mainActivity.hideProgressBar();
            mainActivity.checkConnectionTimeOut();
        } else {
            new Handler().postDelayed(() -> {
                mainActivity.hideProgressBar();
                mainActivity.showJokeDisplay(joke);
            }, 300);
        }
    }
}
