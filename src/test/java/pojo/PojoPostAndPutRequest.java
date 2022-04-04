package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jemos.podam.common.PodamStringValue;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PojoPostAndPutRequest {

    @JsonProperty("name")
    @PodamStringValue(strValue = "morpheus1")
    private String name;

    @JsonProperty("job")
    @PodamStringValue(strValue = "leader1")
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