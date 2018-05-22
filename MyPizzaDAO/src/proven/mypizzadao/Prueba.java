/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizzadao;

import java.util.ArrayList;
import java.util.List;
import proven.modelo.*;

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
        Model m = Model.getInstance();       
        System.out.println("----------Testig model---------------");
        System.out.println("");
        System.out.println("Login:");
        System.out.println("Login ok: "+ m.login("a", "a"));
        System.out.println("Login error: "+ m.login("a", "b"));
        System.out.println("");
        System.out.println("Product:");
        System.out.println("Get all pizzas: "+m.getAllPizzas());
        System.out.println("Get ingredients from pizza: "+m.getIngredientsFromPizzaId(1));
        System.out.println("Get all ingredients: "+m.getAllIngredients());
        System.out.println("Get all drinks: "+m.getAllDrinks());
        System.out.println("Add a pizza: "+ m.addPizza(new Pizza("Pizza Queso", 13.95, "pqsa.jpj", 1), createListOfIngredients()));
        
    }

    private List<Ingrediente> createListOfIngredients() {
        List<Ingrediente> iList = new ArrayList<Ingrediente>();
        iList.add(new Ingrediente(1));
        iList.add(new Ingrediente(2));
        iList.add(new Ingrediente(3));
        iList.add(new Ingrediente(4));
        return iList;
    }
}
