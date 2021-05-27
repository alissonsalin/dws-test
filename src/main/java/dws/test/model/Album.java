
package dws.test.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    @JsonProperty("name")
    public String name;
    @JsonProperty("image")
    public String image;
    @JsonProperty("releasedDate")
    public String releasedDate;
    @JsonProperty("band")
    public String band;
    @JsonProperty("tracks")
    public List<Track> tracks = new ArrayList<Track>();
    @JsonProperty("id")
    public String id;

}
