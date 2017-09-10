package wang.mh.java8;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by 明辉 on 2017/9/9.
 */
public class PreidcateConsumerDemo {


    public static void main(String[] args) {

        Student student1 = new Student("Ashok","Kumar", 9.5);

        student1 = updateStudentFee(student1,
                //Lambda expression for Predicate interface
                student -> student.grade == 8,
                //Lambda expression for Consumer inerface
                student -> student.feeDiscount = 30.0);

        student1.printFee();

        Student student2 = new Student("Rajat","Verma", 8.0);

        student2 = updateStudentFee(student2,
                student -> student.grade >= 8,
                student -> student.feeDiscount = 20.0);

        student2.printFee();

    }

    public static Student updateStudentFee(Student student, Predicate<Student> predicate, Consumer<Student> consumer){

        //Use the predicate to decide when to update the discount.

        if ( predicate.test(student)){

            //Use the consumer to update the discount value.

            consumer.accept(student);
        }

        return student;

    }
}

class Student{

    String firstName;

    String lastName;

    Double grade;

    Double feeDiscount = 0.0;

    Double baseFee = 20000.0;

    public Student(String firstName, String lastName, Double grade) {

        this.firstName = firstName;

        this.lastName = lastName;

        this.grade = grade;
    }

    public void printFee(){

        Double newFee = baseFee - ((baseFee * feeDiscount) / 100);

        System.out.println("The fee after discount: " + newFee);

    }

}
