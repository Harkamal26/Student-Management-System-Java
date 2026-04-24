import java.awt.*;
import java.awt.event.*;

public class LoginForm extends Frame implements ActionListener {

    TextField userField, passField;
    Button loginBtn, registerBtn;
    Label msg;

    LoginForm() {
        setTitle("Login System");

        setLayout(new GridBagLayout()); // center layout
        setBackground(new Color(240, 248, 255));

        Panel panel = new Panel(new GridLayout(4, 2, 8, 8));
        panel.setBackground(Color.WHITE);

        panel.add(new Label("Username:"));
        userField = new TextField(15);
        panel.add(userField);

        panel.add(new Label("Password:"));
        passField = new TextField(15);
        passField.setEchoChar('*');
        panel.add(passField);

        loginBtn = new Button("Login");
        registerBtn = new Button("Register");

        panel.add(loginBtn);
        panel.add(registerBtn);

        msg = new Label("");
        panel.add(msg);

        add(panel); // centered automatically

        loginBtn.addActionListener(this);
        registerBtn.addActionListener(this);

        setSize(300, 200);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

        String user = userField.getText().trim();
        String pass = passField.getText().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            msg.setText("❌ Fields cannot be empty!");
            return;
        }

        if (e.getSource() == loginBtn) {
            if (StudentDAO.login(user, pass)) {
                msg.setText("✅ Login Success");
                new StudentForm();
                dispose();
            } else {
                msg.setText("❌ Invalid Login");
            }
        }

        if (e.getSource() == registerBtn) {
            if (StudentDAO.register(user, pass)) {
                msg.setText("✅ Registered!");
            } else {
                msg.setText("❌ Username exists!");
            }
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}