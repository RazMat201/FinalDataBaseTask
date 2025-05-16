package SmartBuyTask.SmartBuyTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest 
{
 Connection con ;
 Statement stmt ;
 ResultSet rs;
 String customerfirstname;
 String customerLastName;
 String postalcode;
 String email;
 String password;
 WebDriver driver = new ChromeDriver();
  @BeforeTest
  public void MySetUp () throws SQLException {
   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","12345");
   driver.get("https://smartbuy-me.com/account/register");
  }

@Test(priority=1,enabled=false)
public void InsertToDataBase() throws SQLException {
 String Query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (999, 'New Corp', 'Smith', 'John', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00);";
 stmt=con.createStatement();
 int effectedrow = stmt.executeUpdate(Query);
 System.out.print(effectedrow);
}
@Test(priority=2)
public void updateDataBase() throws SQLException {
 String Query = "UPDATE customers SET creditLimit = 75000 WHERE customerNumber = 999;";
 stmt=con.createStatement();
 int effectedrow = stmt.executeUpdate(Query);
 System.out.print(effectedrow);
}
@Test(priority=3)
public void ReadDataBase() throws SQLException {
 String Query = "SELECT * FROM customers WHERE customerNumber = 103;";
 stmt=con.createStatement();
 rs= stmt.executeQuery(Query);
 while (rs.next()) {
  
   customerfirstname = rs.getNString("contactFirstName");
   customerLastName=rs.getNString("contactLastName");
   postalcode=rs.getNString("postalCode");
   email = customerfirstname+customerLastName+"@gmail.com";
   password = customerfirstname+postalcode;
   System.out.print(password);

 }
 driver.findElement(By.id("customer[first_name]")).sendKeys(customerfirstname);
 driver.findElement(By.id("customer[last_name]")).sendKeys(customerLastName);
 driver.findElement(By.id("customer[email]")).sendKeys(email);
 driver.findElement(By.id("customer[password]")).sendKeys(password);

}
@Test(priority=4)
public void DeleteDataBase() throws SQLException {
 
    String check = "SELECT * FROM customers WHERE customerNumber = 999;";
    stmt = con.createStatement();
    rs = stmt.executeQuery(check);


 if (rs.next()) {
    String query = "DELETE FROM customers WHERE customerNumber = 999;";
    int affectedRows = stmt.executeUpdate(query);
    System.out.println(affectedRows); } 
 else {
  System.out.println("The customer info doesn't exist");
 }


}


}