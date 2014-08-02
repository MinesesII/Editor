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
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.ui.Picture;

public class Main extends SimpleApplication 
{ 
	Vector2f oldposition;
	private static Main editor;
	private Node theObject = new Node ("The object");

	public static void main(String[] args)
	{
		Main app = new Main();
		app.start(); // start the editor
	}

	@Override
	public void simpleInitApp() 
	{
		editor = this;
		makeBackground();
		createDefaultObject();
		createBase();
		initializeControls(); 
		oldposition = inputManager.getCursorPosition().clone();
	}
	
	private void makeBackground()
	{
		Picture p = new Picture("background");
		p.setImage(assetManager, "sky.png", false);
		p.setWidth(settings.getWidth());
		p.setHeight(settings.getHeight());
		p.setPosition(0, 0);
		ViewPort pv = renderManager.createPreView("background", cam);
		pv.setClearFlags(true, true, true);
		pv.attachScene(p);
		p.updateGeometricState();
		viewPort.setClearFlags(false, true, true);
	}
	
	private void createBase()
	{
		Node socle = new Node("socle");
		Geometry base = new Geometry("Box", new Box((float)0.6,(float) 0.1, (float)0.6));  
		Geometry base1 = new Geometry("Box", new Box((float)0.2,(float) 1, (float)0.2));  
		Geometry base2 = new Geometry("Box", new Box((float)0.5,(float) 0.1, (float)0.5));  
		Material mat = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setTexture("ColorMap", assetManager.loadTexture("Socle.png"));
		base.setMaterial(mat); 
		base1.setMaterial(mat);  
		base2.setMaterial(mat); 
		socle.attachChild(base); 
		socle.attachChild(base1);
		socle.attachChild(base2);
		base.setLocalTranslation(0,(float) -3.8, 0);
		base1.setLocalTranslation(0, (float)-2.8, 0);
		base2.setLocalTranslation(0, (float)-1.8, 0);
		rootNode.attachChild(socle);
	}

	private void createDefaultObject()
	{
		Geometry model = new Geometry("Box", new Box(1, 1, 1));  
		Material mat = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");  
		mat.setTexture("ColorMap", assetManager.loadTexture("Glowstone.png"));
		model.setMaterial(mat);              
		theObject.attachChild(model);
		rootNode.attachChild(theObject);              
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
					theObject.rotate(0,(float)-0.03, 0);
				}
				else if(newposition.getX()>oldposition.getX())
				{
					theObject.rotate(0,(float)0.03, 0);
				}
				if(newposition.getY()<oldposition.getY())
				{
					theObject.rotate((float)0.03, 0, 0);
				}
				else if(newposition.getY()>oldposition.getY())
				{
					theObject.rotate((float)-0.03, 0, 0);
				}		
				oldposition = newposition.clone() ;
			}
		/*	if(name.contentEquals("right"))
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
			}*/
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