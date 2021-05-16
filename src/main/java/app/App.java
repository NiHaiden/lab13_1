/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;


import db.KurssystemService;
import exception.KursDBException;

/**
 * @author 20160609
 */
public class App {
    public static void main(String[] args) throws KursDBException {
        System.out.println(KurssystemService.getINSTANCE().getKurse().get(0).getKunden());
    }

}
