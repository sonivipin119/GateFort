package in.GabbarElaka.management.Service;

import in.GabbarElaka.management.ResponseDto.HealthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthService {

    private final JdbcTemplate jdbcTemplate;

    public String getHealth(){
        String DatabaseStatus = "DOWN";

        try{
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            if(Integer.valueOf(1).equals(result)){
                DatabaseStatus = "UP";
            }
        }catch (Exception e){
            System.out.println("Mysql Health check failed " + e.getMessage());
        }
        return DatabaseStatus;
    }
}
