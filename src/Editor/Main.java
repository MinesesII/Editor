package Editor;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.ui.Picture;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class Main extends SimpleApplication implements ScreenController
{ 
	Vector2f oldposition;
	private static Main editor;
	private TheObject theObject;
	private Socle socle;
	private Events events;
	private GUI gui;
	private boolean isAdvancedEdit=false;
	private Material mat;


	public static void main(String[] args)
	{
		Main app = new Main();
		app.setPauseOnLostFocus(false);
		app.start();
	}

	@Override
	public void simpleInitApp() 
	{
		editor = this;
		flyCam.setEnabled(false);
		makeBackground();
		events = new Events();
		gui = new GUI();
		socle = new Socle();
		rootNode.attachChild(socle);
		oldposition = inputManager.getCursorPosition().clone();
		setMat("Bedrock");
	}

	@Override
	public void simpleUpdate(float tpf) 
	{
		if(events.isLeftClicPressed() && theObject != null)
		{
			inputManager.setCursorVisible(false);

			Vector2f newposition = Main.getEditor().getInputManager().getCursorPosition();
			if(newposition.getX()<oldposition.getX())
			{
				theObject.rotate(0,(float)-0.03, 0);
			}
			else if(newposition.getX()>oldposition.getX())
			{
				theObject.rotate(0,(float)0.03, 0);
			}
			if(newposition.getY()<oldposition.getY()  && !isAdvancedEdit)
			{
				theObject.rotate((float)0.03, 0, 0);
			}
			else if(newposition.getY()>oldposition.getY()  && !isAdvancedEdit)
			{
				theObject.rotate((float)-0.03, 0, 0);
			}		
			oldposition = newposition.clone() ;
		}
		else
		{
			inputManager.setCursorVisible(true);
		}
	}
	
	public void createDefaultObject()
	{
		if(theObject!=null)
		{
			theObject.detachAllChildren();
		}
		theObject = new TheObject(1);
		rootNode.attachChild(theObject);
	}

	public void createComplexeObject(int type)
	{
		if(theObject!=null)
		{
			theObject.detachAllChildren();
		}
		theObject = new TheObject(type);
		rootNode.attachChild(theObject);

	}

	private void makeBackground()
	{
		Picture p = new Picture("background");
		p.setImage(assetManager, "Sky.png", false);
		p.setWidth(settings.getWidth());
		p.setHeight(settings.getHeight());
		p.setPosition(0, 0);
		ViewPort pv = renderManager.createPreView("background", cam);
		pv.setClearFlags(true, true, true);
		pv.attachScene(p);
		p.updateGeometricState();
		viewPort.setClearFlags(false, true, true);
	}

	public void passToAdvancedEdit()
	{
		isAdvancedEdit=true;
		theObject.setLocalRotation(new Quaternion (0,0,0,1));
		cam.setLocation(new Vector3f(cam.getLocation().x, cam.getLocation().y, cam.getLocation().z-6));
	}

	public void passToNormalEdit()
	{
		isAdvancedEdit=false;
		cam.setLocation(new Vector3f(cam.getLocation().x, cam.getLocation().y, cam.getLocation().z+6));
	}

	public void setMat(String texture)
	{
		mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");  
		mat.setTexture("ColorMap", Main.getEditor().getAssetManager().loadTexture(texture+".jpg"));
	}

	//return wherever is the code the main class
	public static Main getEditor()
	{
		return editor;
	}

	public Material getMat()
	{
		return mat;
	}

	public GUI getGui() 
	{
		return gui;
	}

	public boolean isAdvancedMode() 
	{
		return isAdvancedEdit;
	}

	public TheObject getObject()
	{
		return theObject;
	}

	public Camera getCam()
	{
		return cam;
	}

	@Override
	public void bind(Nifty arg0, Screen arg1) 
	{
	}

	@Override
	public void onEndScreen() 
	{
	}

	@Override
	public void onStartScreen() 
	{
	}
}