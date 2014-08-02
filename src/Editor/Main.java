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
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.ui.Picture;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class Main extends SimpleApplication implements ScreenController
{ 
	Vector2f oldposition;
	private static Main editor;
	private Node theObject = new Node ("The object"); // Node of the object
	private boolean isObject=false; // Verify if the object existe
	private Socle socle;
	private Events events;
	private GUI gui;

	public static void main(String[] args)
	{
		Main app = new Main();
		app.setPauseOnLostFocus(false);
		app.setShowSettings(false);
		app.start(); // start the editor
	}

	@Override
	public void simpleInitApp() 
	{
		editor = this;
		this.flyCam.setEnabled(false);
		
		makeBackground();
		
		events = new Events();
		
		gui = new GUI();
		
		socle = new Socle();
		rootNode.attachChild(socle);
		
		oldposition = inputManager.getCursorPosition().clone();
	}
	
	@Override
	public void simpleUpdate(float tpf) 
	{
		if(events.isLeftClicPressed() && isObject)
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
		else
		{
			inputManager.setCursorVisible(true);
		}
		
		if(gui.isCreatingDefaultObject()){
			
			Geometry model = new Geometry("Box", new Box(1, 1, 1));  
			Material mat = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");  
			mat.setTexture("ColorMap", assetManager.loadTexture("Glowstone.png"));
			model.setMaterial(mat);              
			theObject.attachChild(model);
			rootNode.attachChild(theObject);   
			isObject=true;
		}
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

	//return wherever is the code the main class
	public static Main getEditor()
	{
		return editor;
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