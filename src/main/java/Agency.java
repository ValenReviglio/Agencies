public class Agency implements Comparable<Agency> {
    private Address address;
    private Integer agency_code;
    private String correspondent_id;
    private String description;
    private String disabled;
    private Double distance;
    private String id;
    private String paymeny_method_id;
    private String phone;
    private String site_id;
    private String terminal;
    public static Criterio criterio;

    public enum Criterio {
        ADRESS_LINE, AGENCY_CODE, DISTANCE
    }

    public Agency(Address address, Integer agency_code, String correspondent_id, String description, String disabled, Double distance, String id, String paymeny_method_id, String phone, String site_id, String terminal) {
        this.address = address;
        this.agency_code = agency_code;
        this.correspondent_id = correspondent_id;
        this.description = description;
        this.disabled = disabled;
        this.distance = distance;
        this.id = id;
        this.paymeny_method_id = paymeny_method_id;
        this.phone = phone;
        this.site_id = site_id;
        this.terminal = terminal;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getAgency_code() {
        return agency_code;
    }

    public void setAgency_code(Integer agency_code) {
        this.agency_code = agency_code;
    }

    public String getCorrespondent_id() {
        return correspondent_id;
    }

    public void setCorrespondent_id(String correspondent_id) {
        this.correspondent_id = correspondent_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymeny_method_id() {
        return paymeny_method_id;
    }

    public void setPaymeny_method_id(String paymeny_method_id) {
        this.paymeny_method_id = paymeny_method_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "address=" + address +
                ", agency_code='" + agency_code + '\'' +
                ", correspondent_id='" + correspondent_id + '\'' +
                ", description='" + description + '\'' +
                ", disabled='" + disabled + '\'' +
                ", distance='" + distance + '\'' +
                ", id='" + id + '\'' +
                ", paymeny_method_id='" + paymeny_method_id + '\'' +
                ", phone='" + phone + '\'' +
                ", site_id='" + site_id + '\'' +
                ", terminal='" + terminal + '\'' +
                '}';
    }

    @Override
    public int compareTo(Agency o) {
        switch (criterio) {
            case ADRESS_LINE:
                return this.getAddress().getAddress_line().compareTo(o.getAddress().getAddress_line());
            case AGENCY_CODE:
                return this.getAgency_code().compareTo(o.getAgency_code());
            case DISTANCE:
                return this.getDistance().compareTo(o.getDistance());
            default:
                return -1;
        }
    }
}
