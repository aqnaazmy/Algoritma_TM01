import ProjectTM01.ConnectURI;
import ProjectTM01.ModelResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class MyGUI extends JFrame implements ActionListener {

    private JLabel countLabel;
    private JButton submitButton;

    public MyGUI() {
        // set frame properties
        this.setTitle("My GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);

        // create and add components
        JPanel panel = new JPanel(new GridLayout(2, 1));
        countLabel = new JLabel("Jumlah total obat dengan harga di bawah 2000: ");
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        panel.add(countLabel);
        panel.add(submitButton);

        this.getContentPane().add(panel);

        // show the frame
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // get the response from API
            ConnectURI koneksisaya = new ConnectURI();
            String urlString = "https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM";
            try {
                ArrayList<ModelResponse> modelResponses = getModelResponse(urlString);

                // count the number of prices below 2000
                int count = 0;
                for (ModelResponse response : modelResponses) {
                    int price = Integer.parseInt(response.getI_sell());
                    if (price <= 2000) {
                        count++;
                    }
                }

                // update the label with the count
                countLabel.setText("Jumlah total obat dengan harga di bawah 2000: " + count);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private ArrayList<ModelResponse> getModelResponse(String urlString) throws Exception {
        URL url = new URL(urlString);
        String response = new ConnectURI().getResponseFromHttpUrl(url);

        JSONArray responseJSON = new JSONArray(response);
        ArrayList<ModelResponse> modelResponses = new ArrayList<>();
        for (int i = 0; i < responseJSON.length(); i++) {
            ModelResponse modelResponse = new ModelResponse();
            JSONObject myJSONObject = responseJSON.getJSONObject(i);
            String price = myJSONObject.getString("i_sell");
            modelResponse.setI_sell(price);
            modelResponses.add(modelResponse);
        }

        return modelResponses;
    }

    public static void main(String[] args) {
        new MyGUI();
    }
}
