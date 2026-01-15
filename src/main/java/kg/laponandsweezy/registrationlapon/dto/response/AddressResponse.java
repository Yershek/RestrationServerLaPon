package kg.laponandsweezy.registrationlapon.dto.response;

import lombok.Data;

@Data
public class AddressResponse {
    private int id;
    private int cityGeoId;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String postalCode;
}
