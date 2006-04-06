package uti.codeModel;

public interface BaseCollection {

	public abstract void removeChild(BaseName pack);

	public abstract void renameChild(BaseName pack, String newname);

	public abstract BaseName getChildByName(String name);

	public abstract void addChild(BaseName obj);

	public abstract int getChildCount();
	public abstract BaseName getChild(int i);
}