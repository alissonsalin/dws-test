
package dws.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Track {

    @JsonProperty("name")
    public String name;
    @JsonProperty("duration")
    public Integer duration;
    @JsonProperty("id")
    public String id;

}
