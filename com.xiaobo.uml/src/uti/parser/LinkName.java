package uti.parser;

import java.util.*;

public class LinkName {
   Vector names=new Vector();
   Vector tokens = new Vector();
   void addValue(String name, Token t)
   {
	   names.addElement(name);
	   tokens.addElement(t);
	   
   }
   int getCount()
   {
	   return names.size();
   }
   String getValue(int i)
   {
	   return (String)names.elementAt(i);
   }
   Token getToken(int i)
   {
	   return (Token)tokens.elementAt(i);
   }
   public String toString()
   {
	   String s="";
	   for (int i = 0; i < getCount();i++) s+= getValue(i);
	   return s;
   }
}
