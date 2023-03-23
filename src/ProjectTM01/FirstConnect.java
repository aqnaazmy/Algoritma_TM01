package ProjectTM01;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FirstConnect {

    public static void main(String[] args) throws IOException {
    ConnectURI koneksisaya = new ConnectURI();
        URL myAddress = koneksisaya.buildURL(
                "https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM");
        String response = koneksisaya.getResponseFromHttpUrl(myAddress);


    assert response!= null;
    JSONArray responseJSON = new JSONArray(response);
        ArrayList<ModelResponse> ModelResponse = new ArrayList<>();
        int count = 0;
        for (int i=0; i< responseJSON.length(); i++){
        ModelResponse resModel = new ModelResponse();
        JSONObject myJSONObject = responseJSON.getJSONObject(i);
        String price =myJSONObject.getString("i_sell");
        resModel.setI_sell(price);
        ModelResponse.add(resModel);

        int priceInt =Integer.parseInt(price);
        if (priceInt <= 2000){
            count++;
            }
        }

        for (int index=0; index<ModelResponse.size(); index++){
            int price = Integer.parseInt(ModelResponse.get(index).getI_sell());
            if (price <= 2000){
                System.out.println("price = " + price);
            }
        }
        System.out.println("Jumlah total obat = " + count);
    }
}
