import java.awt.event.ActionEvent;
import javax.swing.*;

public class GameMenu {
   private JMenuBar menuBar = new JMenuBar();
   private GameControl control;

   @SuppressWarnings("serial")
   public GameMenu(GameControl cntrl) {
      this.control = cntrl;

      JMenu menu = new JMenu("Options");
      menu.add(new JMenuItem(new AbstractAction("Restart") {
         public void actionPerformed(ActionEvent ae) {
            if (control != null) {
               control.restart(ae);
            }
         }
      }));
      menuBar.add(menu);
   }

   public JMenuBar getMenuBar() {
      return menuBar;
   }
}
