package database;

import database.data.LinkedPurchaseListKey;
import database.tables.Course;
import database.tables.LinkedPurchaseList;
import database.tables.PurchaseList;
import database.tables.Student;

import java.util.ArrayList;
import java.util.List;

public class ConvertPurchaseList {

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<PurchaseList> purchaseLists = new ArrayList<>();

    public ConvertPurchaseList() {
        setIds();
    }

    private List<Student> getStudents() {
        String sql = "FROM " + Student.class.getSimpleName();
        students = Connection.getSession().createQuery(sql).list();
        return students;
    }

    private List<Course> getCourses() {
        String sql = "FROM " + Course.class.getSimpleName();
        courses = Connection.getSession().createQuery(sql).list();
        return courses;
    }

    private List<PurchaseList> getPurchaseList() {
        String sql = "FROM " + PurchaseList.class.getSimpleName();
        purchaseLists = Connection.getSession().createQuery(sql).list();
        return purchaseLists;
    }

    private void setIds() {
        List<Integer> studentId  = new ArrayList<>();
        List<Integer> courseId = new ArrayList<>();

        for (int i = 0; i < getPurchaseList().size(); i++) {
            for (Student student : getStudents()) {
                if (purchaseLists.get(i).getStudentName().equals(student.getName())) {
                    studentId.add(student.getId());
                }
            }
            for (Course course : getCourses()) {
                if (purchaseLists.get(i).getCourseName().equals(course.getName())) {
                    courseId.add(course.getId());
                }
            }

            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList.setId(new LinkedPurchaseListKey(studentId.get(i), courseId.get(i)));
            Connection.save(linkedPurchaseList);
        }
    }
}
