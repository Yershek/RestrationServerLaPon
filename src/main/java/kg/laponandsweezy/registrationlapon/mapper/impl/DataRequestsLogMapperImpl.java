package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.DataRequestsLogRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DataRequestsLogResponse;
import kg.laponandsweezy.registrationlapon.entity.DataRequestsLog;
import kg.laponandsweezy.registrationlapon.mapper.DataRequestsLogMapper;
import org.springframework.stereotype.Component;

@Component
public class DataRequestsLogMapperImpl implements DataRequestsLogMapper {

    @Override
    public DataRequestsLog toEntity(DataRequestsLogRequest request) {
        if (request == null) {
            return null;
        }
        DataRequestsLog dataRequestsLog = new DataRequestsLog();
        dataRequestsLog.setDataFieldsRequested(request.getDataFieldsRequested());
        dataRequestsLog.setPurposeCode(request.getPurposeCode());
        return dataRequestsLog;
    }

    @Override
    public DataRequestsLogResponse toDto(DataRequestsLog entity) {
        if (entity == null) {
            return null;
        }
        DataRequestsLogResponse response = new DataRequestsLogResponse();
        response.setId(entity.getId());
        response.setRequestingAgencyId(entity.getRequestingAgency() != null ? entity.getRequestingAgency().getId() : 0);
        response.setCitizenId(entity.getCitizen() != null ? entity.getCitizen().getId() : 0);
        response.setRequestTimestamp(entity.getRequestTimestamp());
        response.setDataFieldsRequested(entity.getDataFieldsRequested());
        response.setPurposeCode(entity.getPurposeCode());
        return response;
    }
}
