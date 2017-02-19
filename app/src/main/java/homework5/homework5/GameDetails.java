package homework5.homework5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class GameDetails extends AppCompatActivity implements GetGameAsync.IGameDetails {
    IndividualGame currentGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        String id = getIntent().getExtras().getString("GameId");
        Log.i("ID",id);
        String url = "http://thegamesdb.net/api/GetGame.php?id="+id;
        new GetGameAsync(GameDetails.this).execute(url);
    }

    @Override
    public void getGame(IndividualGame indGame) {
        currentGame = indGame;
        Log.i("Current Game", String.valueOf(currentGame));
    }
}
