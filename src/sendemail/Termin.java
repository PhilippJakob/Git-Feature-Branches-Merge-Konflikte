package sendemail;
import java.time.LocalDate;
import java.time.LocalTime;

public class Termin 
{
	private int terminID;
	private int personID;
	private LocalDate terminStartDatum;
	private LocalDate terminEndeDatum;
	private LocalTime terminZeit;
	private LocalTime terminZeitBis;
	private int terminRaum;
	private String terminInfo;
	private int terminPrivat;
	private String terminPrivatInfo;
	private String terminFarbe;
	
	public int getTerminID() {
		return terminID;
	}
	public void setTerminID(int terminID) {
		this.terminID = terminID;
	}
	public int getpersonID() {
		return personID;
	}
	public void setpersonID(int personID) {
		this.personID = personID;
	}
	public LocalDate getTerminStartDatum() {
		return terminStartDatum;
	}
	public void setTerminStartDatum(LocalDate terminStartDatum) {
		this.terminStartDatum = terminStartDatum;
	}
	public LocalDate getTerminEndeDatum() {
		return terminEndeDatum;
	}
	public void setTerminEndeDatum(LocalDate terminEndeDatum) {
		this.terminEndeDatum = terminEndeDatum;
	}
	public LocalTime getTerminZeit() {
		return terminZeit;
	}
	public void setTerminZeit(LocalTime terminZeit) {
		this.terminZeit = terminZeit;
	}
	public LocalTime getTerminZeitBis() {
		return terminZeitBis;
	}
	public void setTerminZeitBis(LocalTime terminZeitBis) {
		this.terminZeitBis = terminZeitBis;
	}
	public int getTerminRaum() {
		return terminRaum;
	}
	public void setTerminRaum(int terminRaum) {
		this.terminRaum = terminRaum;
	}

	public String getTerminInfo() {
		return terminInfo;
	}
	public void setTerminInfo(String terminInfo) {
		this.terminInfo = terminInfo;
	}
	public int getTerminPrivat() {
		return terminPrivat;
	}
	public void setTerminPrivat(int terminPrivat) {
		this.terminPrivat = terminPrivat;
	}
	public String getTerminPrivatInfo() {
		return terminPrivatInfo;
	}
	public void setTerminPrivatInfo(String terminPrivatInfo) {
		this.terminPrivatInfo = terminPrivatInfo;
	}
	public String getTerminFarbe() {
		return terminFarbe;
	}
	public void setTerminFarbe(String terminFarbe) {
		this.terminFarbe = terminFarbe;
	}

	public String toString(){
		return "Termin [terminID="
				+terminID
				+",personID="
				+personID
				+",terminStartDatum="
				+terminStartDatum
				+",terminEndeDatum="
				+terminEndeDatum
				+",terminZeit="
				+terminZeit				
				+",terminZeitBis="
				+terminZeitBis
				+",terminRaum="
				+terminRaum
				+",terminInfo="
				+terminInfo
				+",terminPrivat="
				+terminPrivat
				+",terminPrivatInfo="
				+terminPrivatInfo				
				+",terminFarbe="
				+terminFarbe+"]";				
	}
	
}
