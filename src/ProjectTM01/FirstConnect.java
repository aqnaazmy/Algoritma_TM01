package ProjectTM01;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class FirstConnect {

    public static void main(String[] args) throws IOException {
    ConnectURI koneksisaya = new ConnectURI();
        URL myAddress = koneksisaya.buildURL(
                "https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM");
        String response = koneksisaya.getResponseFromHttpUrl(myAddress);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukan Harga = ");
        String cariHarga = scanner.nextLine();

        assert response!= null;
        JSONArray responseJSON = new JSONArray(response);
        ArrayList<ModelResponse> ModelResponse = new ArrayList<>();
        int count = 0;
        for (int i=0; i< responseJSON.length(); i++) {

            ModelResponse resModel = new ModelResponse();
            JSONObject myJSONObject = responseJSON.getJSONObject(i);
            resModel.setI_sell(myJSONObject.getString("i_sell"));
            ModelResponse.add(resModel);
        }
        for (ModelResponse respon : ModelResponse){
        if (respon.getI_sell().equals(cariHarga)){
            count++;
            }
        }

        System.out.println("Jumlah total obat = " + count);
    }
}
