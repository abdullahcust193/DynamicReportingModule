import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class JTableToJasperReport extends JFrame implements ActionListener {
  
  private JTable table;
  private JButton generateReportButton;
  
  public JTableToJasperReport() {
    setTitle("JTable to JasperReport Example");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(500, 500);
    setLocationRelativeTo(null);
    JPanel panel = new JPanel();
    table = new JTable(new Object[][]{{"John", "Doe", "25"}, {"Jane", "Doe", "28"}}, 
                       new Object[]{"First Name", "Last Name", "Age"});
    JScrollPane scrollPane = new JScrollPane(table);
    generateReportButton = new JButton("Generate Report");
    generateReportButton.addActionListener(this);
    panel.add(scrollPane);
    panel.add(generateReportButton);
    add(panel);
    setVisible(true);
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == generateReportButton) {
      try {
        // Load the JRXML file
        JasperDesign design = JRXmlLoader.load("C:\\Users\\chabd\\OneDrive\\Documents\\GitHub\\DynamicReportingModule\\src\\main\\java\\DR_GUI\\report1.jrxml");
        // Create fields for the report
        JRDesignField firstNameField = new JRDesignField();
        firstNameField.setName("firstName");
        firstNameField.setValueClass(String.class);
        JRDesignField lastNameField = new JRDesignField();
        lastNameField.setName("lastName");
        lastNameField.setValueClass(String.class);
        JRDesignField ageField = new JRDesignField();
        ageField.setName("age");
        ageField.setValueClass(String.class);
        // Add fields to the report
        design.addField(firstNameField);
        design.addField(lastNameField);
        design.addField(ageField);
        
        
        
        
        // Create the SQL query for the report
        String query = "SELECT firstName, lastName, age FROM people";
        JRDesignQuery jrQuery = new JRDesignQuery();
        jrQuery.setText(query);
        design.setQuery(jrQuery);
        
        
        // Compile the JRXML file
        JasperReport report = net.sf.jasperreports.engine.JasperCompileManager.compileReport(design);
        // Create a HashMap to hold the report parameters
        HashMap<String, Object> parameters = new HashMap<>();
        // Set the data source for the report
        JRTableModelDataSource dataSource = new JRTableModelDataSource((javax.swing.table.TableModel) table.getModel());
        // Fill the report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
        // Show the report in a viewer
        JasperViewer.viewReport(jasperPrint, false);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
   

}
