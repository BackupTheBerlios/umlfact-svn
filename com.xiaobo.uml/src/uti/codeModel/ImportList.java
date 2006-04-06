package uti.codeModel;

import java.util.*;

public class ImportList {
    HashSet PrimaryImportSet = new HashSet();
    HashSet SecondaryImportSet = new HashSet();
	public ImportList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void addPrimary(UtiType typ) {
		if (typ == null) return;
		if (typ instanceof BaseType) return;
		if (SecondaryImportSet.contains(typ)) {
			SecondaryImportSet.remove(typ);
		}
		if (!PrimaryImportSet.contains(typ)) {
			PrimaryImportSet.add(typ);
		}
	}
	public void addSecondary(UtiType typ) {
		if (typ == null) return;
		if (typ instanceof BaseType) return;
		if (PrimaryImportSet.contains(typ)) {
			return;
		}
		if (!SecondaryImportSet.contains(typ)) {
			SecondaryImportSet.add(typ);
		}
	}
	public Object [] getPrimary()
	{
		Object a[] = PrimaryImportSet.toArray();
		return a;
	}
	public Object [] getSecondary()
	{
		return SecondaryImportSet.toArray();
	}

}
