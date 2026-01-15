package kg.laponandsweezy.registrationlapon.repository;

import kg.laponandsweezy.registrationlapon.entity.GeographicHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeographicHierarchyRepository extends JpaRepository<GeographicHierarchy, Integer> {
}
