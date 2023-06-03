/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olah_DB;

import entitas.ent_gudang;
import intface.intface_gudang;
import java.sql.Statement;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.List;
import koneksi_db.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krisn
 */
public class olah_gudang extends UnicastRemoteObject implements intface_gudang {

    public olah_gudang() throws RemoteException {
    }

    @Override
    public ent_gudang insert(ent_gudang gudang) throws RemoteException {
        System.out.println("[Server] Client memanggil fungsi tambah");
        try {
            PreparedStatement ps = koneksi.getCekkoneksi().prepareStatement("insert into gudang (id" + ",nama,harga,jumlah,tgl_masuk)value(?,?,?,?,?)");
            ps.setInt(1, gudang.getId());
            ps.setString(2, gudang.getNama());
            ps.setString(3, gudang.getHarga());
            ps.setString(4, gudang.getJumlah());
            ps.setDate(5, new Date(gudang.getTgl_masuk().getTime()));
            ps.executeUpdate();
            
//            ResultSet st = ps.getGeneratedKeys();
//            if(st.next()){
//                gudang.setId(st.getInt(1));
//            }
            System.out.println("\t "+ps+"\n\t Permintaan tambah berhasil diproses.\n");
            return gudang;
        } catch (SQLException ex) {
            Logger.getLogger(olah_gudang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void delete(int id) throws RemoteException {
        System.out.println("[Server] Client memanggil fungsi hapus");
        try {
            PreparedStatement ps = koneksi.getCekkoneksi().prepareStatement("delete from gudang where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("\t "+ps+"\n\t Permintaan hapus berhasil diproses.\n");
        } catch (SQLException e) {
        }
    }

    @Override
    public void update(ent_gudang gudang) throws RemoteException {
        System.out.println("[Server] Client memanggil fungsi ubah");
        try {
            PreparedStatement ps = koneksi.getCekkoneksi().prepareStatement("update gudang set nama=?,"+ "harga=?,jumlah=?,tgl_masuk=? where id=?");
            ps.setString(1, gudang.getNama());
            ps.setString(2, gudang.getHarga());
            ps.setString(3, gudang.getJumlah());
            ps.setDate(4, new Date(gudang.getTgl_masuk().getTime()));
            ps.setInt(5, gudang.getId());
            ps.executeUpdate();
            System.out.println("\t "+ps+"\n\t Permintaan Ubah berhasil diproses.\n");
        } catch (SQLException e) {
        }
    }

    
    
    @Override
    public List<ent_gudang> getALL() throws RemoteException {
        System.out.println("[Server] Client memanggil data pada database");
        try {
            Statement st = koneksi.getCekkoneksi().createStatement();
            ResultSet rs=st.executeQuery("select * from gudang");
            List<ent_gudang> ent = new ArrayList<ent_gudang>();
            while(rs.next()){
                ent_gudang data = new ent_gudang();
                data.setId(rs.getInt("id"));
                data.setNama(rs.getString("nama"));
                data.setHarga(rs.getString("harga"));
                data.setJumlah(rs.getString("jumlah"));
                data.setTgl_masuk(rs.getDate("tgl_masuk"));
                ent.add(data);
            }
            System.out.println("\t Data berhasil ditampilkan.\n");
            return ent;
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public ArrayList cari(String nama) throws RemoteException {
        ArrayList gudang = new ArrayList();
        try {
            Statement st = koneksi.getCekkoneksi().createStatement();
            ResultSet rs = st.executeQuery("select * from gudang where nama=" +nama+"");
            while(rs.next()){
                gudang.add("id"+rs.getInt("id"));
                gudang.add(rs.getString("nama"));
                gudang.add(rs.getString("harga"));
                gudang.add(rs.getString("jumlah"));
                gudang.add(rs.getDate("tgl_masuk"));
            }
        } catch (Exception e) {
        }
        return null;
    }

}
