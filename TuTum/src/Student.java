import java.util.ArrayList;

public class Student {
	
	// Fields from database
	private String studentID;
	private String studentFirstName;
	private String studentLastName;
	private String studentCurrentLocation; //last known location of student
	private String studentPreviousLocation; //previous location of student
	private boolean studentStatusFlag; //true: student is seen as harmful; false: student is not seen as harmful
	private boolean studentIsAbsent; //true: student is absent from building; false; student is not absent from building

	
	private String absenceAlertMessage; //holds message of alert
	private int absenceAlertLevel; //holds level of alert
	
	public Student(String studentID, String studentFirstName, String studentLastName) {
		this.studentID = studentID;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
	}
	
	// Constructor for inserting data from table
	public Student(String studentID, String studentFirstName, String studentLastName, 
			String currRoom, String prevRoom, boolean sflag, boolean abFlag) {
		this.studentID = studentID;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
		this.studentCurrentLocation = currRoom;
		this.studentPreviousLocation = prevRoom;
		this.studentStatusFlag = sflag;
		this.studentIsAbsent = abFlag;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getStudentCurrentLocation() {
		return studentCurrentLocation;
	}
	
	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	//sets current location of student to new value based on most recent attendance by teacher (Teacher class needs to access this)
	public void setStudentCurrentLocation(String studentCurrentLocation) {
		if(studentCurrentLocation != null)
			studentPreviousLocation = this.studentCurrentLocation;
		this.studentCurrentLocation = studentCurrentLocation;
		studentLocationStatus();
	}

	public boolean getStudentStatusFlag() {
		return studentStatusFlag;
	}
	
	//sets current status flag of student to new value due to an incident (Teacher class must access this)
	public void setStudentStatusFlag(boolean studentStatusFlag) {
		this.studentStatusFlag = studentStatusFlag;
	}
	
	//checks student's most recent and previous location; if student was in previous class but not in current, alert creation method is called
	private void studentLocationStatus() {
		if(!studentPreviousLocation.equals(null) && studentCurrentLocation.equals(null)) {
				if(studentStatusFlag)
					absenceAlertLevel = 2;
				else
					absenceAlertLevel = 1;
				absenceAlertMessage = "Present, but student is not in current class. Last known location: " + studentPreviousLocation;
				getAbsenceAlert();

			}
	}

	//true: student was never in building; false: student in building at some point
	
	public void setStudentIsAbsent(boolean studentAbsence) {
		studentIsAbsent = studentAbsence;
	}
	
	public boolean studentIsAbsent() {
		return studentIsAbsent;
	}
	
	//creates StudentAlert object to be added to database and dealt with
	public StudentAlert getAbsenceAlert() {
		studentLocationStatus();
		return new StudentAlert(absenceAlertLevel, absenceAlertMessage);
	}
}
