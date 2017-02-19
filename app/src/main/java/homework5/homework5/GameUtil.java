package homework5.homework5;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ojasv on 2/16/17.
 */

public class GameUtil {
    static public class GamePullParser {
        static ArrayList<Game> parseGame(InputStream in) throws XmlPullParserException,IOException{
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
           Game game = null;
            ArrayList<Game> gameArrayList = new ArrayList<Game>();
            int event = parser.getEventType();
            Log.i("GameUtil", "Reached");
            while(event !=XmlPullParser.END_DOCUMENT) {
                switch(event) {
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("Game")){
                            game = new Game();

                        } else if (parser.getName().equals("id")){
                                game.setId(Integer.parseInt(parser.nextText()));
                        }  else if (parser.getName().equals("GameTitle")) {
                                game.setName(parser.nextText());
                        }  else if (parser.getName().equals("ReleaseDate")) {
                                game.setReleaseDate(parser.nextText());
                        } else if (parser.getName().equals("Platform")) {
                                game.setPlatform(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("Game")){
                            gameArrayList.add(game);
                            game=null;
                        }
                          break;
                    default:
                        break;
                }
                event = parser.next();
            }
            Log.i("GameUtil", String.valueOf(gameArrayList));
            return gameArrayList;
        }
    }
}
