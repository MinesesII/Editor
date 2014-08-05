package Editor;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
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
	private Geometry selectionCube;

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

		if(getGui().isCreatingDefaultObject() && theObject == null)
		{		
			theObject = new TheObject(1);
			rootNode.attachChild(theObject);
		}

		if(getGui().isCreatingComplexeObject() && theObject == null)
		{		
			theObject = new TheObject(2);
			rootNode.attachChild(theObject);
		}
		
		if(Main.getEditor().isAdvancedMode())
		{
			CollisionResults results = new CollisionResults();
			Vector2f click2d = Main.getEditor().getInputManager().getCursorPosition();
			Vector3f click3d = Main.getEditor().getCam().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
			Vector3f dir = Main.getEditor().getCam().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
			Ray ray = new Ray(click3d, dir);
			Main.getEditor().getObject().collideWith(ray, results);
			if(results.size()!=0 && !results.getCollision(0).getGeometry().getName().contentEquals("SelectionCube"))
			{
				Main.getEditor().getSelection().setLocalTranslation(results.getCollision(0).getGeometry().getLocalTranslation());
			}
			else if (results.size()>1)
			{
				Main.getEditor().getSelection().setLocalTranslation(results.getCollision(1).getGeometry().getLocalTranslation());
			}
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

	public void passToAdvancedEdit()
	{
		isAdvancedEdit=true;
		theObject.setLocalRotation(new Quaternion (0,0,0,1));
		cam.setLocation(new Vector3f(cam.getLocation().x, cam.getLocation().y, cam.getLocation().z-6));
		selectionCube = new Geometry("SelectionCube", new Box(0.13f, 0.13f, 0.13f));
		Material mat = new Material(Main.getEditor().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", new ColorRGBA(1,0,0,0.3f));
		mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		selectionCube.setQueueBucket(Bucket.Translucent); 
		selectionCube.setMaterial(mat);
		theObject.attachChild(selectionCube);
		selectionCube.setLocalTranslation(new Vector3f(-0.875f,-0.875f,0.875f));
	}

	public void passToNormalEdit()
	{
		isAdvancedEdit=false;
		cam.setLocation(new Vector3f(cam.getLocation().x, cam.getLocation().y, cam.getLocation().z+6));
		theObject.detachChild(selectionCube);
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

	public boolean isAdvancedMode() 
	{
		return isAdvancedEdit;
	}

	public TheObject getObject()
	{
		return theObject;
	}

	public Geometry getSelection()
	{
		return selectionCube;
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