/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Ghofrane
 */
public class ObjectHolder {
    
    public static void setInstance(ObjectHolder instance) {
        ObjectHolder.instance = instance;
    }
    
        
  static ObjectHolder instance;
  private int etat_button_ajouter=0;

    public int getEtat_button_ajouter() {
        return etat_button_ajouter;
    }

    public void setEtat_button_ajouter(int etat_button_ajouter) {
        this.etat_button_ajouter = etat_button_ajouter;
    }

  

           
     public static ObjectHolder getInstance()
     {
         if(instance==null){
         instance = new ObjectHolder();
         
         }
         return instance;
    
}
    
}
