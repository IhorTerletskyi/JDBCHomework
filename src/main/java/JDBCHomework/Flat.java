package JDBCHomework;

public class Flat {
    @Id
    private int id;
    private String district;
    private String address;
    private int square;
    private int rooms;
    private int price;

    public Flat(int id, String district, String address, int square, int rooms, int price) {
        this.id = id;
        this.district = district;
        this.address = address;
        this.square = square;
        this.rooms = rooms;
        this.price = price;
    }

    public Flat() {
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
