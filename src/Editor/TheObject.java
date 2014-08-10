package Editor;

import java.io.Serializable;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

@SuppressWarnings("serial")
public class TheObject extends Node implements Serializable
{
	private int types;
	private Box[] boxlist;
	private String name;
	
	public TheObject(int type, String Name)
	{
		types=type;
		name=Name;
		if(type == 1)
		{
			boxlist = new Box[1];
			Geometry model = new Geometry("0", new Box(1, 1, 1, 0,0,0,"Dirt.jpg"));  
			boxlist[0]= new Box(1, 1, 1,0,0,0,"Dirt.jpg");
			Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");  
			mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture("Dirt.jpg"));
			model.setMaterial(mat);              
			attachChild(model);
		}
		else if (type == 2)
		{
			boxlist = new Box[512];
			Geometry model;
			int count = 0;
			for (int x = 0; x<8; x++)
			{
				for (int y = 0; y<8; y++)
				{
					for (int z = 0; z<8; z++)
					{
						model = new Geometry(Integer.toString(count), new Box(0.125f,0.125f,0.125f,(0.25f*x)-0.875f,(0.25f*y)-0.875f,(0.25f*z)-0.875f, "Bedrock.jpg"));
						model.setLocalTranslation((0.25f*x)-0.875f,(0.25f*y)-0.875f,(0.25f*z)-0.875f);
						boxlist[count]= new Box(0.125f,0.125f,0.125f,(0.25f*x)-0.875f,(0.25f*y)-0.875f,(0.25f*z)-0.875f, "Bedrock.jpg");
						attachChild(model);
						count++;
					}
				}
			}
			Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture("Bedrock.jpg")); 
			setMaterial(mat);
		}
		else
		{
			boxlist = new Box[512];
			Geometry model = new Geometry("0", new Box(0.125f,0.125f,0.125f,-0.875f,-0.875f,-0.875f, "Bedrock.jpg"));
			boxlist[0]=new Box(0.125f,0.125f,0.125f,-0.875f,-0.875f,-0.875f, "Bedrock.jpg");
			model.setLocalTranslation(-0.875f,-0.875f,-0.875f);
			attachChild(model);
			Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture("Bedrock.jpg")); 
			model.setMaterial(mat);
			type = 2;
		}
	}


	public void deleteBlock(Geometry cube)
	{
		this.detachChild(cube);
		boxlist[Integer.parseInt(cube.getName())]=null;
	}

	public void addBlock(float x, float y, float z)
	{
		if (CanPlaceACube(x,y,z))
		{
			Geometry model = new Geometry(Integer.toString((int)(((z+0.875f)/0.25f)+((y+0.875f)/0.25f)*8+((x+0.875f)/0.25f)*8*8)), new Box(0.125f,0.125f,0.125f,x,y,z,Main.getEditor().getTexture()));
			boxlist[Integer.parseInt(model.getName())]=new Box(0.125f,0.125f,0.125f,x,y,z,Main.getEditor().getTexture());
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
	
	public Box[] getBoxList()
	{
		return boxlist;
	}
	
	public String getName()
	{
		return name;
	}
}