/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package intface;
import entitas.ent_gudang;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author krisn
 */
public interface intface_gudang extends Remote{
    ent_gudang insert(ent_gudang gudang) throws RemoteException;
    void delete(int id) throws RemoteException;
    void update(ent_gudang gudang)throws RemoteException;
    public ArrayList cari(String nama)throws RemoteException;
    List<ent_gudang>getALL()throws RemoteException;
}
