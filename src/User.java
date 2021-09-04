
public class User {
	
	private String name;
	private String address;
	private String gender;
	private Integer age;
	private String password;
	
//	Generate Setter Getter
//	Alt + Shift + S -> R
	
//	Generate Constructor
//	Alt + Shift + S -> O

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setProfile(String password) {
		this.password = password;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String address, String gender, Integer age, String password) {
		super();
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.age = age;
		this.password = password;
	}

}
