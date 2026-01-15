package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.AgencyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.AgencyResponse;
import kg.laponandsweezy.registrationlapon.entity.Agency;
import kg.laponandsweezy.registrationlapon.mapper.AgencyMapper;
import org.springframework.stereotype.Component;

@Component
public class AgencyMapperImpl implements AgencyMapper {

    @Override
    public Agency toEntity(AgencyRequest request) {
        if (request == null) {
            return null;
        }
        Agency agency = new Agency();
        agency.setName(request.getName());
        agency.setShortCode(request.getShortCode());
        return agency;
    }

    @Override
    public AgencyResponse toDto(Agency entity) {
        if (entity == null) {
            return null;
        }
        AgencyResponse response = new AgencyResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setShortCode(entity.getShortCode());
        return response;
    }
}
