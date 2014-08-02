package Editor;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Main extends SimpleApplication 
{ 
	Vector2f oldposition;
	private static Main editor;

	public static void main(String[] args)
	{
		Main app = new Main();
		app.start(); // start the editor
	}

	@Override
	public void simpleInitApp() 
	{
		editor = this;
		createDefaultObject();
		initializeControls(); 
		oldposition = inputManager.getCursorPosition().clone();
	}

	private void createDefaultObject()
	{
		Geometry theObject = new Geometry("Box", new Box(1, 1, 1));  // create cube geometry from the shape
		Material mat = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
		mat.setTexture("ColorMap", assetManager.loadTexture("test.png")); // set texture
		theObject.setMaterial(mat);                   // set the cube's material
		rootNode.attachChild(theObject);              // make the cube appear in the scene
	}

	private void initializeControls()
	{
		this.flyCam.setEnabled(false);
		inputManager.setCursorVisible(true);
		inputManager.addMapping("leftclick", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_RIGHT));
		inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_LEFT));
		inputManager.addMapping("down", new KeyTrigger(KeyInput.KEY_DOWN));
		inputManager.addMapping("up", new KeyTrigger(KeyInput.KEY_UP));
		inputManager.addListener(analogListener, "leftclick", "right", "left", "down" , "up");
		inputManager.addListener(actionListener, "leftclick");

	}

	private AnalogListener  analogListener  = new AnalogListener ()
	{
		public void onAnalog(String name, float arg1, float arg2) 
		{
			if(name.contentEquals("leftclick"))
			{
				Vector2f newposition = inputManager.getCursorPosition();
				if(newposition.getX()<oldposition.getX())
				{
					rootNode.rotate(0,(float)-0.03, 0);
				}
				else if(newposition.getX()>oldposition.getX())
				{
					rootNode.rotate(0,(float)0.03, 0);
				}
				if(newposition.getY()<oldposition.getY())
				{
					rootNode.rotate((float)0.03, 0, 0);
				}
				else if(newposition.getY()>oldposition.getY())
				{
					rootNode.rotate((float)-0.03, 0, 0);
				}		
				oldposition = newposition.clone() ;
			}
			if(name.contentEquals("right"))
			{
				rootNode.move((float)0.005, 0, 0);
			}
			else if(name.contentEquals("left"))
			{
				rootNode.move((float)-0.005, 0, 0);
			}
			if(name.contentEquals("up"))
			{
				rootNode.move(0, (float)0.005, 0);
			}
			else if(name.contentEquals("down"))
			{
				rootNode.move(0, (float)-0.005, 0);
			}
		}
	};

	private ActionListener actionListener = new ActionListener() 
	{
		public void onAction(String name, boolean keyPressed, float tpf) 
		{
			if(name.contentEquals("leftclick") && keyPressed)
			{
				inputManager.setCursorVisible(false);
			}
			else
			{
				inputManager.setCursorVisible(true);
			}
		}
	};

	public static Main getEditor()
	{
		return editor;
	}
}