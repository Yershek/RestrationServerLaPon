package kg.laponandsweezy.registrationlapon.repository;

import kg.laponandsweezy.registrationlapon.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {
}
