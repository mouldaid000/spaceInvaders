/**
 * Created by Aidan Moulder on 3/15/2017.
 */
public class Stats {
    static boolean menu = true, play = false, pause = false, end = false;
    static int score = 0;

    public static boolean isMenu(){
        return menu;
    }
    public static boolean isPlay(){
        return play;
    }
    public static boolean isPause(){
        return pause;
    }
    public static boolean isEnd(){
        return end;
    }
    public static void endGame(){
        menu = false;
        play = false;
        pause = false;
        end = true;
    }
    public static void togglePause(){
        if(isPlay()){
            play = false;
            pause = true;
        }
        else{
            pause = false;
            play = true;
        }
    }
    public static void startPlay(){
        if(isMenu() ^ isEnd()){
            menu = false;
            play = true;
            end = false;

            resetScore();
        }
    }
    public static void resetScore(){
        score = 0;
    }
    public static void addScore(){
        int multiplier = 1;
        if(score > 1000 && score <= 3000){
            multiplier = 2;
        }
        if(score > 3000 && score <= 5000){
            multiplier = 3;
        }

        score += (100*multiplier);

    }

}
