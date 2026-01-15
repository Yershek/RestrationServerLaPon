package kg.laponandsweezy.registrationlapon.repository;

import kg.laponandsweezy.registrationlapon.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
