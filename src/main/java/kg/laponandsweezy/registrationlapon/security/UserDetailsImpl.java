package kg.laponandsweezy.registrationlapon.security;

import kg.laponandsweezy.registrationlapon.entity.Citizen;
import kg.laponandsweezy.registrationlapon.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private int id;
    private String personalIdNumber;
    private String password; // Добавлено поле для пароля
    private String lastName;
    private String firstName;
    private String middleName;
    private Date dateOfBirth;
    private Gender gender;
    private String placeOfBirth;
    private String nationality;
    private Timestamp dateCreated;

    public static UserDetailsImpl build(Citizen citizen) {
        return new UserDetailsImpl(
                citizen.getId(),
                citizen.getPersonalIdNumber(),
                citizen.getPassword(), // Передаем пароль
                citizen.getLastName(),
                citizen.getFirstName(),
                citizen.getMiddleName(),
                citizen.getDateOfBirth(),
                citizen.getGender(),
                citizen.getPlaceOfBirth(),
                citizen.getNationality(),
                citizen.getDateCreated()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return personalIdNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
