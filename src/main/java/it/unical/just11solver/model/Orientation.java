package it.unical.just11solver.model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("orientation")
public class Orientation {

	@Param(0)
	private String orientation;
	
	public Orientation() {
		// TODO Auto-generated constructor stub
	}
	
	public Orientation(String orientation) {
		setOrientation(orientation);
	}
	
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	public String getOrientation() {
		return orientation;
	}
	
}
