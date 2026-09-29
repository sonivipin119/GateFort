package in.GabbarElaka.management.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HealthResponse {
    private String serviceStatus;
    private String dbStatus;
    private LocalDateTime timestamp;
}
