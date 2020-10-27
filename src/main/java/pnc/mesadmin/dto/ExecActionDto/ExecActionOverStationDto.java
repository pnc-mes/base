package pnc.mesadmin.dto.ExecActionDto;

import java.util.List;

public class ExecActionOverStationDto {
    private List<ExecActionResponseDto> responseDtos;

    private String Batch;

    public List<ExecActionResponseDto> getResponseDtos() {
        return responseDtos;
    }

    public void setResponseDtos(List<ExecActionResponseDto> responseDtos) {
        this.responseDtos = responseDtos;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }
}
