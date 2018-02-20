package io.github.girirajvyas.heroes.rest;

public class Hero {
	public Hero() {
		super();
	}

	private Integer id;
	private String name;
	public Hero(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + "]";
	}

	
}
