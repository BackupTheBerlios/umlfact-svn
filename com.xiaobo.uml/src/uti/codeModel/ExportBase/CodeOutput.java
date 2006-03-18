package uti.codeModel.ExportBase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class CodeOutput extends PrintWriter{
	protected String SourceName;
    protected int currentdepth = 0;
    public static int depthstep = 3;
    String segment = "";
    
    public static void setDepthStep(int i)
    {
    	depthstep = i;
    }
    public String getSourceName()
    {
    	return SourceName;
    }
    public void setSourceName(String s)
    {
    	SourceName = s;
    }
    public void addFile(String name, String seg)
    {
    	segment = seg;
    	addFile(name);
    	segment = "";
    }
    public void addFile(String name)
    {
    	BufferedReader in = null;
    	String line;
    	String currentsegment="";
    	try {
    		in = new BufferedReader(new FileReader(name));
    		while ((line = in.readLine()) != null) {
    			if (line.length() >= 1 && line.charAt(0) == '.') {
    				line = line.substring(1);
    				if (line.startsWith("segment")) {
    					line = line.substring(8, line.length());
    					line = line.trim();
    					currentsegment=line;
    				}
    				
    			} else {
    				if (currentsegment.equals(segment))
    			       this.println(line);
    			}
    		}
    	} catch (FileNotFoundException ex) {
    		System.out.println("Datei nicht gefunden: "+ex);
    	} catch (IOException ex) {
    		System.out.println("IO Exception, Grund"+ex);
    	} finally {
    		if (in != null) {
    			try {
    				in.close();
    			}catch (Exception ex){};
    		}
    	}
    }
    public void incDepth()
    {
    	currentdepth += 1;
    }
    public void decDepth()
    {
    	currentdepth -= 1;
    	if (currentdepth < 0 ) currentdepth = 0;
    }
    public void print(String s) {    	
    		super.print(s);
    }
    public void println() {
    	super.println();
    	for (int i = 0; i < depthstep*currentdepth; i++) {
    		print(" ");    		
    	}
    }
    public CodeOutput(String name) throws IOException
    {    	
    	super(new FileWriter(name));
    	setSourceName(name);
    }
    
	
	public CodeOutput(OutputStream out) {
		super(out);	
	}

	public CodeOutput(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
	}


	public CodeOutput(Writer out) {
		super(out);
	}
	public CodeOutput(Writer out, boolean autoFlush) {
		super(out, autoFlush);
	}
}
