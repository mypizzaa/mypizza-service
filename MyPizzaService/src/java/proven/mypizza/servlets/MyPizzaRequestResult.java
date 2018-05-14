/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizza.servlets;

public class MyPizzaRequestResult {
    private Object data;
    private int resultCode;
 
    public MyPizzaRequestResult(Object data, int resultCode) {
        this.data = data;
        this.resultCode = resultCode;
    }
 
    public Object getData() {
        return data;
    }
 
    public void setData(Object data) {
        this.data = data;
    }
 
    public int getResultCode() {
        return resultCode;
    }
 
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
 
    @Override
    public String toString() {
        return "MyPizzaRequestResult{" + "data=" + data + ", resultCode=" + resultCode + '}';
    }
 
}