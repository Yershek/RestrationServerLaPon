package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.AddressRequest;
import kg.laponandsweezy.registrationlapon.dto.response.AddressResponse;
import kg.laponandsweezy.registrationlapon.entity.Address;
import kg.laponandsweezy.registrationlapon.entity.GeographicHierarchy;
import kg.laponandsweezy.registrationlapon.mapper.AddressMapper;
import kg.laponandsweezy.registrationlapon.repository.AddressRepository;
import kg.laponandsweezy.registrationlapon.repository.GeographicHierarchyRepository;
import kg.laponandsweezy.registrationlapon.service.AddressService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final GeographicHierarchyRepository geographicHierarchyRepository;

    public AddressServiceImpl(AddressRepository addressRepository,
                              AddressMapper addressMapper,
                              GeographicHierarchyRepository geographicHierarchyRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.geographicHierarchyRepository = geographicHierarchyRepository;
    }

    @Override
    public AddressResponse save(AddressRequest request) {
        Address address = addressMapper.toEntity(request);
        GeographicHierarchy cityGeo = geographicHierarchyRepository.findById(request.getCityGeoId())
                .orElseThrow(() -> new RuntimeException("City GeographicHierarchy not found with id: " + request.getCityGeoId()));
        address.setCityGeo(cityGeo);

        Address savedAddress = addressRepository.save(address);
        return addressMapper.toDto(savedAddress);
    }

    @Override
    public AddressResponse findById(int id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        return addressMapper.toDto(address);
    }

    @Override
    public List<AddressResponse> findAll() {
        return addressRepository.findAll().stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse update(int id, AddressRequest request) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        GeographicHierarchy cityGeo = geographicHierarchyRepository.findById(request.getCityGeoId())
                .orElseThrow(() -> new RuntimeException("City GeographicHierarchy not found with id: " + request.getCityGeoId()));
        existingAddress.setCityGeo(cityGeo);

        existingAddress.setStreet(request.getStreet());
        existingAddress.setHouseNumber(request.getHouseNumber());
        existingAddress.setApartmentNumber(request.getApartmentNumber());
        existingAddress.setPostalCode(request.getPostalCode());

        Address updatedAddress = addressRepository.save(existingAddress);
        return addressMapper.toDto(updatedAddress);
    }

    @Override
    public void deleteById(int id) {
        addressRepository.deleteById(id);
    }
}
