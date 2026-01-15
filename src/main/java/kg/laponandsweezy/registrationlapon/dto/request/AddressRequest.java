package kg.laponandsweezy.registrationlapon.dto.request;

import lombok.Data;

@Data
public class AddressRequest {
    private int cityGeoId;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String postalCode;
}
