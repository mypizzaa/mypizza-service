/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizzadao;

/**
 *
 * @author alumne
 */
public class NewClass {
   public static void main(String[] args) {
        NewClass myApp = new NewClass();
        myApp.run();
        
    }

    private void run() {
        Model m = new Model();
        System.out.println( m.login("123", "123"));
        System.out.println(m.getAllPizzas());
    }
}
