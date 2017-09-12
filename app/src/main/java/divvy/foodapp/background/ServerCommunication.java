package divvy.foodapp.background;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by kille on 2017-09-12.
 */

public class ServerCommunication {
    private static String serverIp = "10.117.80.139";







    public static boolean login(String username, String password) throws Exception{
        try {
            URL url = new URL("http://"+serverIp+":8080/login/"+username+"/"+password);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if(responseCode != 200) {
                Log.e("TEST","CODE: "+responseCode);
            }

            Scanner sc = new Scanner(conn.getInputStream());
            String inLine = "";
            while(sc.hasNext()) {
                inLine += sc.nextLine();
            }
            if(!inLine.contains("0")) {
                return true;
            }else {
                return false;
            }
        }catch(Exception e) {
            Log.e("TEST",Log.getStackTraceString(e));
            return false;
        }

    }
}
