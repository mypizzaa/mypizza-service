/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizzadao;

import proven.modelo.Cliente;

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
        System.out.println(m.getAllIngredients());
        System.out.println(m.getAllDrinks());
        System.out.println(m.getIngredientsFromPizzaId(1));
        
        System.out.println("-------------------------------------------------");
        System.out.println(m.addClient(new Cliente("1234678Z", "Yo", "Apellido1 apellido2", "123", "img.png", "client", "yo", "213445", "d1", "d2")));
     
    }
}
