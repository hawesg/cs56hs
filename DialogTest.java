import javax.swing.*;
public class DialogTest{
	public DialogTest(){
		JTextField name = new JTextField();
		String[] choices = { "Male", "Female"};
		JComboBox sex = new JComboBox(choices);
		final JComponent[] inputs = new JComponent[] {
		                new JLabel("Name"),
		                name,
		                new JLabel("Gender"),
		                sex
		};
		JOptionPane.showMessageDialog(null, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
		System.out.println("You entered " +
		                firstName.getText() + ", " +
		                (String)sex.getSelectedItem());
	}
		public static void main(String[] args) {
			DialogTest x = new DialogTest();
		}
}

