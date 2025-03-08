package secured;

public class UserDetails {
    private String name;
    private String phone;
    private String carIds;
    private boolean hasLicense;

    public UserDetails(String name, String phone, String carIds, boolean hasLicense) {
        this.name = name;
        this.phone = phone;
        this.carIds = carIds;
        this.hasLicense = hasLicense;
    }

    public String getName() {
        return name != null ? name : "";
    }

    public String getPhone() {
        return phone != null ? phone : "";
    }

    public String getCarIds() {
        return carIds != null ? carIds : "";
    }

    public boolean isHasLicense() {
        return hasLicense;
    }
}