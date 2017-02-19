package homework5.homework5;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ojasv on 2/16/17.
 */

public class GetGameListAsync extends AsyncTask<String,Object,ArrayList<Game>> {
    ArrayList<Game> gameList = null;
    IGame iGame;
    public GetGameListAsync(IGame iGame){
        this.iGame = iGame;
    }

    @Override
    protected void onPreExecute() {
        iGame.showProgressDialog();
    }

    @Override
    protected ArrayList<Game> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            Log.i("AsyncTask URL", String.valueOf(url));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK){
                InputStream in = con.getInputStream();
                gameList = GameUtil.GamePullParser.parseGame(in);
                return gameList;
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
    protected void onPostExecute(ArrayList<Game> games) {
        super.onPostExecute(games);
        Log.d("demo",games.toString());
        iGame.stopProgress();
        iGame.getGameList(games);
    }

    static public interface IGame {
        public void showProgressDialog();

        public void stopProgress();

        public void getGameList(ArrayList<Game> gamelist);
    }
}
