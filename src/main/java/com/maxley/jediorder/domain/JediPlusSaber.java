package com.maxley.jediorder.domain;

import java.util.List;

public class JediPlusSaber {
	
	private Jedi jedi;
	private List<Saber> saber;
	
	public JediPlusSaber() {}

	public JediPlusSaber(Jedi jedi, List<Saber> saber) {
		super();
		this.jedi = jedi;
		this.saber = saber;
	}

	public Jedi getJedi() {
		return jedi;
	}

	public void setJedi(Jedi jedi) {
		this.jedi = jedi;
	}

	public List<Saber> getSaber() {
		return saber;
	}

	public void setSaber(List<Saber> saber) {
		this.saber = saber;
	}

	
}
