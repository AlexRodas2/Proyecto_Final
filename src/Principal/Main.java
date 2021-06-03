/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;
import GUI.JFPrincipal;
/**
 *
 * @author Usuario
 */
public class Main {

    //se manda a mostrar JF Principal/
    public static void main(String[] args) {
          JFPrincipal frmprincipal = new JFPrincipal();
		frmprincipal.setExtendedState(frmprincipal.MAXIMIZED_BOTH);
		frmprincipal.show();
    }
    
}
