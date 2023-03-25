
import ProjectTM01.ConnectURI;
import ProjectTM01.ModelResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MyGUI extends javax.swing.JFrame {

    private javax.swing.JButton btnklik;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtnama;

    private javax.swing.JTextField txtpwd;

    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnklik = new javax.swing.JButton();
        txtnama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtpwd = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnklik.setText("Submit");
        btnklik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnklikActionPerformed(evt);
            }
        });

        txtnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamaActionPerformed(evt);
            }
        });

        txtpwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                txtnamaActionPerformed(evt);
            }
        });

        jLabel2.setText("Masukan Harga Obat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnklik, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnklik, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(145, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnklikActionPerformed(java.awt.event.ActionEvent evt) {
        String cariHarga = txtnama.getText();
        ConnectURI koneksisaya = new ConnectURI();
        URL myAddress = koneksisaya.buildURL("https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM");
        String response = null;
        try {
            response = koneksisaya.getResponseFromHttpUrl(myAddress);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assert response != null;
        JSONArray responJSON = new JSONArray(response);
        ArrayList<ModelResponse> respondModel = new ArrayList<>();
        for (int i = 0; i < responJSON.length(); i++) {
            ModelResponse resmodel = new ModelResponse();
            JSONObject myJSONObject = responJSON.getJSONObject(i);
            resmodel.setI_sell(myJSONObject.getString("i_sell"));
            respondModel.add(resmodel);
        }
        int count = 0;
        for (ModelResponse responsee : respondModel) {
            if (responsee.getI_sell().equals(cariHarga)) {
                count++;
            }
        }
        JOptionPane.showMessageDialog(this,"Jumlah obat dengan harga " + cariHarga + " = " + count);

    }
    private void txtnamaActionPerformed(java.awt.event.ActionEvent evt) {
    }
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyGUI().setVisible(true);
            }
        });
    }
    public MyGUI() {
        initComponents();
    }

}
