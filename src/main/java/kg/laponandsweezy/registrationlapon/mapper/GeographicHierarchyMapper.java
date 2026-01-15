package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.GeographicHierarchyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.GeographicHierarchyResponse;
import kg.laponandsweezy.registrationlapon.entity.GeographicHierarchy;

public interface GeographicHierarchyMapper {
    GeographicHierarchy toEntity(GeographicHierarchyRequest request);
    GeographicHierarchyResponse toDto(GeographicHierarchy entity);
}
