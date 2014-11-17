package android.example.studygroup.helper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.example.studygroup.model.*;


public class DatabaseHelper extends SQLiteOpenHelper {
	private static DatabaseHelper dbHelperSingleton;
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DB1";

	private static final String TABLE_USER = "user";
	private static final String TABLE_COURSE = "course";
	private static final String TABLE_TAKEN = "taken";
	private static final String TABLE_NOTIFICATION = "notification";
	private static final String TABLE_MEMBEROF = "memberOf";
	private static final String TABLE_STUDYGROUP = "studyGroup";

	private static final String COLUMN_GROUPNAME = "groupName";
	private static final String COLUMN_COURSENAME = "courseName";
	private static final String COLUMN_ADMIN = "adminName";
	private static final String COLUMN_DESCRIPTION = "description";
	private static final String COLUMN_PLACE = "place";
	private static final String COLUMN_STARTTIME = "startTime";
	private static final String COLUMN_ENDTIME = "endTime";
	private static final String COLUMN_DAYS = "days";

	private static final String COLUMN_USERID = "userId";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_EMAILID = "emailId";
	private static final String COLUMN_MAJOR = "major";
	
	private static final String COLUMN_COURSEID = "courseId";
	private static final String COLUMN_SENDERID = "senderId";
	private static final String COLUMN_MESSAGE = "message";
	
	private static final String CREATE_TABLE_USER="CREATE TABLE "+TABLE_USER+" ( "+ COLUMN_USERID +" TEXT PRIMARY KEY , "+ COLUMN_NAME +" TEXT, "+ COLUMN_EMAILID +" TEXT, "+ COLUMN_MAJOR +" TEXT )";
	private static final String CREATE_TABLE_GROUP="CREATE TABLE "+TABLE_STUDYGROUP+"("+ COLUMN_GROUPNAME +" TEXT PRIMARY KEY, "+ COLUMN_ADMIN +" TEXT, "+ COLUMN_DESCRIPTION +" TEXT, "+ COLUMN_COURSEID +" TEXT, "+ COLUMN_PLACE +" TEXT, "+COLUMN_STARTTIME+ " TEXT, "+COLUMN_ENDTIME+" TEXT, "+COLUMN_DAYS+ " TEXT, FOREIGN KEY ("+ COLUMN_ADMIN +") REFERENCES "+TABLE_USER+"("+COLUMN_USERID+"), FOREIGN KEY ("+ COLUMN_COURSEID +") REFERENCES "+TABLE_COURSE+"("+COLUMN_COURSEID+") )";
	private static final String CREATE_TABLE_MEMBEROF="CREATE TABLE "+TABLE_MEMBEROF+" ( "+ COLUMN_USERID +" TEXT, "+ COLUMN_GROUPNAME +" TEXT, FOREIGN KEY( "+ COLUMN_USERID +" ) REFERENCES "+TABLE_USER+"("+COLUMN_USERID+"), FOREIGN KEY("+ COLUMN_GROUPNAME +") REFERENCES "+TABLE_STUDYGROUP+"("+COLUMN_GROUPNAME+"))";
	private static final String CREATE_TABLE_COURSE="CREATE TABLE "+TABLE_COURSE+" ( "+ COLUMN_COURSEID +" TEXT PRIMARY KEY , "+ COLUMN_COURSENAME +" TEXT, "+ COLUMN_DAYS +" TEXT, "+ COLUMN_STARTTIME +" TEXT, "+ COLUMN_ENDTIME +" TEXT )";
	private static final String CREATE_TABLE_TAKEN="CREATE TABLE "+TABLE_TAKEN+" ( "+ COLUMN_USERID +" TEXT , "+ COLUMN_COURSEID +" TEXT, FOREIGN KEY("+ COLUMN_USERID +") REFERENCES "+TABLE_USER+"("+COLUMN_USERID+"), FOREIGN KEY("+ COLUMN_COURSEID +") REFERENCES "+TABLE_COURSE+"("+COLUMN_COURSEID+") )";
	private static final String CREATE_TABLE_NOTIFICATION="CREATE TABLE "+TABLE_NOTIFICATION+" ( "+ COLUMN_SENDERID +" TEXT , "+ COLUMN_GROUPNAME +" TEXT, "+ COLUMN_MESSAGE +" TEXT, FOREIGN KEY("+ COLUMN_SENDERID +") REFERENCES "+TABLE_USER+"("+COLUMN_USERID+"), FOREIGN KEY("+ COLUMN_GROUPNAME +") REFERENCES "+TABLE_STUDYGROUP+"("+COLUMN_GROUPNAME+") )";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	 public static DatabaseHelper getDataHelper(Context context) {
         if (dbHelperSingleton == null) {
        	 dbHelperSingleton = new DatabaseHelper(context);
           }
         return dbHelperSingleton;
 }

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBEROF);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDYGROUP);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAKEN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
		
		db.execSQL(CREATE_TABLE_COURSE);
		db.execSQL(CREATE_TABLE_USER);
		db.execSQL(CREATE_TABLE_TAKEN);
		db.execSQL(CREATE_TABLE_GROUP);
		db.execSQL(CREATE_TABLE_NOTIFICATION);
		db.execSQL(CREATE_TABLE_MEMBEROF);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBEROF);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDYGROUP);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAKEN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);

		onCreate(db);
	}
	
	public void insertUser(User user){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_USERID, user.getUserId());
		values.put(COLUMN_NAME, user.getName());
		values.put(COLUMN_EMAILID, user.getMajor());

		long row_id = db.insert(TABLE_USER, null, values);
		
	}
	
	public void insertGroup(StudyGroup group){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_GROUPNAME, group.getGroupName());
		values.put(COLUMN_ADMIN, group.getAdminName());
		values.put(COLUMN_COURSEID, group.getCourseId());
		values.put(COLUMN_DESCRIPTION, group.getDescription());
		values.put(COLUMN_PLACE, group.getPlace());
		values.put(COLUMN_STARTTIME, group.getStartTime());
		values.put(COLUMN_ENDTIME, group.getEndTime());
		values.put(COLUMN_DAYS, group.getDays());

		long row_id = db.insert(TABLE_STUDYGROUP, null, values);	
	}
	
	public void insertMemberOf(MemberOf memberOf){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_USERID, memberOf.getUserId());
		values.put(COLUMN_GROUPNAME, memberOf.getGroupName());
		long row_id = db.insert(TABLE_STUDYGROUP, null, values);	
	}
	
	public void insertCourse(Course course){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_COURSEID, course.getCourseId());
		values.put(COLUMN_COURSENAME, course.getCourseName());
		values.put(COLUMN_STARTTIME, course.getStartTime());
		values.put(COLUMN_ENDTIME, course.getEndTime());
		values.put(COLUMN_DAYS, course.getDays());

		long row_id = db.insert(TABLE_COURSE, null, values);
		
	}
	
	public void insertTaken(Taken taken){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_COURSEID, taken.getCourseId());
		values.put(COLUMN_USERID, taken.getUserId());

		long row_id = db.insert(TABLE_TAKEN, null, values);
		
	}
	
	public void insertNotification(Notification notification){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_SENDERID, notification.getSenderId());
		values.put(COLUMN_GROUPNAME, notification.getGroupName());
		values.put(COLUMN_MESSAGE, notification.getMessage());

		long row_id = db.insert(TABLE_NOTIFICATION, null, values);
		
	}
	
	public List<String> retriveGroups(String courseId){
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  "+COLUMN_GROUPNAME+" FROM " + TABLE_STUDYGROUP + " WHERE "
				+ COLUMN_COURSEID + " = '" + courseId +"'";
		
		Cursor c = db.rawQuery(selectQuery, null);
		ArrayList<String> groupList = new ArrayList<String>();
		if (c.moveToFirst()) {
			do {
				groupList.add((c.getString(c.getColumnIndex(COLUMN_GROUPNAME))));
			} while (c.moveToNext());
		}
		return groupList;
	}
	
	public List<String> retrieveCourses(String userId){
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  DISTINCT "+COLUMN_COURSEID+" FROM " + TABLE_TAKEN + " WHERE "
				+ COLUMN_USERID + " = '" + userId +"'";
		
		Cursor c = db.rawQuery(selectQuery, null);
		ArrayList<String> courseList = new ArrayList<String>();
		if (c.moveToFirst()) {
			do {
				courseList.add((c.getString(c.getColumnIndex(COLUMN_COURSEID))));
			} while (c.moveToNext());
		}
		return courseList;
	}
}
