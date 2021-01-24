package fr.upec.episen.resources.object;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Notes {

    public Notes(){
        super();
    }

    public Notes(String etudiant,ArrayList<Integer> notes){
        this.etudiant = etudiant;
        this.notes = notes;
    }

    private String etudiant;
    private ArrayList<Integer> notes;


}
