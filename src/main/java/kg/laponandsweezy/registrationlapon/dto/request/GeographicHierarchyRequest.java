package kg.laponandsweezy.registrationlapon.dto.request;

import kg.laponandsweezy.registrationlapon.enums.Level;
import lombok.Data;

@Data
public class GeographicHierarchyRequest {
    private Integer parentId;
    private String nameRu;
    private Level level;
}
