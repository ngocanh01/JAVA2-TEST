import NguyenThiNgocAnh_Student1290586.entities.Manufacture;
import NguyenThiNgocAnh_Student1290586.entities.Moto;
import NguyenThiNgocAnh_Student1290586.models.ManufactureModel;
import NguyenThiNgocAnh_Student1290586.models.MotoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class NguyenThiNgocAnh_Student1290586 {
    private JButton jButtonAdd;
    private JPanel panelMain;
    private JTextField textFieldName;
    private JTextField textFieldQuantity;
    private JTextField textFieldCode;
    private JComboBox comboBoxMaker;
    private JTable tableList;
    private JButton jbuttonDelete;
    private JButton jButtonSave;
    private JButton jButtonExit;
    private JButton jButtonClear;

    public NguyenThiNgocAnh_Student1290586(){
        //set data to jcombobox
        List<Manufacture> manufactures = new ArrayList<Manufacture>();
        manufactures = ManufactureModel.findAll();
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        for (Manufacture manufacture: manufactures
        ) {
            comboBoxModel.addElement(manufacture.getName());
        }
        comboBoxMaker.setModel(comboBoxModel);
        //set data to jtable
        loadData();
        //set action of add button
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Moto moto = new Moto();
                if(textFieldCode.getText().toString().trim().isEmpty()  ||
                        textFieldName.getText().toString().trim().isEmpty() ||
                        textFieldQuantity.getText().toString().trim().isEmpty()  ){
                    JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ!");
                }else {
                    try {
                        moto.setCode(Integer.parseInt(textFieldCode.getText()));
                        moto.setName(textFieldName.getText());
                        moto.setQuantity(Integer.parseInt(textFieldQuantity.getText()));
                        moto.setManu_name(comboBoxMaker.getSelectedItem().toString());

                        if (MotoModel.create(moto)){
                            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công!");
                            loadData();
                            clearData();
                        } else {
                            JOptionPane.showMessageDialog(null, "Thất bại");
                        }
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ!");
                    }

                }

            }
        });

        //set action of clear button
        jButtonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearData();
            }
        });

        //set delete button
        jbuttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteModel();
            }
        });
        //set update button
        jButtonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateModel();
            }
        });

        //set exit button
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        // set click event
        tableList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showModel();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MotoManage");
        frame.setContentPane(new NguyenThiNgocAnh_Student1290586().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void exit(){
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát không?");
        if(input == 0){
            System.exit(0);
        }
    }

    private void deleteModel(){
        if(tableList.getSelectedRow() != -1)
        {
            int column = 0;
            int row = tableList.getSelectedRow();
            String value = tableList.getModel().getValueAt(row, column).toString();
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?");
            if (value != null && input == 0){
                if (MotoModel.deleteByCode(Integer.parseInt(value))){
                    JOptionPane.showMessageDialog(null, "Deleted successfully");
                    loadData();
                    clearData();
                } else {
                    JOptionPane.showMessageDialog(null, "Deleted failed");
                }
            }
        }
    }

    private void updateModel(){
        Moto moto = new Moto();
        moto.setCode(Integer.parseInt(textFieldCode.getText()));
        moto.setName(textFieldName.getText());
        moto.setQuantity(Integer.parseInt(textFieldQuantity.getText()));
        moto.setManu_name(comboBoxMaker.getSelectedItem().toString());

        if (MotoModel.update(moto)){
            JOptionPane.showMessageDialog(null, "Success");
            loadData();
            clearData();
        } else {
            JOptionPane.showMessageDialog(null, "failed");
        }
    }

    private void showModel(){
        if(tableList.getSelectedRow() != -1)
        {

            int row = tableList.getSelectedRow();
            String code = tableList.getModel().getValueAt(row, 0).toString();
            String name = tableList.getModel().getValueAt(row, 1).toString();
            String manu_id = tableList.getModel().getValueAt(row, 2).toString();
            String quantity = tableList.getModel().getValueAt(row, 3).toString();

            textFieldCode.setText(code);
            textFieldCode.disable();
            textFieldName.setText(name);
            textFieldQuantity.setText(quantity);
            comboBoxMaker.setSelectedItem(Integer.parseInt(manu_id));
        }
    }

    private void clearData(){
        textFieldCode.setText("");
        textFieldCode.setEnabled(true);
        textFieldCode.requestFocusInWindow();
        textFieldName.setText("");
        textFieldQuantity.setText("");
        comboBoxMaker.setSelectedIndex(0);
    }

    private void loadData(){
        List<Moto> motos = new ArrayList<Moto>();
        motos = MotoModel.findAll();
        DefaultTableModel models = new DefaultTableModel();
        models.addColumn("Mã xe");
        models.addColumn("Tên xe");
        models.addColumn("Nhà SX");
        models.addColumn("Số lượng");

        for (Moto moto: motos) {
            models.addRow(new Object[]{
                    moto.getCode(),
                    moto.getName(),
                    moto.getManu_name(),
                    moto.getQuantity()
            });
        }
        tableList.setModel(models);
        tableList.setDefaultEditor(Object.class, null);
        tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}