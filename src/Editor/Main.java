package Editor;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector2f;
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
		
		if(getGui().isCreatingDefaultObject())
		{		
			theObject = new TheObject();
			rootNode.attachChild(theObject);
		}
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

	//return wherever is the code the main class
	public static Main getEditor()
	{
		return editor;
	}
	
	public GUI getGui() 
	{
		return gui;
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