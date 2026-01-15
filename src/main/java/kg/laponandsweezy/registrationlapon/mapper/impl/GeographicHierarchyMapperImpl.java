package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.GeographicHierarchyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.GeographicHierarchyResponse;
import kg.laponandsweezy.registrationlapon.entity.GeographicHierarchy;
import kg.laponandsweezy.registrationlapon.mapper.GeographicHierarchyMapper;
import kg.laponandsweezy.registrationlapon.repository.GeographicHierarchyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeographicHierarchyMapperImpl implements GeographicHierarchyMapper {

    GeographicHierarchyRepository geographicHierarchyRepository;

    @Autowired
    public GeographicHierarchyMapperImpl(GeographicHierarchyRepository geographicHierarchyRepository) {
        this.geographicHierarchyRepository = geographicHierarchyRepository;
    }

    @Override
    public GeographicHierarchy toEntity(GeographicHierarchyRequest request) {
        if (request == null) {
            return null;
        }
        GeographicHierarchy geographicHierarchy = new GeographicHierarchy();
        if (request.getParentId() != null) {
             geographicHierarchy.setParent(geographicHierarchyRepository.findById(request.getParentId()).orElse(null));
        }
        geographicHierarchy.setNameRu(request.getNameRu());
        geographicHierarchy.setLevel(request.getLevel());
        return geographicHierarchy;
    }

    @Override
    public GeographicHierarchyResponse toDto(GeographicHierarchy entity) {
        if (entity == null) {
            return null;
        }
        GeographicHierarchyResponse response = new GeographicHierarchyResponse();
        response.setId(entity.getId());
        response.setParentId(entity.getParent() != null ? entity.getParent().getId() : null);
        response.setNameRu(entity.getNameRu());
        response.setLevel(entity.getLevel());
        return response;
    }
}
