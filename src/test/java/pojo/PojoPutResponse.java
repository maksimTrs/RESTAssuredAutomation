package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PojoPutResponse {

	@JsonProperty("name")
	private String name;

	@JsonProperty("job")
	private String job;

	@JsonProperty("updatedAt")
	private String updatedAt;

	@Override
 	public String toString(){
		return 
			"PojoPutResponse{" +
			"name = '" + name + '\'' + 
			",job = '" + job + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}