
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class Alumnos {

    private int codi;
    private String nom;
    private List<String> assignatures = new ArrayList<String>(), tutories = new ArrayList<String>();

    public Alumnos() {

    }

    public Alumnos(int codi, String nom) {
        this.codi = codi;
        this.nom = nom;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public void addAssignatura(String assignatura) {
        assignatures.add(assignatura);
    }

    public List<String> getAssignatures() {
        return assignatures;
    }

    public void setAssignatures(List<String> assignatures) {
        this.assignatures = assignatures;
    }

    public void addTutoria(String tutoria) {
        tutories.add(tutoria);
    }

    public List<String> getTutories() {
        return tutories;
    }

    public void setTutories(List<String> tutories) {
        this.tutories = tutories;
    }
    
}
