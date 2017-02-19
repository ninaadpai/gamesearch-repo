package homework5.homework5;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ojasv on 2/18/17.
 */

public class GameDetailParser {
    static public class GameDetailPullParser {
        static IndividualGame parseGameDetail(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            IndividualGame inGame =null;
            SimilarGame simGame = null;
            ArrayList<SimilarGame> simGameList = null;
            int event = parser.getEventType();
            while(event!=XmlPullParser.END_DOCUMENT){
                switch(event) {
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("Game")){
                            inGame = new IndividualGame();
                        } else if (parser.getName().equals("GameTitle")){
                            inGame.setName(parser.nextText());
                        }  else if (parser.getName().equals("Overview")) {
                            inGame.setOverview(parser.nextText());
                        }  else if (parser.getName().equals("genre")) {
                            inGame.setGenre(parser.nextText());
                        } else if (parser.getName().equals("Publisher")) {
                            inGame.setPublisher(parser.nextText());
                        }
                          else if (parser.getName().equals("Similar")) {
                            if(parser.getName().equals("Game")) {
                                simGame = new SimilarGame();
                            }else if(parser.getName().equals("id")){
                                simGame.setId(parser.nextText());
                            }
                            else if(parser.getName().equals("PlatformId")){
                                simGame.setPlatformId(parser.nextText());
                            }
                            simGameList.add(simGame);
                            simGame = null;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("Game")){
                            return inGame;
                        }
                        break;
                    default:
                        break;
                }
            }
            return null;
        }
    }
}
