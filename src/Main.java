import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String choice;
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Consultation> consultations = new ArrayList<>();

        do {
            System.out.println("##### Welcome to the Skin Consultation Centre Console Menu! #####");
            System.out.println("A : Add a new doctor");
            System.out.println("D : Delete a doctor");
            System.out.println("P : Print the list of the doctors");
            System.out.println("S : Save data into a file");
            System.out.println("L : Load data from file");
            System.out.println("G : Open Graphical User Interface");

            System.out.println();
            System.out.print("Enter which method that you want to execute : ");

            Scanner scn = new Scanner(System.in);
            String inputM = scn.nextLine();

            switch (inputM) {
                case "A":
                case "a":
                    Scanner input = new Scanner(System.in);
                    for (int i = 0; i < 10; i++) {

                        System.out.println("### ADD A DOCTOR ###");
                        System.out.print("Name : ");
                        String name = input.nextLine();

                        System.out.print("SurName : ");
                        String surName= input.nextLine();

                        System.out.print("DOB : ");
                        String dob = input.nextLine();

                        System.out.print("Mobile No : ");
                        String mblNumber = input.nextLine();

                        System.out.print("licenceNum : ");
                        String medId = input.nextLine();

                        System.out.print("Specialisation(Cosmetic/Medical/Paediatric etc.) : ");
                        String specialisation = input.nextLine();

                        Doctor doctor=new Doctor();
                        doctor.setName(name);
                        doctor.setSurName(surName);
                        doctor.setDob(dob);
                        doctor.setMblNumber(Integer.parseInt(mblNumber));
                        doctor.setMedId(medId);
                        doctor.setSpecialisation(specialisation);
                        doctors.add(doctor); //doctor object is adding to the doctors arraylist.

                        System.out.print("If you want to exit enter Y, else enter any other key : " );
                        String exit = input.nextLine();
                        if (exit.equals("Y") | exit.equals("y")){
                            break;
                        }
                        System.out.println();
                    }
                    System.out.println();
                break;

                case "D":
                case "d":
                    System.out.println("### Delete a Doctor ###");
                    Scanner input2 = new Scanner(System.in);
                    System.out.print("Enter the medical license number of the doctor that you want to remove : ");
                    String enterId = input2.nextLine();

                    Doctor doctor = null; //Creates a variable name doctor with null value.
                    for (Doctor d : doctors) {
                        if (d.getMedId().equals(enterId)) {
                            doctor = d;
                            break;
                        }
                    }
                    if (doctor == null) {
                        System.out.println("No doctor was found with the specified license number! Please try again!");
                    } else {
                        doctors.remove(doctor);
                        System.out.println("The doctor with name " + doctor.getName() + " " + doctor.getSurName() + " and license number " + doctor.getMedId() + " has been removed!");
                        System.out.println("The centre now has " + doctors.size() + " doctors.");
                    }
                break;

                case "P":
                case "p":
                    System.out.println("### Doctors ###");
                    doctors.sort(new Comparator<>() {
                        @Override
                        public int compare(Doctor d1, Doctor d2) {
                            return d1.getSurName().compareToIgnoreCase(d2.getSurName());
                        }
                    });
                    for (Doctor doctor3 : doctors) {
                        System.out.println("Doctor's name : " + doctor3.getName());
                        System.out.println("SurName : " + doctor3.getSurName());
                        System.out.println("DOB : "+ doctor3.getDob());
                        System.out.println("Mobile No : "+ doctor3.getMblNumber());
                        System.out.println("License No : " + doctor3.getMedId());
                        System.out.println("Specialization : " + doctor3.getSpecialisation());
                        System.out.println();
                    }
                break;

                case "S":
                case "s":
                    System.out.println("### Save data into a file ###");
                    File f0 = new File("D:Stored Data.txt");
                    FileWriter writer = new FileWriter(f0);
                    if (f0.exists()) {
                        for (Doctor doctor2 : doctors) {
                            writer.write("Doctor's Name : " + doctor2.getName());
                            writer.write("\n");
                            writer.write("SurName : " + doctor2.getSurName());
                            writer.write("\n");
                            writer.write("DOB : " + doctor2.getDob());
                            writer.write("\n");
                            writer.write("Mobile No : " + doctor2.getMblNumber());
                            writer.write("\n");
                            writer.write("Licence No : " + doctor2.getMedId());
                            writer.write("\n");
                            writer.write("Specialisation : " + doctor2.getSpecialisation());
                            writer.write("\n");
                            writer.write("\n");
                        }
                        System.out.println("The file was successfully saved as Stored Data.txt!");
                    } else {
                        System.out.println("The file does not exist.");
                    }
                    writer.close();
                break;

                case "L":
                case "l":
                    System.out.println("### Load data from file ###");
                    File file = new File("D:Stored Data.txt");
                    List<Doctor> doctors0 = new ArrayList<>();

                    if (file.exists()) {
                        try {
                            FileReader fileReader = new FileReader(file);
                            BufferedReader bufferedReader = new BufferedReader(fileReader);
                            String line;
                            Doctor doctor0 = null;

                            while ((line = bufferedReader.readLine()) != null) {

                                if (line.startsWith("Doctor's Name : ")) {
                                    if (doctor0 != null) {
                                        doctors.add(doctor0); //Add the previously created Doctor object to the list.
                                    }
                                    doctor0 = new Doctor();
                                    doctor0.setName(line.substring(16)); //Extract the name from the line.
                                }
                                else if (line.startsWith("SurName : ") && (doctor0 != null)) {
                                    doctor0.setSurName(line.substring(11));
                                }
                                else if (line.startsWith("DOB : ") && (doctor0 != null)) {
                                    doctor0.setDob(line.substring(7));
                                }
                                else if (line.startsWith("Mobile No : ") && (doctor0 != null)) {
                                    doctor0.setMblNumber(Integer.parseInt(line.substring(13)));
                                }
                                else if (line.startsWith("Licence No : ") && (doctor0 != null)) {
                                    doctor0.setMedId(line.substring(14));
                                }
                                else if (line.startsWith("Specialisation : ") && (doctor0 != null)) {
                                    doctor0.setSpecialisation(line.substring(18));
                                }
                            }

                            if (doctor0 != null) {
                                doctors.add(doctor0); //Add the last Doctor object to the list.
                            }
                            bufferedReader.close();
                            System.out.println("Data was successfully loaded from Stored Data.txt!");

                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    else {
                        System.out.println("The file does not exist!");
                    }
                break;

                case "G":
                case "g":
                    JFrame cc = new JFrame();
                    JPanel panel = new JPanel();
                    cc.getContentPane();

                    JButton b = new JButton("All Doctors");
                    b.addActionListener(e ->
                            {
                                viewDoctor(doctors);
                            }
                    );
                    Dimension size1 = b.getPreferredSize();
                    b.setBounds(150, 60, 150,25);
                    panel.setLayout(null);
                    panel.add(b);

                    JButton aa = new JButton("Book a doctor");
                    aa.addActionListener(e ->
                            {
                                bookDoctor(doctors,consultations);
                            }
                    );

                    Dimension size2 = aa.getPreferredSize();
                    aa.setBounds(150, 120, 150,25);
                    panel.setLayout(null);
                    panel.add(aa);


                    JButton bb = new JButton("Consultation Details");
                    bb.addActionListener(e ->
                            {
                                viewChannel(doctors,consultations);
                            }
                    );

                    Dimension sizeViewChnl = bb.getPreferredSize();
                    bb.setBounds(150, 180, 150,25);
                    panel.setLayout(null);
                    panel.add(bb);

                    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    cc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    cc.add(panel);
                    cc.setLocation(400,70);
                    cc.setSize(500, 300);
                    cc.setVisible(true);
                break;

            }

            System.out.print("Do you want to go to the menu? : ");
            choice = scn.nextLine();
            System.out.println();
        } while (choice.equals("Y") | choice.equals("y"));

    }




    private static void viewDoctor(java.util.List<Doctor> doctors) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.getContentPane();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel);
        frame.setSize(500, 300);
        frame.setVisible(true);
        doctors.sort(new Comparator<Doctor>() {
            @Override
            public int compare(Doctor d1, Doctor d2) {
                return d1.getSurName().compareTo(d2.getSurName());
            }
        });
        String[] Col = { "Name", "surName", "Dob", "mblNumber","liceneNumber","Specialication" };
        DefaultTableModel tableM = new DefaultTableModel(Col, 0);
        JTable table = new JTable(tableM);
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        for (int i =0; i<doctors.size();i++) {
            String Name = doctors.get(i).getName();
            String surName = doctors.get(i).getSurName();
            String Dob = doctors.get(i).getDob();
            int mblNumber = doctors.get(i).getMblNumber();
            String liceneNumber = doctors.get(i).getMedId();
            String Specialication = doctors.get(i).getSpecialisation();
            Object[] data = {Name, surName, Dob, mblNumber,liceneNumber,Specialication};
            tableM.addRow(data);
        }
    }




    private static void bookDoctor(java.util.List<Doctor> doctors, ArrayList<Consultation> consultations) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.getContentPane();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel);
        frame.setSize(600, 900);
        frame.setVisible(true);

        JLabel title = new JLabel("Patient Info");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(200, 30);
        panel.add(title);

        //Name
        JLabel   a1= new JLabel("Name:");
        a1.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension size1 = a1.getPreferredSize();
        a1.setBounds(100, 100, size1.width, size1.height);
        panel.setLayout(null);
        panel.add(a1);

        JTextField b1 = new JTextField();
        b1.setSize(150,20);
        b1.setLocation(250, 105);
        panel.add(b1);

        //surName
        JLabel  a2 = new JLabel("SurName:");
        a2.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension size5 = a2.getPreferredSize();
        a2.setBounds(100, 150, size5.width, size5.height);
        panel.setLayout(null);
        panel.add(a2);

        JTextField b2 = new JTextField();
        b2.setSize(150,20);
        b2.setLocation(250, 150);
        panel.add(b2);

        //datepicker
        JLabel a3 = new JLabel("DOB:");
        a3.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizedate = a3.getPreferredSize();
        a3.setBounds(100, 200, sizedate.width, sizedate.height);
        panel.setLayout(null);
        panel.add(a3);

        JTextField b3 = new JTextField(20);
        b3.setSize(150,20);
        b3.setLocation(250, 200);
        panel.add(b3);

        JLabel  a4 = new JLabel("TEL:");
        a4.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension size7 = a4.getPreferredSize();
        a4.setBounds(100, 250, size7.width, size7.height);
        panel.setLayout(null);
        panel.add(a4);

        JTextField b5 = new JTextField();
        b5.setSize(150,20);
        b5.setLocation(250, 250);
        panel.add(b5);

        JLabel a5 = new JLabel("Consult Information");
        a5.setFont(new Font("Arial", Font.PLAIN, 30));
        a5.setSize(300, 30);
        a5.setLocation(150, 290);
        panel.add(a5);

        JLabel a6 = new JLabel("Date:");
        a6.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeDob = a6.getPreferredSize();
        a6.setBounds(100, 350, sizeDob.width, sizeDob.height);
        panel.setLayout(null);
        panel.add(a6);

        JTextField a7 = new JTextField(20);
        a7.setSize(150,20);
        a7.setLocation(250, 350);
        panel.add(a7);

        JButton popUp = new JButton("Select Date");
        Dimension size = popUp.getPreferredSize();
        popUp.setBounds(400, 350, size.width, size.height);
        panel.add(popUp);
        popUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                a7.setText(new DatePicker(frame).setPickedDate());
            }
        });

        JLabel  a8 = new JLabel("Start Time:");
        a8.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension size9 = a8.getPreferredSize();
        a8.setBounds(100, 400, size9.width, size9.height);
        panel.setLayout(null);
        panel.add(a8);

        String []startTimearray=new String[18];
        for (int i = 6; i < 18; i++) {
            startTimearray[i]= String.valueOf(i);
        }
        JComboBox a9=new JComboBox(startTimearray);
        a9.setSize(150,20);
        a9.setLocation(250, 400);
        panel.add(a9);

        String []h=new String[100];
        for (int i = 0; i <12; i++) {
            h[i]= String.valueOf(i+1);

        }

        JLabel  a10 = new JLabel("Booked Hours");
        a10.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizehrs = a10.getPreferredSize();
        a10.setBounds(100, 420, sizehrs.width, sizehrs.height);
        panel.setLayout(null);
        panel.add(a10);

        JComboBox a11=new JComboBox(h);
        a11.setSize(150,20);
        a11.setLocation(250, 420);
        panel.add(a11);

        JLabel  a12 = new JLabel("End Time:");
        a12.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension size10 = a12.getPreferredSize();
        a12.setBounds(100, 450, size10.width, size10.height);
        panel.setLayout(null);
        panel.add(a12);

        JTextField tEndTime = new JTextField();
        tEndTime.setSize(150,20);
        tEndTime.setLocation(250, 450);
        panel.add(tEndTime);

        a11.addActionListener(e ->{
            int getStartTime= Integer.parseInt(a9.getSelectedItem().toString());

            int howMnyHurs= Integer.parseInt(a11.getSelectedItem().toString());
            int endManualfinalTime=getStartTime+howMnyHurs;
            tEndTime.setText(String.valueOf(endManualfinalTime));
        } );

        String []dctrName=new String[10];
        for (int i = 0; i < doctors.size(); i++) {
            dctrName[i]=doctors.get(i).getName()+" "+doctors.get(i).getSurName();
        }

        JLabel  a13 = new JLabel("Doctor Name:");
        a13.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension size11 = a13.getPreferredSize();
        a13.setBounds(100, 500, size11.width, size11.height);
        panel.setLayout(null);
        panel.add(a13);

        JComboBox a14=new JComboBox(dctrName);
        a14.setSize(150,20);
        a14.setLocation(250, 500);
        panel.add(a14);

        JLabel  a15 = new JLabel("Notes:");
        a15.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension size12 = a15.getPreferredSize();
        a15.setBounds(100, 550, size12.width, size12.height);
        panel.setLayout(null);
        panel.add(a15);

        JTextField a16 = new JTextField();
        a16.setSize(150,20);
        a16.setLocation(250, 550);
        panel.add(a16);

        JLabel  a17 = new JLabel("Is this your first consultation?");
        a17.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeCalCost = a17.getPreferredSize();
        a17.setBounds(100, 580, sizeCalCost.width, sizeCalCost.height);
        panel.setLayout(null);
        panel.add(a17);

        JButton yes = new JButton("Yes");
        Dimension sizeYes = yes.getPreferredSize();
        yes.setBounds(400, 580, sizeYes.width, sizeYes.height);
        panel.add(yes);

        JButton no = new JButton("No");
        Dimension sizeNo = no.getPreferredSize();
        no.setBounds(450, 580, sizeNo.width, sizeNo.height);
        panel.add(no);

        JLabel  a18 = new JLabel("Cost :");
        a18.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizecost = a18.getPreferredSize();
        a18.setBounds(100, 620, sizecost.width, sizecost.height);
        panel.setLayout(null);
        panel.add(a18);

        JTextField tcost = new JTextField();
        tcost.setSize(150,20);
        tcost.setLocation(250, 620);
        panel.add(tcost);

        yes.addActionListener(e ->{
            int hourscal= Integer.parseInt(a11.getSelectedItem().toString());
            int finalCost=hourscal*15;
            tcost.setText(String.valueOf(finalCost));

        });
        no.addActionListener(e ->{
            int hourscalNo= Integer.parseInt(a11.getSelectedItem().toString());
            int finalCost=hourscalNo*25;
            tcost.setText(String.valueOf(finalCost));

        });

        //book dctr button
        JButton button = new JButton("Book Doctor");
        Dimension sizebook = button.getPreferredSize();
        button.setBounds(250, 655, sizebook.width, sizebook.height);
        panel.add(button);
        button.addActionListener(e ->
        {
            System.out.println("Successfully Booked!");
            JOptionPane.showMessageDialog(null, "Successfully Booked!");

            Consultation consultation1 = new Consultation();
            consultation1.setDctrName(a14.getSelectedItem().toString());
            consultation1.setDctrSurName(a14.getSelectedItem().toString());
            consultation1.setDate(a7.getText());
            consultation1.setStartTime(a9.getSelectedItem().toString());
            consultation1.setNotes(a16.getText());
            consultation1.setEndTime(tEndTime.getText());
            consultation1.setPtntName(b1.getText());
            consultation1.setPtnSurName(b2.getText());
            consultation1.setCost(Integer.parseInt(tcost.getText()));
            consultation1.setPtnDOB(b3.getText());
            consultation1.setPtnTel(b5.getText());

            if (consultations.isEmpty()){
                consultations.add(consultation1);
            }else {
                for (Consultation consultation:consultations) {
                    if (consultation.getDctrName().equals(consultation1.getDctrName())){
                        if (consultation.getDate().equals(consultation1.getDate())){
                            int c1= Integer.parseInt(consultation.getStartTime());
                            int c2= Integer.parseInt(consultation.getEndTime());
                            int d1= Integer.parseInt(consultation1.getEndTime());
                            int d2= Integer.parseInt(consultation1.getStartTime());
                            if (c1>d1 || c2<d2){
                                consultations.add(consultation1);
                            }else{
                                System.out.println("This doctor is booked!");
                                JOptionPane.showMessageDialog(null, "Already Booked. Try another time or doctor!");
                            }
                        }else{
                            consultations.add(consultation1);
                        }

                    }else {
                        consultations.add(consultation1);
                    }
                }
            }
        });

    }




    private static void viewChannel(java.util.List<Doctor> doctors, ArrayList<Consultation> consultations) {
                    JFrame onlyTable = new JFrame();
                    JPanel panelOnly = new JPanel();
                    onlyTable.getContentPane();
                    panelOnly.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    onlyTable.add(panelOnly);
                    onlyTable.setSize(1300, 800);
                    onlyTable.setVisible(true);

                    String[] cols = { "Patient Name","Booked Date", "Assigned Doctor", "Start Time", "End Time","Cost","Notes" };
                    DefaultTableModel tableM = new DefaultTableModel(cols, 0);
                    JTable table = new JTable(tableM);
                    JScrollPane sp = new JScrollPane(table);
                    onlyTable.add(sp);

                    ArrayList<Consultation> c1=new ArrayList<>();
                    for (Consultation consultation:consultations) {
                        c1.add(consultation);
                    }
                    for (Consultation consultation:c1) {
                        String patient = consultation.getPtntName()+" "+consultation.getPtnSurName();
                        String date = consultation.getDate();
                        String dctrName = consultation.getDctrName();
                        String Start_Time = consultation.getStartTime();
                        String End_Time = consultation.getEndTime();
                        int Cost = consultation.getCost();
                        String Notes = consultation.getNotes();

                        Object[] data = {patient, date, dctrName, Start_Time, End_Time, Cost, Notes};

                        tableM.addRow(data);

                    }
                }


}

