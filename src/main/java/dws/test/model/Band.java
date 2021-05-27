
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
public class Band {

    @JsonProperty("name")
    public String name;
    @JsonProperty("image")
    public String image;
    @JsonProperty("genre")
    public String genre;
    @JsonProperty("biography")
    public String biography;
    @JsonProperty("numPlays")
    public Integer numPlays;
    @JsonProperty("albums")
    public List<String> albums = new ArrayList<String>();
    @JsonProperty("id")
    public String id;
    @JsonProperty("albumList")
    public List<List<Album>> albumList = new ArrayList<List<Album>>();

}
