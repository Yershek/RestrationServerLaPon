package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.GeographicHierarchyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.GeographicHierarchyResponse;
import java.util.List;

public interface GeographicHierarchyService {
    GeographicHierarchyResponse save(GeographicHierarchyRequest request);
    GeographicHierarchyResponse findById(int id);
    List<GeographicHierarchyResponse> findAll();
    GeographicHierarchyResponse update(int id, GeographicHierarchyRequest request);
    void deleteById(int id);
}
