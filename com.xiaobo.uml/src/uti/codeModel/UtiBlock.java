package uti.codeModel;

import java.util.HashMap;
import java.util.Vector;

public class UtiBlock extends UtiCommand implements BaseCollection {
	Vector children = new Vector();
	HashMap childrenmap = new HashMap();
	HashMap posmap = new HashMap();
    Vector commands = new Vector();
public UtiBlock(BaseCode p) {
	super(p);
	
}
public int getChildCount()
{
	return children.size();
	
}
public BaseName getChild(int i)
{
	return (BaseName)children.elementAt(i);
}
public void addCommand(BaseCommand com)
{
	commands.addElement(com);
	if (com instanceof BaseName) {
		addChild((BaseName)com, commands.size()-1);
	}
}
public void addChild(BaseName obj, int value)
{
	addChild(obj);
	posmap.put(obj.getName(), new Integer(value));
}
public void addChild(BaseName obj) {
	// TODO Auto-generated method stub
	children.addElement(obj.getName());
	   childrenmap.put(obj.getName(), obj);
	
}
public UtiInterface addInterface(String name) {

	return null;
}
public UtiClass addObject(String name) {

	return null;
}
public BaseName getChildByName(String name) {

	return (UtiClass)childrenmap.get(name);
}
public void removeChild(BaseName pack) {

	children.remove(pack);
	   childrenmap.remove(pack.getName());
	   posmap.remove(pack.getName());
}
public void renameChild(BaseName pack, String newname) {

	Integer v = (Integer)posmap.get(pack.getName());
	posmap.remove(pack.getName());
	childrenmap.remove(pack.getName());
	   pack.setName(newname);
	   childrenmap.put(pack, newname);
	   posmap.put(pack.getName(), v);
}
}
