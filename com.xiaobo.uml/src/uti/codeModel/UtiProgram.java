package uti.codeModel;

import java.io.File;

import org.w3c.dom.Element;

import uti.codeModel.ExportBase.BaseExporter;
import uti.codeModel.ExportBase.CodeSys;
import uti.codeModel.ExportCPP.CPPExporter;
import uti.java.UtiOB;
import uti.parser.LinkMemory;
import uti.parser.ModelGenerator;
import uti.parser.SyntaxException;
import java.util.*;

/**
 * Erzeugt ein neues Javaprogramm. Diese Klasse enthält, das Basispacket
 * indem sich alle Objekte befinden. Sie sollte zuerst angelegt werden.
 * 
 * Diese Klasse ist ein Singleton.
 * @author staud
 *
 */
public class UtiProgram extends BaseCode {
    UtiPackage base = new UtiPackage(null);
    Vector arrays = new Vector();
    HashMap arraymap = new HashMap();
    public static UtiProgram MainProg=null;
    /**
     * Erzeugt ein neues UtiProgram
     *
     */
	public UtiProgram() {		
		super();
		init();
	}

	public UtiProgram(UtiOB p) {
		super(p);
		init();
	}
    void init()
    {
    	MainProg = this;
		base.setName("Base");
    }
    /**
     * Gibt das Basispackage zurück
     * @return das Basispackage
     */
	public UtiPackage getBase() {
		return base;
	}
	/**
	 * Versucht (!) den in file angegeben Javaquellcode zu parsen und entsprechende
	 * Klassen in den internen Datenstrukturen zu erzeugen.
	 * @param file Name der Java darei.
	 */
	void readFile(String file)
	{ 
		File f = new File(file);
        ModelGenerator.readSingleFile(f, base);
	}
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "Base", base, version);
		UtiOB.readList(xml, "arrays", arrays, version, this);
		arraymap.clear();
		for (int i = 0; i < arrays.size(); i++)
		{
			UtiArray s = (UtiArray)arrays.elementAt(i);
			arraymap.put(s.getBasetype(), s);
		}
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "Base", base, version);
		UtiOB.writeList(xml,"arrays", arrays, version);
	}
	void addArray(UtiArray a, UtiType base)
	{
		arrays.addElement(a);
		arraymap.put(base, a);
		
	}

	void link()
	{
		try {
	        LinkMemory.doLink();
	   } catch (SyntaxException e) {
	        	System.out.println(e.toString());
	   }
	}
	void exportToCpp()
	{
		BaseExporter exp = new CPPExporter();
        exp.export(base);
        CodeSys.free();
	}
	UtiArray findArrayByType(UtiType t)
	{
		return (UtiArray)arraymap.get(t);
	}
	/**
	 * Diese Methode gibt einen Arraytyp der angegeben Dimension
	 * zurück. Diese Methode wird vom Parser verwendet.
	 * 
	 * Bei dimensions = 0 wird t wieder zurückgegeben. 
	 * 
	 * Wurde die Methoden schonmal mit den selben Parametern
	 * aufgerufen, dann gitb sie genau die selbe Instanz zurück.
	 * 
	 * @param t Basistyp des Array.
	 * @param dimensions Dimensionen des neuen Arrays.
	 * @return Das neue Array.
	 */
	public UtiType getArrayType(UtiType t, int dimensions)
	{
		if (dimensions == 0) {
			return t;
		}
		UtiType basetype = getArrayType(t, dimensions-1);
		UtiArray a = findArrayByType(basetype);
		if (a == null) {
			a = new UtiArray(this);
			a.setBasetype(basetype);
			addArray(a, basetype);
		}
		return a;
	
	}

}
