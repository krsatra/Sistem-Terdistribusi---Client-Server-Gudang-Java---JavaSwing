/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table_model;

import entitas.ent_gudang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author krisn
 */
public class model_gudang extends AbstractTableModel{
    private List<ent_gudang> data = new ArrayList<ent_gudang>();
    public model_gudang(){
    }
    
    public ent_gudang get(int r){
        return data.get(r);
    }
    
    public void insert (ent_gudang ent){
        data.add(ent);
        fireTableDataChanged();
    }
    
    public void update(int row, ent_gudang ent){
        data.set(row, ent);
        fireTableDataChanged();
    }
    
    public void delete(int row){
        data.remove(row);
        fireTableDataChanged();
    }
    
    public void setdata(List<ent_gudang>data){
        this.data = data;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0 : return data.get(rowIndex).getId();
            case 1 : return data.get(rowIndex).getNama();
            case 2 : return data.get(rowIndex).getHarga();
            case 3 : return data.get(rowIndex).getJumlah();
            case 4 : return data.get(rowIndex).getTgl_masuk();
            default : return null;
        }
    }
    
    
    public String getColumnName(int column){
        switch(column){
            case 0 : return "id Barang";
            case 1 : return "nama Barang";
            case 2 : return "harga";
            case 3 : return "jumlah";
            case 4 : return "tgl Masuk";
            default : return null;
        }
    }
    
}
