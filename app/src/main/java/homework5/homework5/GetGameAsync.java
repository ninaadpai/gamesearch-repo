package homework5.homework5;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ninaadpai on 2/18/17.
 */
public class GetGameAsync extends AsyncTask<String,Object,IndividualGame>{
    IndividualGame gameDetails = null;
    IGameDetails igDetails;
    public GetGameAsync(IGameDetails gameDetails) {
        this.igDetails = gameDetails;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected IndividualGame doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            Log.i("AsyncTask URL", String.valueOf(url));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                gameDetails = GameDetailParser.GameDetailPullParser.parseGameDetail(in);
                return gameDetails;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
        protected void onPostExecute (IndividualGame games){
            super.onPostExecute(games);
            Log.d("demo", games.toString());
            igDetails.getGame(games);

    }
        static public interface IGameDetails {
            public void getGame(IndividualGame indGame);
        }
    }

