package continuitE;

public class FilmInfo {
	private String strTitle = "dftTitle";
	private String strDirector = "dftDirector";
	private String strProductionCompany = "dftProdCompany";
	private String strDOP = "dftDOP";
	public String getStrTitle() {
		return strTitle;
	}
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	public String getStrDirector() {
		return strDirector;
	}
	public void setStrDirector(String strDirector) {
		this.strDirector = strDirector;
	}
	public String getStrProductionCompany() {
		return strProductionCompany;
	}
	public void setStrProductionCompany(String strProductionCompany) {
		this.strProductionCompany = strProductionCompany;
	}
	public String getStrDOP() {
		return strDOP;
	}
	public void setStrDOP(String strDOP) {
		this.strDOP = strDOP;
	}
	public FilmInfo(){
		this.strTitle = "dftTitle";
		this.strDirector = "dftDirector";
		this.strProductionCompany = "dftProdCompany";
		this.strDOP = "dftDOP";
	}
}
