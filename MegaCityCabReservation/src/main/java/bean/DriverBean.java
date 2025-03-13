package bean;

public class DriverBean {
    private int driverId;
    private String username;
    private String nic;
    private String address;
    private String phone;
    private String licenceNumber;
    private String email;
    private String carType;
    private String password;
    private boolean availability;

    // Constructors
    public DriverBean() {}

    public DriverBean(String username, String nic, String address, String phone, String licenceNumber, String email, String carType, String password) {
        this.username = username;
        this.nic = nic;
        this.address = address;
        this.phone = phone;
        this.licenceNumber = licenceNumber;
        this.email = email;
        this.carType = carType;
        this.password = password;
        this.availability = true; // Default to available
    }

    // Getters and Setters
    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getNic() { return nic; }
    public void setNic(String nic) { this.nic = nic; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLicenceNumber() { return licenceNumber; }
    public void setLicenceNumber(String licenceNumber) { this.licenceNumber = licenceNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCarType() { return carType; }
    public void setCarType(String carType) { this.carType = carType; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }
}