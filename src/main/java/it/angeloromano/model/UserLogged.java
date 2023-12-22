package it.angeloromano.model;

public class UserLogged {
	private Long id;
	private String name;
	private String email;
	private Boolean isAdmin;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public UserLogged(User u) {
		this.email = u.getEmail();
		this.id = u.getId();
		this.name = u.getName();
		this.isAdmin = u.isAdmin();
	}

}
