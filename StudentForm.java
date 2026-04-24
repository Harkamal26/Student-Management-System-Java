import java.awt.*;
import java.awt.event.*;

public class StudentForm extends Frame implements ActionListener {

    TextField nameField, ageField, courseField, subjectCountField;
    TextField searchField, idField;
    TextArea area;

    Button addBtn, viewBtn, deleteBtn, searchBtn, updateBtn;

    StudentForm() {

        setTitle("Student Management System");
        setLayout(new BorderLayout());

        // ===== FORM PANEL =====
        Panel form = new Panel(new GridLayout(6, 2, 10, 10));
        form.setBackground(new Color(230, 240, 255));

        form.add(new Label("Name:"));
        nameField = new TextField();
        form.add(nameField);

        form.add(new Label("Age:"));
        ageField = new TextField();
        form.add(ageField);

        form.add(new Label("Course:"));
        courseField = new TextField();
        form.add(courseField);

        form.add(new Label("No. of Subjects:"));
        subjectCountField = new TextField();
        form.add(subjectCountField);

        form.add(new Label("Search Name:"));
        searchField = new TextField();
        form.add(searchField);

        form.add(new Label("Student ID:"));
        idField = new TextField();
        form.add(idField);

        add(form, BorderLayout.NORTH);

        // ===== BUTTON PANEL =====
        Panel buttons = new Panel();
        buttons.setBackground(new Color(200, 220, 255));

        addBtn = new Button("Add");
        viewBtn = new Button("View");
        searchBtn = new Button("Search");
        updateBtn = new Button("Update");
        deleteBtn = new Button("Delete");

        addBtn.setBackground(Color.GREEN);
        viewBtn.setBackground(Color.CYAN);
        searchBtn.setBackground(Color.ORANGE);
        updateBtn.setBackground(Color.YELLOW);
        deleteBtn.setBackground(Color.PINK);

        buttons.add(addBtn);
        buttons.add(viewBtn);
        buttons.add(searchBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);

        add(buttons, BorderLayout.CENTER);

        // ===== OUTPUT =====
        area = new TextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(area, BorderLayout.SOUTH);

        // EVENTS
        addBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);

        setSize(550, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // ===== ADD STUDENT WITH SUBJECTS =====
        if (e.getSource() == addBtn) {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String course = courseField.getText();

                int n = Integer.parseInt(subjectCountField.getText());

                String subjects = "";
                int total = 0;

                for (int i = 1; i <= n; i++) {

                    String sub = javax.swing.JOptionPane.showInputDialog("Enter Subject " + i);
                    int marks = Integer.parseInt(
                        javax.swing.JOptionPane.showInputDialog("Marks for " + sub)
                    );

                    subjects += sub + "-" + marks;
                    if (i != n) subjects += "|";

                    total += marks;
                }

                double percentage = total / (double) n;
                String result = (percentage >= 40) ? "Pass" : "Fail";

                StudentDAO.addStudent(name, age, course,
                        subjects, total, percentage, result);

                area.setText("✅ Student Added with Result\nPercentage: " + percentage);

            } catch (Exception ex) {
                area.setText("❌ Invalid Input!");
            }
        }

        // ===== VIEW =====
        if (e.getSource() == viewBtn) {
            java.util.List<String> list = StudentDAO.getAllStudents();
            area.setText("");
            for (String s : list) area.append(s + "\n");
        }

        // ===== SEARCH =====
        if (e.getSource() == searchBtn) {
    try {
        int id = Integer.parseInt(idField.getText());
        String data = StudentDAO.searchStudentById(id);
        area.setText(data);
    } catch (Exception ex) {
        area.setText("❌ Enter valid ID");
    }
}

        // ===== DELETE =====
        if (e.getSource() == deleteBtn) {
    try {
        int id = Integer.parseInt(idField.getText());

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            null, "Delete this student?"
        );

        if (confirm == 0) {
            StudentDAO.deleteStudent(id);
            area.setText("🗑️ Deleted ID: " + id);
        }

    } catch (Exception ex) {
        area.setText("❌ Invalid ID");
    }
}

        // ===== UPDATE NAME =====
        if (e.getSource() == updateBtn) {
    try {
        int id = Integer.parseInt(idField.getText());

        String field = javax.swing.JOptionPane.showInputDialog(
            "What to update? (name/age/course)"
        );

        String value = javax.swing.JOptionPane.showInputDialog(
            "Enter new value"
        );

        StudentDAO.updateStudent(id, field, value);

        area.setText("✏️ Updated!");

    } catch (Exception ex) {
        area.setText("❌ Update failed!");
    }
}
    }
}