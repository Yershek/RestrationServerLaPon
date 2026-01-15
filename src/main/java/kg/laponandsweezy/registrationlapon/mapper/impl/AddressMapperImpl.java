package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.AddressRequest;
import kg.laponandsweezy.registrationlapon.dto.response.AddressResponse;
import kg.laponandsweezy.registrationlapon.entity.Address;
import kg.laponandsweezy.registrationlapon.mapper.AddressMapper;
import kg.laponandsweezy.registrationlapon.repository.GeographicHierarchyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements AddressMapper {
    GeographicHierarchyRepository geographicHierarchyRepository;

    @Autowired
    public AddressMapperImpl(GeographicHierarchyRepository geographicHierarchyRepository) {
        this.geographicHierarchyRepository = geographicHierarchyRepository;
    }

    @Override
    public Address toEntity(AddressRequest request) {
        if (request == null) {
            return null;
        }
        Address address = new Address();
        address.setCityGeo(geographicHierarchyRepository.findById(request.getCityGeoId()).orElse(null));
        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setApartmentNumber(request.getApartmentNumber());
        address.setPostalCode(request.getPostalCode());
        return address;
    }

    @Override
    public AddressResponse toDto(Address entity) {
        if (entity == null) {
            return null;
        }
        AddressResponse response = new AddressResponse();
        response.setId(entity.getId());
        response.setCityGeoId(entity.getCityGeo() != null ? entity.getCityGeo().getId() : 0);
        response.setStreet(entity.getStreet());
        response.setHouseNumber(entity.getHouseNumber());
        response.setApartmentNumber(entity.getApartmentNumber());
        response.setPostalCode(entity.getPostalCode());
        return response;
    }
}
