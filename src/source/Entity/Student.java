package source.Entity;

public class Student {
	private String Sno;
	private String Clno;
	private String Sname;
	private int Ssex;
	private int Sage;
	private String Shome;
	private int Scredit;
	private String Spwd;
	private String Clname;
	
	@Override
	public String toString() {
		return "Student [Sno=" + Sno + ", Clno=" + Clno + ", Sname=" + Sname + ", Ssex=" + Ssex + ", Sage=" + Sage
				+ ", Shome=" + Shome + ", Scredit=" + Scredit + ", Spwd=" + Spwd + ", Clname=" + Clname + "]";
	}

	public String getSno() {
		return Sno;
	}
	
	public void setSno(String sno) {
		Sno = sno;
	}
	
	public String getClno() {
		return Clno;
	}
	
	public void setClno(String clno) {
		Clno = clno;
	}
	
	public String getSname() {
		return Sname;
	}
	
	public void setSname(String sname) {
		Sname = sname;
	}
	
	public int getSsex() {
		return Ssex;
	}
	
	public void setSsex(int ssex) {
		Ssex = ssex;
	}
	
	public int getSage() {
		return Sage;
	}
	
	public void setSage(int sage) {
		Sage = sage;
	}
	
	public String getShome() {
		return Shome;
	}
	
	public void setShome(String shome) {
		Shome = shome;
	}
	
	public int getScredit() {
		return Scredit;
	}
	
	public void setScredit(int scredit) {
		Scredit = scredit;
	}
	
	public String getSpwd() {
		return Spwd;
	}
	
	public void setSpwd(String spwd) {
		Spwd = spwd;
	}

	public String getClname() {
		return Clname;
	}

	public void setClname(String clname) {
		Clname = clname;
	}
	
}
