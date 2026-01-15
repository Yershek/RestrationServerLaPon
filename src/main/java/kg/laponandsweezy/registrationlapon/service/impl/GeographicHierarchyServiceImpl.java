package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.GeographicHierarchyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.GeographicHierarchyResponse;
import kg.laponandsweezy.registrationlapon.entity.GeographicHierarchy;
import kg.laponandsweezy.registrationlapon.mapper.GeographicHierarchyMapper;
import kg.laponandsweezy.registrationlapon.repository.GeographicHierarchyRepository;
import kg.laponandsweezy.registrationlapon.service.GeographicHierarchyService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeographicHierarchyServiceImpl implements GeographicHierarchyService {

    private final GeographicHierarchyRepository geographicHierarchyRepository;
    private final GeographicHierarchyMapper geographicHierarchyMapper;

    public GeographicHierarchyServiceImpl(GeographicHierarchyRepository geographicHierarchyRepository,
                                          GeographicHierarchyMapper geographicHierarchyMapper) {
        this.geographicHierarchyRepository = geographicHierarchyRepository;
        this.geographicHierarchyMapper = geographicHierarchyMapper;
    }

    @Override
    public GeographicHierarchyResponse save(GeographicHierarchyRequest request) {
        GeographicHierarchy geographicHierarchy = geographicHierarchyMapper.toEntity(request);
        if (request.getParentId() != null && request.getParentId() != 0) {
            GeographicHierarchy parent = geographicHierarchyRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent GeographicHierarchy not found with id: " + request.getParentId()));
            geographicHierarchy.setParent(parent);
        }

        GeographicHierarchy savedHierarchy = geographicHierarchyRepository.save(geographicHierarchy);
        return geographicHierarchyMapper.toDto(savedHierarchy);
    }

    @Override
    public GeographicHierarchyResponse findById(int id) {
        GeographicHierarchy geographicHierarchy = geographicHierarchyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeographicHierarchy not found with id: " + id));
        return geographicHierarchyMapper.toDto(geographicHierarchy);
    }

    @Override
    public List<GeographicHierarchyResponse> findAll() {
        return geographicHierarchyRepository.findAll().stream()
                .map(geographicHierarchyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GeographicHierarchyResponse update(int id, GeographicHierarchyRequest request) {
        GeographicHierarchy existingHierarchy = geographicHierarchyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeographicHierarchy not found with id: " + id));
        if (request.getParentId() != null && request.getParentId() != 0) {
            GeographicHierarchy parent = geographicHierarchyRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent GeographicHierarchy not found with id: " + request.getParentId()));
            existingHierarchy.setParent(parent);
        } else {
            existingHierarchy.setParent(null);
        }

        existingHierarchy.setNameRu(request.getNameRu());
        existingHierarchy.setLevel(request.getLevel());

        GeographicHierarchy updatedHierarchy = geographicHierarchyRepository.save(existingHierarchy);
        return geographicHierarchyMapper.toDto(updatedHierarchy);
    }

    @Override
    public void deleteById(int id) {
        geographicHierarchyRepository.deleteById(id);
    }
}
