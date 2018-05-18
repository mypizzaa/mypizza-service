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
public class Prueba {

    public static void main(String[] args) {
        Prueba myApp = new Prueba();
        myApp.run();
    }

    private void run() {
        Model m = new Model();
        System.out.println("Login: " + m.login("123", "123"));
        System.out.println("All pizzas: " + m.getAllPizzas());
        System.out.println("All ingredients: " + m.getAllIngredients());
        System.out.println("All drinks: " + m.getAllDrinks());
        System.out.println("Ingredients by pizza: " + m.getIngredientsFromPizzaId(1));

        System.out.println("-------------------------------------------------");
        System.out.println("Add Client: " + m.addClient(new Cliente("1234678Z", "Yo", "Apellido1 apellido2", "123", "img.png", "client", "yo", "213445", "d1", "d2")));
        System.out.println("Change password: " + m.changePassword(new Cliente("a", "b")));
        System.out.println("Exist user: " + m.checkIfExist(new Cliente("a")));
        System.out.println("Inactivate client: " + m.inactivateClient(new Cliente("yo")));

    }
}
