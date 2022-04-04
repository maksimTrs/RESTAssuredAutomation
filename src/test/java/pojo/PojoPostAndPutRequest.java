package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PojoPostAndPutRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("job")
    private String job;


    @Override
    public String toString() {
        return
                "PojoPostAndPutRequest{" +
                        "name = '" + name + '\'' +
                        ",job = '" + job + '\'' +
                        "}";
    }
}