package NguyenThiNgocAnh_Student1290586.entities;

public class Moto {
    private int id;
    private int code;
    private String name;
    private String manu_name;
    private int quantity;

    public Moto() {
    }

    public Moto(int id, int code, String name, String manu_name, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.manu_name = manu_name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManu_name() {
        return manu_name;
    }

    public void setManu_name(String manu_name) {
        this.manu_name = manu_name;
    }
}
