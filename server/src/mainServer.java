import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import formServer.menu_server;
import olah_DB.olah_gudang;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author krisn
 */
public class mainServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws RemoteException, NotBoundException{
        // TODO code application logic here
        Registry reg = LocateRegistry.createRegistry(1212);
        olah_gudang olah = new olah_gudang();

        reg.rebind("gudang", olah);
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            // TODO: handle exception
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                menu_server ms = new menu_server();
                ms.setLocationRelativeTo(ms);
                ms.setVisible(true);
                System.out.println("[Server] Server Berjalan pada port 1212");
            }
        });
    }
    
}
