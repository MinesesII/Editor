package Editor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;



public class BlockFile 
{
	public void exportObject()
	{
		ObjectOutputStream oos;
		try 
		{
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Blocks.txt"))));
			oos.writeObject(Main.getEditor().getObject());
			oos.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}     	
	}

	public void importObject()
	{
		ObjectInputStream ois;
		try 
		{
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Blocks.txt"))));

			try 
			{
				TheObject theobject = (TheObject)ois.readObject();
				if(theobject.getType()==1)
				{
					Geometry model = new Geometry("0", new Box(theobject.getBoxList()[0].getSizeX(),
							theobject.getBoxList()[0].getSizeY(),
							theobject.getBoxList()[0].getSizeZ(),
							theobject.getBoxList()[0].getPosX(),
							theobject.getBoxList()[0].getPosY(),
							theobject.getBoxList()[0].getPosZ(),
							theobject.getBoxList()[0].getTextureName()));  
					Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");  
					mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture(theobject.getBoxList()[0].getTextureName()));
					model.setMaterial(mat);     
					theobject.attachChild(model);
				}
				if(theobject.getType()==2)
				{
					int count = 0;
					for (int x = 0; x<8; x++)
					{
						for (int y = 0; y<8; y++)
						{
							for (int z = 0; z<8; z++)
							{
								if(theobject.getBoxList()[count]!=null)
								{
									Geometry model = new Geometry(Integer.toString(count), new Box(theobject.getBoxList()[count].getSizeX(),
											theobject.getBoxList()[count].getSizeY(),
											theobject.getBoxList()[count].getSizeZ(),
											theobject.getBoxList()[count].getPosX(),
											theobject.getBoxList()[count].getPosY(),
											theobject.getBoxList()[count].getPosZ(),
											theobject.getBoxList()[count].getTextureName())); 
									model.setLocalTranslation(theobject.getBoxList()[count].getPosX(),theobject.getBoxList()[count].getPosY(),theobject.getBoxList()[count].getPosZ());
									Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");  
									mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture(theobject.getBoxList()[count].getTextureName()));
									model.setMaterial(mat);
									theobject.attachChild(model);
								}
								count++;
							}
						}
					}
				}

				Main.getEditor().createImportObject(theobject);
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}

			ois.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}   
	}
}


