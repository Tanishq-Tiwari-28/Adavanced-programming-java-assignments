import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
class students{

    private String name;
    private int roll_number;
    private double cgpa;
    private String branch;
    private String status;
    private double highest_CTC;
    private String offer;
    public double getHighest_CTC() {
        return highest_CTC;
    }

    public void setHighest_CTC(double highest_CTC) {
        this.highest_CTC = highest_CTC;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(int roll_number) {
        this.roll_number = roll_number;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public students(String name,int roll_number,double cgpa,String branch){
        this.name=name;
        this.roll_number=roll_number;
        this.cgpa=cgpa;
        this.branch=branch;
        this.highest_CTC=0;
        this.offer="Not placed";
        this.status="Not applied";
    }
    private ArrayList<companies> available_company=new ArrayList<>();
    private ArrayList<companies> offering_company=new ArrayList<>();
    private ArrayList<companies> rejected_company=new ArrayList<>();
    public void add_offering_companies(companies c){
        this.offering_company.add(c);
    }
    public void student_details(){
        System.out.println("Student Name: " + this.name);
        System.out.println("Student roll: " + this.roll_number);
        System.out.println("Student branch: " + this.branch);
        System.out.println("Student CGPA: " + this.cgpa);
    }
    public void student_register_institute_drive(Placement_cell_IIITD P){
        if(Objects.equals(this.status, "Not applied")){
            P.register_student(this);
            System.out.println(this.name + "Registered for Placement Drive att IIITD \n Your details are: \n");
            System.out.println("Name: " + this.name);
            System.out.println("Roll No: " + this.roll_number);
            System.out.println("CGPA: " + this.cgpa);
            System.out.println("Branch: " + this.branch);
            this.status="applied";
            ArrayList<companies> company = P.get_Companies_registration_cell();
            for(int i=0;i<company.size();i++){     //
                if(this.cgpa>=company.get(i).getMin_cgpa()&&company.get(i).getMin_cgpa()>=3*highest_CTC){
                    this.available_company.add(company.get(i));
                }
            }
        }
    }
    public void get_available_companies(){
        if(this.offer=="Not placed"){
            for(int i=0;i<available_company.size();i++){
                available_company.get(i).company_details();
            }
        }else{
            System.out.println("UNAVAILABLE");
        }
    }
    public void student_register_for_company(String name){
        if(this.status=="applied"&&this.offer=="Not placed"){
            for(int i=0;i<available_company.size();i++){
                if(available_company.get(i).getNameC()==name){
                    available_company.get(i).add_register_company(this);
                    System.out.println("Successfully registered for "+available_company.get(i).getRole()+" Role at "+available_company.get(i).getNameC());
                }
            }
        }
    }



    public void get_current_status(){
        if(this.status=="blocked"){
            System.out.println("Sorry you are blocked");
        }else if(this.status=="Placed"){
            System.out.println("You are already placed");
        }else{
            int num=-1;
            int max=0;
            for(int i=0;i<offering_company.size();i++){
                if(offering_company.get(i).getPackage() > max){
                    num=i;
                }
            }if(num!=-1){
                companies C=offering_company.get(num);
                System.out.println("You have been offered by "+C.getNameC()+" !! please accept the offer");
            }else{
                System.out.println("You have no offer till now");
            }
        }
    }

    public void accept_the_offer(){
        if(this.offer=="Not placed"&& offering_company.size()>0){
            int num=-1;
            int max=0;
            for(int i=0;i<offering_company.size();i++){
                if(offering_company.get(i).getPackage() >max){
                    num=i;
                }
            }
            if(num!=-1){
                companies C =offering_company.get(num);
                C.add_accepted_company(this);
                this.offering_company.clear();
                this.offering_company.add(C);
                this.offer="placed";
                this.highest_CTC=C.getPackage();
                System.out.println("Congratulations" + this.name + " You have accepted the offer by" + C.getNameC() + "!!");
            }else{
                System.out.println("you have no orders till now");
            }
        }
    }
    public void reject_offer(String name){
        if(status.equals("not-placed")){
            int num = -1;
            for(int i=0;i<offering_company.size();i++){
                if(offering_company.get(i).getNameC().equals(name)){
                    num = i;
                    break;
                }
            }
            if(num != -1){
                rejected_company.add(offering_company.get(num));
                offering_company.remove(num);
            }
            if(offering_company.size() == 0 && rejected_company.size() > 0){
                status = "blocked";
            }
        }
    }

}
class companies {
    private ArrayList<students> students_registered_for_company ;
    private ArrayList<students> selected_students ;
    private ArrayList<students> accepted_students;
    private String name;
    private String role;
    private double Package;
    private double min_cgpa;
    private String registration_time;
    private boolean is_registered;

    public boolean is_registered() {
        return is_registered;
    }

    public void set_registered(boolean is_registered) {
        this.is_registered = is_registered;
    }
    public ArrayList<students> get_offered_students(){
        return this.selected_students;
    }
    public ArrayList<students> get_accepted_students(){
        return this.accepted_students;
    }
    public String getNameC() {
        return name;
    }

    public void setNameC(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    public double getPackage() {
        return Package;
    }

    public void setPackage(double Package) {
        this.Package = Package;
    }

    public double getMin_cgpa() {
        return min_cgpa;
    }

    public void setMin_cgpa(double min_cgpa) {
        this.min_cgpa = min_cgpa;
    }

    public String getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(String registration_time) {
        this.registration_time = registration_time;
    }
    public void company_details() {
        System.out.println("CompanyName: " + this.name);
        System.out.println("Company Role offering: " + this.role);
        System.out.println("Company Package: " + this.Package);
        System.out.println("Company CGPA criteria: " + this.min_cgpa);
    }

    public void add_register_company(students S){
        this.students_registered_for_company.add(S);
    }
    public void add_accepted_company(students S){
        this.selected_students.add(S);
    }


    public void company_register_institute_drive(Placement_cell_IIITD P){
        if(!this.is_registered){
            System.out.println("Registered!!");
            this.is_registered=true;
            P.register_company(this);
        }
    }
    public void offer_students(){
        if(students_registered_for_company.size()<2){
            for(int i=0;i<students_registered_for_company.size();i++){
                students_registered_for_company.get(i).add_offering_companies(this);

            }
        }
    }



}
class Placement_cell_IIITD {
    private ArrayList<students> students_registration_cell;
    private ArrayList<companies> companies_registration_cell;
    public void register_company(companies C) {
        companies_registration_cell.add(C);
    }
    public void register_student(students S){
        students_registration_cell.add(S);
    }
    public ArrayList<students> get_Students_registration_cell() {
        return students_registration_cell;
    }

    public ArrayList<companies> get_Companies_registration_cell() {
        return companies_registration_cell;
    }
    public int get_number_students_registered(){
        return students_registration_cell.size();
    }

    public int get_number_company_registered(){
        return companies_registration_cell.size();
    }
    private String start_date_company;
    private String end_date_company;

    private String start_date_student;
    private String end_date_student;

    // getter/setters
    public String get_start_date_company(){
        return this.start_date_company;
    }
    public String get_end_date_company(){
        return this.end_date_company;
    }
    public String get_start_date_student(){
        return this.start_date_student;
    }
    public String get_end_date_student(){
        return this.end_date_student;
    }
    public void Open_student_registrations(String a, String b){
        this.start_date_student = a;
        this.end_date_student = b;
    }
    // open company registrations
    public void Open_company_registrations(String a, String b){
        this.start_date_company = a;
        this.end_date_company = b;
    }

}


    public class A1_2021496 {

        public static void menu0() {
            System.out.println("Welcome to Future Builder.....");
            System.out.println("Enter 1 to enter the application or enter 2 to exit the application");
        }

        public static void menu1() {
            System.out.println("Choose the mode you want to enter in:");
            System.out.println("1.Enter as student mode");
            System.out.println("2.Enter as company mode");
            System.out.println("3.Enter as placement cell mode");
            System.out.println("4.return to main application");
        }

        public static void menu2() {
            System.out.println("Choose the student query to perform");
            System.out.println("1.enter as a student(give name and roll number)");
            System.out.println("2.add students");
            System.out.println("3.back");
        }

        public static void menu3() {
            System.out.println("Choose the company query to perform:");
            System.out.println("1.Add company and details");
            System.out.println("2.Choose company");
            System.out.println("3.Get available companies");
            System.out.println("4.back");
        }

        public static void menu4() {
            System.out.println("Welcome to IIITD placement cell");
            System.out.println("1.Open Student Registrations");
            System.out.println("2.Open Company Registrations");
            System.out.println("3.Get number of students1 registrations");
            System.out.println("4.Get number of company registrations");
            System.out.println("5.Get number of offered/un-offered/blocked students1");
            System.out.println("6.Get student details");
            System.out.println("7.Get company details");
            System.out.println("8.Get average Package");
            System.out.println("9.Get company process results");
            System.out.println("10.Back");
        }

        public static void menu5() {
            System.out.println("1.Register for Placement Drive");
            System.out.println("2.Register for company");
            System.out.println("3.Get all available companies");
            System.out.println("4.Get current status");
            System.out.println("5.Update cgpa");
            System.out.println("6.Accept order");
            System.out.println("7.Reject order");
            System.out.println("8.back");
        }

        public static void main(String[] args) {
            Placement_cell_IIITD P = new Placement_cell_IIITD();
            ArrayList<companies> companies_registered = new ArrayList<>();
            ArrayList<students> student_registered = new ArrayList<>();
            ArrayList<students> added_students=new ArrayList<>();
            System.out.println("Welcome to Future Builder.....");
            System.out.println("Enter 1 to enter the application or enter 2 to exit the application");
            Scanner sc = new Scanner(System.in);
            String x = sc.next();
            boolean back1 = false; //this will take user to menu1
            while (!back1) {
                if (Integer.parseInt(x) == 1) {
                    back1 = true;
                    menu1();
                    String y = sc.next();
                    if (Integer.parseInt(y) == 1) {
                        menu2();
                        String z = sc.next();
                        boolean back2 = false; //this will take user to menu2
                        while (!back2) {
                            if (Integer.parseInt(z) == 2) {
                                System.out.println("Enter the number of students to register.");
                                int a=sc.nextInt();
                                back2=true;
                                for(int i=0;i<a;i++){
                                    String n=sc.next();
                                    int r=sc.nextInt();
                                    double cg=sc.nextInt();
                                    String br=sc.next();
                                    students S =new students(n,r,cg,br);
                                    added_students.add(S);
                                    System.out.println();
                                    System.out.println();
                                }
                                menu2();
                                z=sc.next();


                            } else if (Integer.parseInt(z) == 1) {
                                back2 = true;
                                System.out.println("enter name and roll number");
                                String na=sc.next();
                                int ro=sc.nextInt();
                                int count=0;
                                for (students S:added_students){
                                    if(Objects.equals(S.getName(), na) &&S.getRoll_number()==ro){
                                        System.out.println("Welcome "+na+"!!");
                                        menu5();
                                        boolean back3 = false; //this will take user to menu2
                                        while (!back3) {
                                            if (Integer.parseInt(z) == 1) {
                                                back3 = true;
                                            } else if (Integer.parseInt(z) == 2) {
                                                back3 = true;
                                            } else if (Integer.parseInt(z) == 3) {
                                                back3 = true;
                                            } else if (Integer.parseInt(z) == 2) {
                                                back3 = true;
                                            }else if (Integer.parseInt(z) == 2) {
                                                back3 = true;
                                            }else if (Integer.parseInt(z) == 2) {
                                                back3 = true;
                                            }else if (Integer.parseInt(z) == 4) {
                                                back2 = false;
                                                break;
                                            }
                                        }
                                    }else{
                                        System.out.println("No such student exists");
                                    }
                                }
                            } else if (Integer.parseInt(z) == 3) {
                                back1 = false;
                                break;
                            }
                        }
                    } else if (Integer.parseInt(y) == 2) {
                        menu3();
                        String z = sc.next();
                        boolean back2 = false; //this will take user to menu2
                        while (!back2) {
                            if (Integer.parseInt(z) == 1) {
                                back2 = true;
                            } else if (Integer.parseInt(z) == 2) {
                                back2 = true;
                            } else if (Integer.parseInt(z) == 3) {
                                back2 = true;
                            } else if (Integer.parseInt(z) == 4) {
                                back1 = false;
                                break;
                            }
                        }
                    } else if (Integer.parseInt(y) == 3) {
                        menu4();
                        String z = sc.next();
                        boolean back2 = false; //this will take user to menu2
                        while (!back2) {
                            if (Integer.parseInt(z) == 1) {
                                back2 = true;
                                System.out.println("Enter opening time and closing time for registrations for students");
                            } else if (Integer.parseInt(z) == 2) {
                                back2 = true;
                                System.out.println("Enter opening time and closing time for registrations for company");
                            } else if (Integer.parseInt(z) == 3) {
                                back2 = true;
                                if (P.get_Students_registration_cell().size() == 0) {
                                    System.out.println("No students1 have registered till now");
                                } else {
                                    System.out.println("Total students1 registered are: " + P.get_Students_registration_cell().size());
                                }
                            } else if (Integer.parseInt(z) == 4) {
                                back2 = true;
                                if (P.get_Companies_registration_cell().size() == 0) {
                                    System.out.println("No students1 have registered till now");
                                } else {
                                    System.out.println("Total students1 registered are: " + P.get_Companies_registration_cell().size());
                                }
                            } else if (Integer.parseInt(z) == 5) {
                                back2 = true;
                            } else if (Integer.parseInt(z) == 6) {
                                back2 = true;
                            } else if (Integer.parseInt(z) == 7) {
                                back2 = true;
                            } else if (Integer.parseInt(z) == 8) {
                                back2 = true;
                            } else if (Integer.parseInt(z) == 9) {
                                back2 = true;
                            } else if (Integer.parseInt(z) == 10) {
                                back1 = false;
                                break;
                            }
                        }
                    } else if (Integer.parseInt(y) == 4) {
                        back1 = false;
                        menu0();
                        x = sc.next();
                    }
                } else if (Integer.parseInt(x) == 2) {
                    back1 = true;
                    System.out.println("Thank you for Using Future Builder Application");
                    System.exit(0);
                } else {
                    System.out.println("invalid input");
                    System.out.println("Enter 1 to enter the application or enter 2 to exit the application");
                    x = sc.next();
                }
            }
        }
    }