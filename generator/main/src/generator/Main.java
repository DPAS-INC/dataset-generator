package generator;

// Main class which is run at the command line
public class Main {

   public static void main(String[] args) {

      System.out.println("Dataset Generator loading...");

      //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
      try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new Form().setVisible(true);
         }
      });// </editor-fold>

      System.out.println("Dataset Generator loaded");
   }
}


