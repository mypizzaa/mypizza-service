/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizzadao;

import java.sql.Time;
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
        Model m = new Model();
        System.out.println("--------------------Testig model--------------------");
        System.out.println("");
        System.out.println("/*************Login*************/");
        System.out.println("Login ok: "+ m.login("a", "a"));
        System.out.println("Login error: "+ m.login("a", "b"));
        System.out.println("Generate token: "+m.generateToken(new Usuario(1)));
        System.out.println("Validate token: "+m.validateUser("315612114121853181517111012131069"));
        System.out.println("");
        System.out.println("/*************Product*************/");
        System.out.println("Get all pizzas: "+m.getAllPizzas());
        System.out.println("Get ingredients from pizza: "+m.getIngredientsFromPizzaId(1));
        System.out.println("Get all ingredients: "+m.getAllIngredients());
        System.out.println("Get all drinks: "+m.getAllDrinks());
        System.out.println("Add a pizza: "+ m.addPizza(new Pizza("Pizza Queso", 13.95, "pqsa.jpj", 1), createListOfIngredients()));
        System.out.println("Add an ingredient: "+m.addIngredient(new Ingrediente("Ajo", 0.50, "ajo.png", 3)));
        System.out.println("Add a drink: "+m.addDrink(new Refresco("Fanta limon", 1.8, "flimon.png", 2)));
        System.out.println("Modify product: "+ m.modifyProductInfo(new Producto(4, "Atun", 0.50, "atun.jpg")));
        System.out.println("Add ingredients to pizza: "+m.addIgredientsToPizza(new Pizza(1), addIngedient()));
        System.out.println("Remove igredients from pizza: "+ m.removeIngredientFromPizza(new Pizza(12), removeIngredients()));
        System.out.println("Remove pizza: "+m.removePizza(new Pizza(12)));
        System.out.println("Remove ingrediente: "+m.removeIngrediente(new Ingrediente(1)));
        System.out.println("Remove drink: "+m.removeDrink(new Refresco(2)));
        System.out.println("");
        System.out.println("/*************Client*************/");
        System.out.println("List all clients: "+m.listAllClients());
        System.out.println("Find client by dni: "+ m.findClientByDni(new Cliente("21354234C")));
        System.out.println("Add client: "+ m.addClient(new Cliente("12642375A", "Joselito", "Manolito", "man123", "jmanolito", "cliente", "man@gmail.com", "c/ Santa Ana 34", null, "491248642", "Barcelona", "08901")));
        System.out.println("Check if exist: "+ m.checkIfExist(new Cliente("12642375A")));
        System.out.println("Modify password: "+ m.modifyPassword(new Cliente("12642375A", "pass2")));
        System.out.println("Modify client: "+m.modifyClient(new Cliente(15, "12642375A", "Joselito", "Manolito", "manolete", "jmanolito", "cliente", "man@gmail.com", "c/ Santa Ana 34", null, "491248642", "Barcelona", "08901")));
        System.out.println("Inactivate client: "+ m.inactivateClient(new Cliente("12642375A")));
        System.out.println("Find client by phone: "+m.findClienteByPhone("123456789"));
        System.out.println("");
        System.out.println("/*************Employee*************/");
        System.out.println("List all employees: "+m.listAllEmployees());
        System.out.println("Find employee: "+m.findEmployee(new Empleado("87542162P")));
        System.out.println("Add employee: "+m.addEmployee(new Empleado(Time.valueOf("9:00:00"), Time.valueOf("17:00:00"), 40, 1000, "42532264H", "Pepe", "El killo", "pep123", "pkillo", "empleado", "pep@gmail.com")));
        System.out.println("Update employee: "+m.updateEmployee(new Empleado(Time.valueOf("8:00:00"), Time.valueOf("17:00:00"), 45, 1200, 16, "42532264H", "Pepe", "Hernandez", "pep123", "pkillo", "empleado", "pep@gmail.com")));            
        System.out.println("Inactivate empleado: "+m.inactivateEmployee(new Empleado("42532264H")));
    }

    private List<Ingrediente> createListOfIngredients() {
        List<Ingrediente> iList = new ArrayList<Ingrediente>();
        iList.add(new Ingrediente(1));
        iList.add(new Ingrediente(2));
        iList.add(new Ingrediente(3));
        iList.add(new Ingrediente(4));
        return iList;
    }

    private List<Ingrediente> removeIngredients() {
        List<Ingrediente> iList = new ArrayList<Ingrediente>();
        iList.add(new Ingrediente(3));
        iList.add(new Ingrediente(4));
        return iList;
    }

    private List<Ingrediente> addIngedient() {
        List<Ingrediente> iList = new ArrayList<Ingrediente>();
        iList.add(new Ingrediente(5));
        iList.add(new Ingrediente(2));
        return iList;
    }    
}
