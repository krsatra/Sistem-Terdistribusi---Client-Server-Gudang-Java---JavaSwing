
/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
*/
import intface.intface_gudang;
import user_intface.form_gudang;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.rmi.NotBoundException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author krisn
 */
public class mainClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
        // TODO code application logic here
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1212);
            final intface_gudang ingudang = (intface_gudang) reg.lookup("gudang");
            try {
                UIManager.setLookAndFeel(new NimbusLookAndFeel());
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(mainClient.class.getName());
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    form_gudang fgudang = new form_gudang(ingudang);
                    fgudang.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    fgudang.setVisible(true);
                    System.out.println("Tersambung Server");
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak Tersambung ke Server", "Periksa Jaringan",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

}
