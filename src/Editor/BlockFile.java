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
import com.jme3.scene.shape.Box;



public class BlockFile 
{
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public void exportObject(String directory, String fileName)
	{
		if(fileName!=null)
		{
			try 
			{
				if(fileName.endsWith(".blok"))
				{
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(directory+fileName))));
				}
				else
				{
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(directory+fileName+".blok"))));
				}
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
	}

	public void importObject(String directory, String fileName)
	{
		if(fileName!=null)
		{
			try 
			{
				ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(directory+fileName))));

				try 
				{
					TheObject theobject = (TheObject)ois.readObject();
					if(theobject.getType()==1)
					{
						Geometry model = new Geometry("0", new Box(1,1,1));
						Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");  
						mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture(theobject.getTexturesList()[0]));
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
									if(theobject.getTexturesList()[count]!=null)
									{
										Geometry model = new Geometry(Integer.toString(count), new Box(0.125f,0.125f,0.125f));
										model.setLocalTranslation((0.25f*x)-0.875f,(0.25f*y)-0.875f,(0.25f*z)-0.875f);
										Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");  
										mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture(theobject.getTexturesList()[count]));
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
}


