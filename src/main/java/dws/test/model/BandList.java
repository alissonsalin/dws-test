package dws.test.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BandList {
	private List<Band> bands;
	
	public BandList() {
		bands = new ArrayList<Band>();
	}
}
