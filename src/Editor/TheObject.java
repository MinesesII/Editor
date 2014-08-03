package Editor;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class TheObject extends Node
{

	public TheObject()
	{
		Geometry model = new Geometry("Box", new Box(1, 1, 1));  
		Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");  
		mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture("Glowstone.png"));
		model.setMaterial(mat);              
		attachChild(model);
	}
}