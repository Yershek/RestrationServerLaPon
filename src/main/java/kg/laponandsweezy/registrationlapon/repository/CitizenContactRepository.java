package kg.laponandsweezy.registrationlapon.repository;

import kg.laponandsweezy.registrationlapon.entity.CitizenContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenContactRepository extends JpaRepository<CitizenContact, Integer> {
}
