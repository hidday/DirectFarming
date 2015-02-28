package com.example.hidday.directfarming;

import android.content.Context;
import android.util.Log;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hidday on 28/02/2015.
 */
public class Model {

    // class member
    private static Model instance;

    // private constructor
    private Model(Context context){
        init(context);
    }
    //public accessor
    public static Model getInstance(Context context){
        if (instance == null) {
            instance = new Model(context);
        }
        return instance;
    }

    private void init(Context context){
        Log.d("Model", "Initializing DB ");
        //Parse.initialize(context, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
        Parse.initialize(context, "zlDQOcXVpC2a3exWmuvGl3FcWGvsmpn0MgFC7i1D", "I29kas1FRUnzesQxtqegGlpBEoHUDMhmft5QtCDY");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

    }

    public void addStudent(Student st){
        Log.d("HY","Model addStudent "+st);
        ParseObject newStudent=studentToJson(st);
        //		newStudent.saveInBackground();
        try {
            newStudent.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    /////////////No Background Operation//////////////////
    public ArrayList<Student> getAllStudents(){
        Log.d("HY", "Model - Getting all students");
        ArrayList<Student> students = new ArrayList<Student>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Student");
        try{
            List<ParseObject> objects=query.find() ;
            if(objects!=null){
                Log.d("HY", "Model - Getting all students - done (), objects.size()=" +objects.size() );

                for(ParseObject o: objects){
                    students.add(jsonToStudent(o));
                }
                Log.d("HY", "Model - after coversion students.size()=" +students.size());
            }
        }
        catch(ParseException e){
            e.printStackTrace();
            Log.e("HY", "Model - query.find() exeption"+e.toString());
        }
        Log.d("HY", "Model - Getting all students finished" );
        // This delay is added to demonstrate how the UI looks in cases of slow download
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return students;
    }

    /////////////CallBack Interface //////////////////
    interface GetAllClbck{
        public void done(List<Student> students);
    }
    /////////////Find In Background Operation with CallBack//////////////////
    public void getAllStudents(GetAllClbck clbck){
        final GetAllClbck getAllListener=clbck;

        Log.d("HY", "Model - Getting all students");

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Student");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                Log.d("HY", "Model - Getting all students - done ()" );
                List<Student> students = new ArrayList<Student>();
                for(ParseObject o: objects){
                    students.add(jsonToStudent(o));
                }
                getAllListener.done(students);
                Log.d("HY", "Model after getAllListener.done()" );
            }

        });
        Log.d("HY", "Model Getting all students -after findInBackground ()" );

    }

    ////////////Helper method to covert from ParseObject to Student //////////////
    public Student jsonToStudent(ParseObject p){
        Student s= new Student(p.getInt("index"),p.getString("name"),p.getInt("phone"),p.getBoolean("female"));
        Log.d("HY", "Model - jsonToStudent" +s );
        return s;
    }

    ////////////Helper method to covert from Student to ParseObject /////////////
    public ParseObject studentToJson(Student st){
        ParseObject po = new ParseObject("Student");
        po.put("index",st.getIndex());
        po.put("name", st.getName());
        po.put("phone", st.getPhone());
        po.put("female", st.isFemale());
        return po;
    }


    public void editStudent(Student s) {
        final Student s1=s;
        ParseObject studentToEdit=null;
        Log.d("HY", "Model.editStudent index= " +s1.getIndex());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
        query.whereEqualTo("index", s.getIndex());
        try{
            List<ParseObject> studentsList=query.find();
            if (studentsList.size()>0) {
                Log.d("HY", "Model.editStudent Retrieved " + studentsList.size() + " students");
                studentToEdit=studentsList.get(0);
                studentToEdit.put("name",s1.getName());
                studentToEdit.put("phone",s1.getPhone());
                studentToEdit.put("female",s1.isFemale());
            }
        }
        catch(ParseException e){
            e.printStackTrace();
        }


        studentToEdit.saveInBackground();

//		try {
//			studentToEdit.save();
//
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//			Log.e("HY", "Model.editStudent Error: " + e1.getMessage());
//		}



    }

    // Retrieve the object by id
    Student s;
    public Student getStudentById(int id){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
        query.whereEqualTo("_id", id);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> studentsList, ParseException e) {
                if (e == null) {
                    Log.d("HY", "Model.getStudentById Retrieved " + studentsList.size() + " students");
                    s=jsonToStudent(studentsList.get(0));

                } else {
                    Log.d("HY", "Model.getStudentById Error: " + e.getMessage());
                }
            }
        });
        return s;
    }



    public void deleteStudent(int id){
        Log.d("HY", "Model.deleteStudent index= " +id);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
        query.whereEqualTo("index", id);
        try{
            List<ParseObject> studentsList=query.find();
            if (studentsList.size()>0) {
                Log.d("HY", "Model.deleteStudent Retrieved " + studentsList.size() + " students");
                studentsList.get(0).deleteInBackground();

            }
        }
        catch (ParseException e1) {
            e1.printStackTrace();
            Log.e("HY", "Model.deleteStudent Error: " + e1.getMessage());
        }


    }




}
