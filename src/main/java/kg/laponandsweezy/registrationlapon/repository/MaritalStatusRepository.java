package kg.laponandsweezy.registrationlapon.repository;

import kg.laponandsweezy.registrationlapon.entity.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Integer> {
}
