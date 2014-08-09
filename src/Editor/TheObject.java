package Editor;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class TheObject extends Node
{

	private int types;

	public TheObject(int type)
	{
		types=type;
		if(type == 1)
		{
			Geometry model = new Geometry("Cube", new Box(1, 1, 1));  
			Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");  
			mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture("Dirt.jpg"));
			model.setMaterial(mat);              
			attachChild(model);
		}
		else if (type == 2)
		{
			Geometry model;
			for (int x = 0; x<8; x++)
			{
				for (int y = 0; y<8; y++)
				{
					for (int z = 0; z<8; z++)
					{
						model = new Geometry("Cube", new Box(0.125f,0.125f,0.125f));
						model.setLocalTranslation((0.25f*x)-0.875f,(0.25f*y)-0.875f,(0.25f*z)-0.875f);
						attachChild(model);
					}
				}
			}
			Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture("Bedrock.jpg")); 
			setMaterial(mat);
		}
		else
		{
			Geometry model = new Geometry("Cube", new Box(0.125f,0.125f,0.125f));
			model.setLocalTranslation(-0.875f,-0.875f,-0.875f);
			attachChild(model);
		}
		Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture("Bedrock.jpg")); 
		setMaterial(mat);
	}


	public void deleteBlock(Geometry cube)
	{
		this.detachChild(cube);
	}

	public void addBlock(float x, float y, float z)
	{
		if (CanPlaceACube(x,y,z))
		{
			Geometry model = new Geometry("Cube", new Box(0.125f,0.125f,0.125f));
			model.setMaterial(Main.getEditor().getMat());
			attachChild(model);
			model.setLocalTranslation(x,y,z);
		}
	}

	public boolean CanPlaceACube(float x, float y, float z)
	{
		for(int i = 0 ; i < getQuantity(); i++)
		{
			if(getChild(i).getLocalTranslation().x==x && 
					getChild(i).getLocalTranslation().y==y && 
					getChild(i).getLocalTranslation().z==z)
			{
				return false;
			}
		}
		if (x<=0.875 && x>=-0.875 && y<=0.875 && y>=-0.875 && z<=0.875 && z>=-0.875)
		{
			return true;
		}
		return false;
	}

	public int getType()
	{
		return types;
	}
}