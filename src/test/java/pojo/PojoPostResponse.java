package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PojoPostResponse {

	@JsonProperty("createdAt")
	private  String createdAt;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("job")
	private String job;

	@Override
 	public String toString(){
		return 
			"PojoPostResponse{" +
			"createdAt = '" + createdAt + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",job = '" + job + '\'' + 
			"}";
		}
}