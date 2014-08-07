package Editor;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class Socle extends Node
{

	public Socle()
	{
		
		Geometry base = new Geometry("Box", new Box((float)0.6,(float) 0.1, (float)0.6));  
		Geometry base1 = new Geometry("Box", new Box((float)0.2,(float) 1, (float)0.2));  
		Geometry base2 = new Geometry("Box", new Box((float)0.5,(float) 0.1, (float)0.5));  
		Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture("Stone.jpg"));
		base.setMaterial(mat); 
		base1.setMaterial(mat);  
		base2.setMaterial(mat); 
		this.attachChild(base); 
		this.attachChild(base1);
		this.attachChild(base2);
		base.setLocalTranslation(0,(float) -3.8, 0);
		base1.setLocalTranslation(0, (float)-2.8, 0);
		base2.setLocalTranslation(0, (float)-1.8, 0);
	}
}