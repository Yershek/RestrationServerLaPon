package kg.laponandsweezy.registrationlapon.dto.response;

import kg.laponandsweezy.registrationlapon.enums.Level;
import lombok.Data;

@Data
public class GeographicHierarchyResponse {
    private int id;
    private Integer parentId;
    private String nameRu;
    private Level level;
}
