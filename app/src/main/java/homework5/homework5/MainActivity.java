package homework5.homework5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetGameListAsync.IGame{
    EditText getGame;
    Button search;
    Button go;
    ProgressDialog progressDialog;
    LinearLayout container;
    RadioButton radioButton;

    String url = "http://thegamesdb.net/api/GetGamesList.php?name=";
    ArrayList<Game> gameList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getGame = (EditText) findViewById(R.id.editText);
        search = (Button) findViewById(R.id.buttonSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gameName = getGame.getText().toString();
                String finUrl = url + gameName;
                Log.i("MainActivity URL",finUrl);
                if(isConnectedOnline()) {
                    new GetGameListAsync(MainActivity.this).execute(finUrl);
                }
                else {
                    Toast.makeText(MainActivity.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        go=(Button)findViewById(R.id.buttonGo);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Buttongo", String.valueOf(radioButton.getId()));
                Intent intent = new Intent(MainActivity.this,GameDetails.class);
                intent.putExtra("GameId",String.valueOf(radioButton.getId()));
                startActivity(intent);
            }
        });

    }
    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Games");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public void stopProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void getGameList(ArrayList<Game> gamelist) {
        gameList.addAll(gamelist);
        Log.i("game list", String.valueOf(gamelist));
        display(gameList);
    }

    private void display(ArrayList<Game> gameList) {
       for(Game game : gameList) {
           LayoutInflater layoutinflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           container = (LinearLayout)findViewById(R.id.scrollView);
           View AddView = layoutinflater.from(this).inflate(R.layout.row,null);
           radioButton = (RadioButton)AddView.findViewById(R.id.radioButton);
           radioButton.setId(game.getId());
           radioButton.setText(game.getName().toString().trim()+", Released in "+game.getReleaseDate().toString().trim()+
                    "Platform: "+game.getPlatform().toString().trim());
           container.addView(AddView);
       }
    }




    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
