package in.GabbarElaka.management.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HealthResponse {
    private String serviceName;
    private String serviceDescription;
    private String serviceStatus;
}
