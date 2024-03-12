import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;

public class Gui extends JFrame{
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtDate;
    private JCheckBox checkBox;
    private JButton prevBtn;
    private JButton nextBtn;
    private JPanel panel;
    private int index = 0;
    private List<Zamestnanec> zamestnanci = new ArrayList<>();
    public Gui() {
        components();
        menu();
        nextBtn.addActionListener(e -> next());
        prevBtn.addActionListener(e -> prev());
    }
    public void menu() {
        JMenuBar jmb = new JMenuBar();
        setJMenuBar(jmb);

        JMenu jm = new JMenu("Nastavení");
        jmb.add(jm);

        JMenuItem loadFile = new JMenuItem("načítej negříčku");
        jm.add(loadFile);
        loadFile.addActionListener(e-> chooseFile());
    }
    public void components() {
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Idk");
        setBounds(700,300,400,300);
    }
    public void prev() {
        if(index > 0) {
            index--;
            display(getZamestnanec(index));
        }
    }
    public void next() {
        if(index < zamestnanci.size()){
            index++;
            display(getZamestnanec(index));
        }
    }
    public void chooseFile(){
        JFileChooser fc = new JFileChooser(".");
        fc.setFileFilter(new FileNameExtensionFilter("Project files", "txt"));
        int result = fc.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            File selected = fc.getSelectedFile();
            readFile(selected);
        }
    }
    public Zamestnanec getZamestnanec(int i){
        return zamestnanci.get(i);
    }
    public void display(Zamestnanec zamestnanec) {
        txtName.setText(zamestnanec.getName());
        txtSurname.setText(zamestnanec.getSurname());
        checkBox.setSelected(zamestnanec.isInsurance());
        txtDate.setText(String.valueOf(zamestnanec.getDateOfBirth()));
    }
    public void readFile(File selectedFile) {
        index = 0;
        zamestnanci.clear();
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(selectedFile)))) {
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split(":");
                String name = parts[0];
                String surname = parts[1];
                boolean insurance = parts[2].equals("ano");
                LocalDate dateOfBirth = LocalDate.parse(parts[3]);
                zamestnanci.add(new Zamestnanec(name, surname, insurance, dateOfBirth));
                display(getZamestnanec(index));
            }
        }catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File not found: "+e.getLocalizedMessage());
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Problem with number format: "+e.getLocalizedMessage());
        }catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Problem with date format: "+e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        Gui g = new Gui();
        g.setVisible(true);
    }
}
