package com.maxley.jediorder.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Jedi implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String name;
	
	@NotNull
	private Double midiClorian;
	
	//@JsonManagedReference
	//@OneToMany(mappedBy = "jedi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private List<Saber> saber = new ArrayList<>();
	
	public Jedi() { }

	public Jedi(Long id, String name, Double midiClorian, List<Saber> saber) {
		super();
		this.id = id;
		this.name = name;
		this.midiClorian = midiClorian;
		//this.saber = saber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMidiClorian() {
		return midiClorian;
	}

	public void setMidiClorian(Double midiClorian) {
		this.midiClorian = midiClorian;
	}

	/*public List<Saber> getSaber() {
		return saber;
	}

	public void setSaber(List<Saber> saber) {
		this.saber = saber;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jedi other = (Jedi) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
