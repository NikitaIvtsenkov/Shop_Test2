/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptv21shopivtsenkov;

import entity.Customer;
import entity.History;
import entity.Product;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
import managers.CustomerManager;
import managers.HistoryManager;
import managers.ProductManager;

/**
 *
 * @author Nikita
 */
public class App {
    private Product[] products;
    private Customer[] customers;
    History histories[];
    private final HistoryManager historyManager;
    
public App(){
    this.products = new Product[0];
    this.customers = new Customer[0];
    this.histories= new History[0];
    historyManager = new HistoryManager();
}
    
    private boolean repeat;
    public void run(){
        boolean repeat = true;
        Scanner scanner = new Scanner(System.in);
   
    do{
        System.out.println("Функции приложения");
        System.out.println("0.Выход из программы");
        System.out.println("1.Добавить продукт");
        System.out.println("2.Список продаваемых продуктов");
        System.out.println("3.Добавить покупателя");
        System.out.println("4.Список зарегистрированных покупателей");
        System.out.println("5.Покупка покупателем продукта");
        System.out.println("6.Доход магазина за все время работы");
        System.out.println("7.Добавить денег покупателю");
        System.out.println("8.Добавить количество");
        System.out.print("Выберите номер задачи: ");
        int task = scanner.nextInt();
        scanner.nextLine();
        System.out.println("_____________________________");
        switch(task){
            case 0:
                repeat = false;
                break;
            case 1:
                System.out.println("1.Добавить продукт: ");
                ProductManager productManager = new ProductManager();
                this.products = Arrays.copyOf(this.products, this.products.length+1);
                this.products[this.products.length-1] = productManager.addProduct();
                break;
            case 2:
                System.out.println("2.Список продаваемых продуктов: ");
                for (int i = 0; i<products.length;i++){
                    Product producti = products[i];
                    System.out.printf(i+1+". Название продукта: %s%n", producti.getName());
                    System.out.printf("Цена продукта: €%s%n", producti.getPrice());
                    System.out.printf("Количество продукта: %s%n", producti.getQuantity());
                    System.out.println();
                }
                break;
            case 3:
                System.out.println("3.Добавить покупателя: ");
                CustomerManager customerManager = new CustomerManager();
                this.customers = Arrays.copyOf(this.customers, this.customers.length+1);
                this.customers[this.customers.length-1] = customerManager.createCustomer();
                break;
            case 4:
                System.out.println("4.Список зарегистрированных покупателей: "); 
                for (int i = 0; i<customers.length;i++){
                    Customer customer = customers[i];
                    System.out.printf(i+1+". Имя покупателя: %s%n", customer.getFirstname());
                    System.out.printf("Фамилия покупателя: %s%n", customer.getLastname());
                    System.out.printf("Деньги покупателя: €%s%n", customer.getMoney());
                    System.out.println();
                }
                break;
            case 5:
               System.out.println("5. Покупка продукта");
                    addHistory(historyManager.takeOnProduct(products, customers));
                    break;
            case 6:
                System.out.println("6. Доход за все время");
                System.out.println("Доход за все время: €"+historyManager.getProfite(histories));
                break;
            case 7:
                System.out.println("7.Добавить денег покупателю");
                 for (int i = 0; i<customers.length;i++){
                    Customer customer = customers[i];
                    System.out.printf(i+1+". Имя покупателя: %s%n", customer.getFirstname());
                    System.out.printf("Фамилия покупателя: %s%n", customer.getLastname());
                    System.out.printf("Деньги покупателя: €%s%n", customer.getMoney());
                    System.out.println();
                 }
                System.out.print("Выберите покупателя для изменения: ");
                int num1 = scanner.nextInt();
                System.out.print("Введите количество денег, которое хотите добавить: ");
                int num2 = scanner.nextInt();
                int moneyy = customers[num1-1].getMoney() + num2;
                        customers[num1-1].setMoney(moneyy);
                break;
            case 8:
                System.out.println("8. Список продуктов");
                for (int i = 0; i<products.length;i++){ 
                    Product product = products[i];
                    System.out.printf(i+1+". Название продукта: %s%n", product.getName());
                    System.out.printf("Цена продукта: €%s%n", product.getPrice());
                    System.out.printf("Количество продукта до изменения: %s%n", product.getQuantity());
                    System.out.println();
                }
                    System.out.println("Добавить количество товара");
                    System.out.print("Введите номер продукта, который хотите изменить: ");
                    int numproduct= scanner.nextInt();
                    System.out.print("Введите новое количество товара: ");
                    int numqe = scanner.nextInt();
                    int quantityproductnew = this.products[numproduct-1].getQuantity()+ numqe;
                    products[numproduct-1].setQuantity(quantityproductnew);
                    break;
            default:
                    System.out.println("Выберите задание из списка: ");
    }
            System.out.println("_____________________________");
        }while(repeat);
        System.out.println("До свидания!");
    }
private void addHistory(History histories) {
        this.histories = Arrays.copyOf(this.histories, this.histories.length+1);
        this.histories[this.histories.length - 1] = histories; 
}
}
