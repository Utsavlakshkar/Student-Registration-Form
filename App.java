package p1.javafxjdbc1;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.sql.*;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 * @author Utsav Lakshkar 20BCE0933
 */
public class App extends Application {
    Label l1 = new Label("Name");
    TextField tf1 = new TextField();
    Label l2 = new Label("Registration Number:");
    TextField tf2 = new TextField();
    Label l3 = new Label("Gender");
    TextField tf3 = new TextField();
    RadioButton rb1 = new RadioButton("Male");
    RadioButton rb2 = new RadioButton("Female");
    final ToggleGroup group = new ToggleGroup();        
    Label l4 = new Label("Interests");
    ArrayList<String> al = new ArrayList<>();
    CheckBox cb1 = new CheckBox("Programming");
    CheckBox cb2 = new CheckBox("Gaming");
    Label l5 = new Label("Username");
    TextField tf4 = new TextField();
    Label l6 = new Label("Password");
    PasswordField pf = new PasswordField();
    ScrollBar sb = new ScrollBar();
    Menu m = new Menu("Select Operation");
    MenuBar mb=new MenuBar();    
    Label l7 = new Label("Address");
    TextArea ta = new TextArea();
    Label l8 = new Label("Date of Birth");
    DatePicker dp = new DatePicker();
    Label l9 = new Label("Upload Resume");
    FileChooser fc = new FileChooser();
    Button b = new Button("Browse");
    TextField tf5 = new TextField();
    Button submit = new Button("Submit");    
    Label l10 = new Label("Annual Income(in Lakhs)");    
    TextField tf6 = new TextField();    
    GridPane fp = new GridPane();
    GridPane gp=new GridPane();
    GridPane gp1=new GridPane();
    GridPane gp2=new GridPane();    
    @Override
    public void start(Stage primaryStage) {        
        mb.autosize();        
        Label l = new Label("Utsav Lakshkar 20BCE0933");
        VBox vb = new VBox(mb,l);
        vb.setAlignment(Pos.CENTER);
        MenuItem m1=new MenuItem("Create");
        MenuItem m2=new MenuItem("Read");
        MenuItem m3=new MenuItem("Update");
        MenuItem m4=new MenuItem("Delete");        
        m.getItems().addAll(m1,m2,m3,m4);                                        
        m1.setOnAction((ActionEvent e) -> {
            vb.getChildren().remove(gp);
            vb.getChildren().remove(gp1);
            vb.getChildren().remove(gp2);
            vb.getChildren().add(fp);
            tf1.setPromptText("Enter Name");
            tf2.setPromptText("Enter Registration Number");
            tf4.setPromptText("Enter Username");
            pf.setPromptText("Enter Password");
            rb1.setToggleGroup(group);
            rb1.setUserData("Male");
            rb2.setToggleGroup(group);
            rb2.setUserData("Female");
            ta.setPromptText("Enter Address");
            dp.setPromptText("Choose a Date");
            rb1.setOnAction((ActionEvent t) -> {
                tf4.setText(tf2.getText());
                tf3.setText(group.getSelectedToggle().getUserData().toString());
            });
            
            rb2.setOnAction((ActionEvent t) -> {
                tf4.setText(tf2.getText());
                tf3.setText(group.getSelectedToggle().getUserData().toString());
            });
            
            cb1.setOnAction((ActionEvent t) -> {
                al.add(cb1.getText());
            });
            cb2.setOnAction((ActionEvent t) -> {
                al.add(cb2.getText());
            });
            b.setOnAction((ActionEvent t) -> {
                fc.setTitle("Resume Upload");
                tf5.setText(fc.showOpenDialog(primaryStage).getName());
            });
            sb.setMin(0);
            sb.setMax(10);
            sb.setUnitIncrement(1);
            
            submit.setOnAction((ActionEvent t) -> {
                tf6.setText(Integer.toString((int)sb.getValue()));
                String name=tf1.getText();
                String reg=tf2.getText();
                String gender=tf3.getText();
                ArrayList<String> inter=al;
                String user=tf4.getText();
                String pass=pf.getText();
                String income=tf6.getText();
                String add=ta.getText();
                String dob=dp.getValue().toString();
                String file=tf5.getText();
                System.out.println("\n\nUtsav Lakshkar 20BCE0933");
                System.out.println("Form Submitted Successfully");
                insertdb(name,reg,gender,inter,user,pass,income,add,dob,file);
                System.out.println("Name: " + name);
                System.out.println("Registration Number: " + reg);
                System.out.println("Gender: " + gender);
                System.out.println("Interests: " + al);
                System.out.println("Username: " + user);
                System.out.println("Password: " + pass);
                System.out.println("Income(in lakhs): " + income);
                System.out.println("Address: " + add);
                System.out.println("DOB: " + dob);
                System.out.println("File Uploaded: " + file);
            });
            
            fp.setHgap(10);
            fp.add(l1,0,1);
            fp.add(tf1,1,1);
            fp.add(l2,0,2);
            fp.add(tf2,1,2);
            fp.add(l3,0,3);
            fp.add(rb1,1,3);
            fp.add(rb2,2,3);
            fp.add(l4,0,4);
            fp.add(cb1,1,4);
            fp.add(cb2,2,4);
            fp.add(l5,0,5);
            fp.add(tf4,1,5);
            fp.add(l6,0,6);
            fp.add(pf,1,6);
            fp.add(l7,0,7);
            fp.add(ta,1,7);
            fp.add(l8,0,8);
            fp.add(dp,1,8);
            fp.add(l9,0,9);
            fp.add(b,1,9);
            fp.add(l10,0,10);
            fp.add(sb,1,10);
            fp.add(submit,1,11);
            fp.setAlignment(Pos.CENTER);
            fp.setVgap(10);
            fp.setStyle("-fx-background-color:AQUA;-fx-border-color:BLACK;-fx-border-width:10px;");
        });                
        m2.setOnAction((ActionEvent t) -> {
            vb.getChildren().remove(fp);
            vb.getChildren().remove(gp1);
            vb.getChildren().remove(gp2);            
            vb.getChildren().add(gp);            
            Label l11 = new Label("Username");
            TextField tf7=new TextField();
            Label l12 = new Label("Password");
            PasswordField tf8=new PasswordField();            
            Button login=new Button("Login");
            gp.add(l11,0,0);
            gp.add(tf7,1,0);
            gp.add(l12,0,1);
            gp.add(tf8,1,1);
            gp.add(login,0,2);
            login.setOnAction((ActionEvent e) -> {
                String user=tf7.getText();
                String pass=tf8.getText();            
                System.out.println("\nEntered username: "+user);
                System.out.println("Entered password: "+pass);
                boolean valid=validate(user,pass);
                if(valid==true)
                    display(user);
                else
                    System.out.println("Record Not Found");
            });
            gp.setAlignment(Pos.CENTER);
            gp.setVgap(10);
            gp.setStyle("-fx-background-color:AQUA;-fx-border-color:BLACK;-fx-border-width:10px;");            
        });
        m3.setOnAction((ActionEvent t)-> {
            vb.getChildren().remove(fp);
            vb.getChildren().remove(gp);
            vb.getChildren().remove(gp2);            
            vb.getChildren().add(gp1);
            Label l11 = new Label("Username");
            TextField tf7=new TextField();
            Label l12 = new Label("Password");
            PasswordField tf8=new PasswordField();
            Label l13 = new Label("New Password");
            PasswordField tf9=new PasswordField();
            Label l14 = new Label("Confirm New Password");
            PasswordField tf10=new PasswordField();            
            Button login=new Button("Login");
            Button update=new Button("Update");            
            gp1.add(l11,0,0);
            gp1.add(tf7,1,0);
            gp1.add(l12,0,1);
            gp1.add(tf8,1,1);
            gp1.add(login,0,2);
            login.setOnAction((ActionEvent e) -> {
                String user=tf7.getText();
                String pass=tf8.getText();
                System.out.println("\nEntered username: "+user);
                System.out.println("Entered password: "+pass);
                boolean valid=validate(user,pass);
                
                if(valid==true){
                    display(user);
                    gp1.add(l13,0,3);
                    gp1.add(tf9,1,3);
                    gp1.add(l14,0,4);
                    gp1.add(tf10,1,4);                    
                }                                
            });
            boolean match=tf9.getText().equals(tf10.getText());                    
            if(match==true)
                gp1.add(update,0,5);
            else
                System.out.println("Password Mismatch");
            update.setOnAction((ActionEvent e1) -> {                    
                updatedb(tf7.getText(),tf9.getText());
            });
            gp1.setAlignment(Pos.CENTER);
            gp1.setVgap(10);
            gp1.setStyle("-fx-background-color:AQUA;-fx-border-color:BLACK;-fx-border-width:10px;");
        });
        m4.setOnAction((ActionEvent e) -> {
            vb.getChildren().remove(fp);
            vb.getChildren().remove(gp);
            vb.getChildren().remove(gp1);
            vb.getChildren().add(gp2);
            Label l11 = new Label("Username");
            TextField tf7 = new TextField();
            Label l12 = new Label("Password");
            PasswordField tf8 = new PasswordField();
            Label l13 = new Label("New Password");
            PasswordField tf9=new PasswordField();
            Label l14 = new Label("Confirm New Password");
            PasswordField tf10=new PasswordField();                        
            Button login = new Button("Login");
            Button delete=new Button("Delete");                        
            gp2.add(l11, 0, 0);
            gp2.add(tf7, 1, 0);
            gp2.add(l12, 0, 1);
            gp2.add(tf8, 1, 1);
            gp2.add(login, 0, 2);
            delete.setOnAction((ActionEvent e1) -> {
                String user=tf7.getText();
                String pass=tf8.getText();
                System.out.println("\nEntered username: "+user);
                System.out.println("Entered password: "+pass);
                boolean valid=validate(user,pass);
                if(valid==true){
                    display(user);
                    gp2.add(l13,0,3);
                    gp2.add(tf9,1,3);
                    gp2.add(l14,0,4);
                    gp2.add(tf10,1,4);
                    if(tf9.getText().equals(tf10.getText())==true)
                        gp2.add(delete,0,5);
                    else
                        System.out.println("Password Mismatch");
                }
                delete.setOnAction((ActionEvent e2) -> {
                    deletedb(tf7.getText());
                });
            });
            gp2.setAlignment(Pos.CENTER);
            gp2.setVgap(10);
            gp2.setStyle("-fx-background-color:AQUA;-fx-border-color:BLACK;-fx-border-width:10px;");
            });
        mb.getMenus().add(m);
        Scene scene = new Scene(vb, 1000, 1250);        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(primScreenBounds.getWidth());
        primaryStage.setHeight(primScreenBounds.getHeight());        
        primaryStage.setX(primScreenBounds.getMinX());
        primaryStage.setY(primScreenBounds.getMinY());                
        primaryStage.setTitle("Registration Form");
        primaryStage.setScene(scene);
        primaryStage.show();        
    }
    public static void main(String[] args) {
        launch();
    }

    private void insertdb(String name, String reg, String gender, ArrayList<String> inter, String user, String pass, String income, String add, String dob, String file) {        
        String url="jdbc:oracle:thin:@localhost:1521:XE";
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(url,"utsav","utsavc403");        
            Statement stmt=con.createStatement();                                         
            int arr[] = new int[inter.size()];
            String in[]={"Programming","Gaming"};            
            for(int i=0;i<inter.size();i++){
                String s=inter.get(i);
                for(int j=0;j<2;j++){
                    if(s.equals(in[j]))
                        arr[i]=j+1;
                }                
                stmt.executeUpdate("insert into Form933 values("+"'"+name+"'"+","+"'"+reg+"'"+","+"'"+gender+"'"+","+arr[i]+","+"'"+user+"'"+","+"'"+pass+"'"+","+income+","+"'"+add+"'"+","+"'"+dob+"'"+","+"'"+file+"'"+")");                
                System.out.println(arr[i]);
            }
            display(reg);
            con.close();
        }
        catch(ClassNotFoundException e){ 
            System.out.println("Driver Not Found"+e);
        }        
        catch(SQLException e){ 
            System.out.println(e);
        }
    }
    
    private boolean validate(String user,String pass) {        
        boolean valid=false;
        String url="jdbc:oracle:thin:@localhost:1521:XE";
        try
        {            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(url,"utsav","utsavc403");            
            Statement stmt=con.createStatement();                                                                 
            ResultSet rs=stmt.executeQuery("select username,password from Form933");            
            while(rs.next())
            {                                
                if((rs.getString(1).equals(user))&&(rs.getString(2).equals(pass)))
                    valid=true;
            }
            if(valid==true){
                System.out.println("Login Successful");            
            }            
            con.close();
        }
        catch(ClassNotFoundException e){ 
            System.out.println("Driver Not Found"+e);
        }        
        catch(SQLException e){ 
            System.out.println(e);
        }
        return valid;
    }
    
    private void updatedb(String user,String pass) {        
        String url="jdbc:oracle:thin:@localhost:1521:XE";
        try
        {            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(url,"utsav","utsavc403");            
            Statement stmt=con.createStatement();                                                                 
            stmt.executeQuery("update Form933 set password='"+pass+"' where username='"+user+"'");            
            System.out.println("Records updated successfully");
            display(user);            
            con.close();
        }
        catch(ClassNotFoundException e){ 
            System.out.println("Driver Not Found"+e);
        }        
        catch(SQLException e){ 
            System.out.println(e);
        }
    }
    
    private void deletedb(String user) {        
        String url="jdbc:oracle:thin:@localhost:1521:XE";
        try
        {            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(url,"utsav","utsavc403");            
            Statement stmt=con.createStatement();                                                                 
            stmt.executeQuery("delete from Form933 where username='"+user+"'");            
            System.out.println("Records deleted successfully");
            display(user);            
            con.close();
        }
        catch(ClassNotFoundException e){ 
            System.out.println("Driver Not Found"+e);
        }        
        catch(SQLException e){ 
            System.out.println(e);
        }
    }
    
    private void display(String reg) {                
        String url="jdbc:oracle:thin:@localhost:1521:XE";
        try
        {            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(url,"utsav","utsavc403");            
            Statement stmt=con.createStatement();                                                     
            System.out.println("\nRecords are:");            
            ResultSet rs1=stmt.executeQuery("select * from Form933 where regno='"+reg+"'");            
            while(rs1.next())
            {                
                System.out.println(rs1.getString(1)+" "+rs1.getString(2)+" "+rs1.getString(3)+" "+rs1.getInt(4)+" "+rs1.getString(5)+" "+rs1.getString(6)+" "+rs1.getInt(7)+" "+rs1.getString(8)+" "+rs1.getString(9)+" "+rs1.getString(10));                    
            }                        
            con.close();
        }
        catch(ClassNotFoundException e){ 
            System.out.println("Driver Not Found"+e);
        }        
        catch(SQLException e){ 
            System.out.println(e);
        }        
    }
}