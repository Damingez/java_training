package pl.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.List;

public class Collections {

  public static void main (String args[]) {

   String[] tabl = {"Java","C#","Python","PHP"};

    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Ptyhon");
    languages.add("PHP");

/*
   for (String t: tabl)
   {
     System.out.println(t);
   }
        */
    for (String l: languages)
    {
      System.out.println(l);
    }

    for (int i=0; i< languages.size(); i++)
    {
      System.out.println(languages.get(i));
    }

  }
}

