package NguyenThiNgocAnh_Student1290586.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import NguyenThiNgocAnh_Student1290586.entities.Manufacture;
import NguyenThiNgocAnh_Student1290586.configs.ConnectDB;
import NguyenThiNgocAnh_Student1290586.entities.Moto;

public class ManufactureModel {
    public static List<Manufacture> findAll() {
        List<Manufacture> manufactures_list = new ArrayList<Manufacture>();
        try {
            PreparedStatement prepareStatement = ConnectDB.getConnection().prepareStatement("SELECT * FROM `manufacture`");
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                Manufacture manufacture = new Manufacture();
                manufacture.setId(resultSet.getInt("id"));
                manufacture.setName(resultSet.getString("name"));
                manufactures_list.add(manufacture);
            }

            return manufactures_list;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    public static Moto findById(int id) {
        try {
            PreparedStatement prepareStatement = ConnectDB.getConnection().prepareStatement("SELECT * FROM `moto` WHERE id = ?");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();


            Moto moto = new Moto();
            moto.setId(resultSet.getInt("id"));
            moto.setName(resultSet.getString("name"));
            moto.setCode(resultSet.getInt("code"));
            moto.setManu_name(resultSet.getString("manu_name"));
            moto.setQuantity(resultSet.getInt("quantity"));

            return moto;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
}
