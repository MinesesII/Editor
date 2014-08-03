package Editor;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class TheObject extends Node
{

	public TheObject(int type)
	{
		if(type == 1)
		{
			Geometry model = new Geometry("Cube", new Box(1, 1, 1));  
			Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");  
			mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture("Glowstone.png"));
			model.setMaterial(mat);              
			attachChild(model);
		}
		else
		{
			Geometry model;
			for (int x = 0; x<8; x++)
			{
				for (int y = 0; y<8; y++)
				{
					for (int z = 0; z<8; z++)
					{
						model = new Geometry("Cube"+(x+y+z), new Box(0.125f,0.125f,0.125f));
						model.setLocalTranslation((0.25f*x)-0.875f,(0.25f*y)-0.875f,(0.25f*z)-0.875f);
						attachChild(model);
					}
				}
			}
			Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture("Test.png")); 
			setMaterial(mat);
		}
	}
}