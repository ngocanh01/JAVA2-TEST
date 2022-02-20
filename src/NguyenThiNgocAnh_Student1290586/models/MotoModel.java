package NguyenThiNgocAnh_Student1290586.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import NguyenThiNgocAnh_Student1290586.entities.Moto;
import NguyenThiNgocAnh_Student1290586.configs.ConnectDB;

public class MotoModel {
    public static List<Moto> findAll() {
        List<Moto> motos_list = new ArrayList<Moto>();
        try {
            PreparedStatement prepareStatement = ConnectDB.getConnection().prepareStatement("SELECT * FROM `moto`");
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                Moto moto = new Moto();
                moto.setId(resultSet.getInt("id"));
                moto.setName(resultSet.getString("name"));
                moto.setCode(resultSet.getInt("code"));
                moto.setManu_name(resultSet.getString("manu_name"));
                moto.setQuantity(resultSet.getInt("quantity"));
                motos_list.add(moto);
            }

            return motos_list;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public static boolean create(Moto moto) {
        try {
            PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
                    "INSERT INTO `moto`(`code`, `name`, `manu_name`, `quantity`) " + "VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, moto.getCode());
            preparedStatement.setString(2, moto.getName());
            preparedStatement.setString(3, moto.getManu_name());
            preparedStatement.setInt(4, moto.getQuantity());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public static boolean update(Moto moto) {
        try {
            PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
                    "UPDATE `moto` SET `name`=?,`manu_name`=?," +
                            "`quantity`=? WHERE code = ?");
            preparedStatement.setString(1, moto.getName());
            preparedStatement.setString(2, moto.getManu_name());
            preparedStatement.setInt(3, moto.getQuantity());
            preparedStatement.setInt(4, moto.getCode());

            int result = preparedStatement.executeUpdate();
            if (result <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteByCode(int code) {
        try {
            PreparedStatement preparedStatement = ConnectDB.getConnection()
                    .prepareStatement("DELETE FROM `moto` WHERE code = ?");
            preparedStatement.setInt(1, code);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }
}
