public class School {
	String SchoolName;
	String SchoolYear;

	public School(String names, String years){
		this.SchoolName = names;
		this.SchoolYear = years;
	}

	public School() {

	}

	public String getName()	 {
		return SchoolName;
	}

	public String getYear()	 {
		return SchoolYear;
	}

	public void setName(String names)	{
		this.SchoolName = names;
	}

	public void setYear(String years)	{
		this.SchoolYear = years;
	}

	public String toString()	{
		String s = SchoolName + "," + "Year:" + SchoolYear;
		return s;
	}
}