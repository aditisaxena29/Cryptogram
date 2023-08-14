/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DB.DbConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.security.Key;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Aditi
 */
public class ViewFile extends javax.swing.JFrame {

    /**
     * Creates new form ViewFile
     */
    public ViewFile() {
        initComponents();
        try {
           //Connection conn =DbConnection.getConnection();
           PreparedStatement ps = DbConnection.conn.prepareStatement("select * from encrypt");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String SNo = String.valueOf(rs.getInt("SNo"));
                String Files = rs.getString("FileName");
                
                String[] tblData = {SNo,Files};
                DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();
                tblModel.addRow(tblData);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //checks whether the encryption and decryption passwords are same
   /* boolean areHashesEqual(File file, String keyHash) throws FileNotFoundException, IOException
    {
        
        BufferedInputStream fileReader=new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
        //reading key hash from file
        StringBuffer keyHashFromFile=new StringBuffer(128);
        for(int i=0; i<128; i++)
        {
            keyHashFromFile.append((char)fileReader.read());
        }
                
        //verifying both hashes
        fileReader.close();
        if(keyHashFromFile.toString().equals(keyHash))
        {
            return true;
        }
        return false;
    }*/
    
    /*private String getHashInString(String key) throws NoSuchAlgorithmException
    {
        byte[] keyHash;
        final MessageDigest md = MessageDigest.getInstance("SHA-512");
                keyHash = md.digest(key.getBytes());
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< keyHash.length ;i++)
                {
                    sb.append(Integer.toString((keyHash[i] & 0xff) + 0x100, 16).substring(1));
                }
                String hashOfPassword = sb.toString();
                return hashOfPassword;
                
    }*/
    
    String key(){
        String key=jTextField2.getText();
        return key;
    }
    
    private Key generateKey() throws Exception{
            byte[] keyValue = key().getBytes();
            Key key = new SecretKeySpec(keyValue,"AES");
            return key;        
    }
    
    void DecryptFile(){
        int sno=Integer.parseInt(jTextField3.getText());
        try {
                   Key key = generateKey();
                   Cipher c = Cipher.getInstance("AES");
                   c.init(Cipher.DECRYPT_MODE,key);
                   File file=new File(jTextField4.getText());
            file.createNewFile();
            //Connection conn =DbConnection.getConnection();
            PreparedStatement ps = DbConnection.conn.prepareStatement("select * from encrypt where SNo=?");
            ps.setInt(1,sno);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Blob b = rs.getBlob(3);
                byte[] arr = b.getBytes(1,(int)b.length());
                 byte[] decValue = c.doFinal(arr);
                 //String decryptedValue = new String(decValue);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(decValue);    
                JOptionPane.showMessageDialog(null,"Congratulations!! The file has been safely Decrypted and can be viewed at the specified path");
            }
            
            
            else
            {
                JOptionPane.showMessageDialog(null,"Oops!! Wrong Password, The file cannot be decrypted and cannot be viewed");
            }
           
        } catch(Exception e){
            e.printStackTrace();
        }
        
    } 
    
    /*void DecryptFile(){
        String keyHash;
        int key=key();
        int sno=Integer.parseInt(jTextField3.getText());
        
        
        
             
        try {
            File file=new File(jTextField4.getText());
            file.createNewFile();
            //Connection conn =DbConnection.getConnection();
            PreparedStatement ps = DbConnection.conn.prepareStatement("select * from encrypt where SNo=?");
            ps.setInt(1,sno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Blob b = rs.getBlob(3);
                byte[] arr = b.getBytes(1,(int)b.length());
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(arr);
                
            }
            keyHash=getHashInString(Integer.toString(key));
            if( areHashesEqual(file, keyHash))
            {
            FileInputStream obj1 = new FileInputStream(file);
            byte[] data = new byte[obj1.available()];
            for(int i=0; i<128; i++)
            {
                 if(obj1.available()>0)
                 {
                            obj1.read();
                 }
            }
            obj1.read(data);
            int i=0;
            for(byte b:data){
                data[i]=(byte)(b^key);
                i++;
            }
            FileOutputStream obj2 = new FileOutputStream(file);
            obj2.write(data);
            obj2.close();
            obj1.close();
            JOptionPane.showMessageDialog(null,"Congratulations!! The file has been safely Decrypted and can be viewed at the specified path");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Oops!! Wrong Password, The file cannot be decrypted and cannot be viewed");
            }
          } catch(Exception e){
            e.printStackTrace();
        }
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cryptogram");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 5, true));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 33, 636, 118));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SNo", "Files"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, 220));

        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 102, 153));
        jButton4.setText("Back");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 2, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 70, 37));

        jButton2.setFont(new java.awt.Font("MV Boli", 1, 21)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 102));
        jButton2.setText("Decrypt to View");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 3, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 400, 210, 50));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 21)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Enter Password:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 160, 46));
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, 180, 46));

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 21)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Enter SNo:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, 170, 46));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 190, 46));

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 21)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Enter SNo:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, 170, 46));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 21)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Enter Password:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 160, 46));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 21)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Enter Path:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 170, 46));

        jTextField4.setForeground(new java.awt.Color(153, 153, 153));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("C:\\Users\\india\\Desktop\\img.jpg");
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 188, 46));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bg.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -4, 810, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        HomePage obj = new HomePage();
        obj.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here
        DecryptFile();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained
        // TODO add your handling code here:
        if(jTextField4.getText().trim().equals("C:\\Users\\india\\Desktop\\img.jpg"))
        {
            jTextField4.setText("");
        }
        jTextField4.setForeground(Color.BLACK);
    }//GEN-LAST:event_jTextField4FocusGained

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        // TODO add your handling code here:
        if(jTextField4.getText().trim().equals(""))
        {
            jTextField4.setText("C:\\Users\\india\\Desktop\\img.jpg");
            jTextField4.setForeground(new Color(153,153,153));
        }
        
    }//GEN-LAST:event_jTextField4FocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewFile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
