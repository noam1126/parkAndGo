package com.example.tempsign.services;

import android.os.StrictMode;

import com.example.tempsign.models.ParkingLot;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DataService {

    private static ArrayList<ParkingLot> arrParkingLots=new ArrayList<>();

    public static ArrayList<ParkingLot> getArrParkingLots() {//נבנה את המערך מהAPI שלנו

        String sURL="https://gisn.tel-aviv.gov.il/arcgis/rest/services/IView2/MapServer/556/query?where=1%3D1&outFields=name,address,num_vehicles,num_disabled&f=json";

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();//2 שורות שמפה המערכת יוצרת לנו THEARD בודד ככה שכל דבר מתחת ל2 השורות יעבוד במקביל לאפליקיה שלנו
        StrictMode.setThreadPolicy(policy);

        try {
            URL url=new URL(sURL);//לקחנו את הסטרינג והמרנו אותו לURL
            HttpURLConnection request=(HttpURLConnection) url.openConnection();//התחברנו לכתובת והפעלנו אותו ואז קיבלנו את הJSON
            request.connect();

            JsonParser jp=new JsonParser();
            JsonElement root=jp.parse(new InputStreamReader((InputStream) request.getContent()));//מפרסר לי את כל המידע
            JsonObject rootObject = root.getAsJsonObject();
            JsonArray featuresArray = rootObject.getAsJsonArray("features");

            for (JsonElement je : featuresArray) {
                JsonObject featureObject = je.getAsJsonObject();
                JsonObject attributes = featureObject.getAsJsonObject("attributes");

                String parksNum = getStringValue(attributes, "num_vehicles");
                if (parksNum!="לא ידוע") {
                    String name = getStringValue(attributes, "name");
                    String address = getStringValue(attributes, "address");
                    String disablesNum = getStringValue(attributes, "num_disabled");
                    String parkingLotId = getStringValue(attributes, "oid");

                    arrParkingLots.add(new ParkingLot("שם החניון: "+name,"כתובת: "+ address,"מספר חניות: "+ parksNum,"מספר חניות נכים: "+ disablesNum));//יצירת אובייקט של מדינה אחת עם הנתונים שלה
                }
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arrParkingLots;
    }

    private static String getStringValue(JsonObject obj, String fieldName) {
        JsonElement enties = obj.get(fieldName);

        if (enties != null && !enties.isJsonNull()) {
            return enties.toString().replace("\"", "");
        } else {
            return "לא ידוע";
        }
    }
}
