package Editor;

import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

public class Events implements ActionListener
{
	private boolean leftClicPressed = false;

	public Events()
	{
		initializeControls();
	}

	private void initializeControls()
	{

		Main.getEditor().getInputManager().setCursorVisible(true);
		Main.getEditor().getInputManager().addMapping("rightclick", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
		Main.getEditor().getInputManager().addMapping("leftclick", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		Main.getEditor().getInputManager().addMapping("right", new KeyTrigger(KeyInput.KEY_RIGHT));
		Main.getEditor().getInputManager().addMapping("left", new KeyTrigger(KeyInput.KEY_LEFT));
		Main.getEditor().getInputManager().addMapping("down", new KeyTrigger(KeyInput.KEY_DOWN));
		Main.getEditor().getInputManager().addMapping("up", new KeyTrigger(KeyInput.KEY_UP));
		Main.getEditor().getInputManager().addMapping("suppr", new KeyTrigger(KeyInput.KEY_DELETE));
		Main.getEditor().getInputManager().addMapping("add", new KeyTrigger(KeyInput.KEY_NUMPAD0));
		Main.getEditor().getInputManager().addListener(this, "leftclick", "rightclick");
		Main.getEditor().getInputManager().addListener(this, "down", "up", "right", "left", "suppr", "add");
	}

	@Override
	public void onAction(String name, boolean keyPressed, float tpf) 
	{
		if(name.contentEquals("rightclick"))
		{
			leftClicPressed = keyPressed;
		}
		else if (name.contentEquals("leftclick"))
		{
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
		else if (name.contentEquals("suppr"))
		{
			Main.getEditor().getObject().deleteBlock();
		}
		else if (name.contentEquals("add"))
		{
			Main.getEditor().getObject().addBlock();
		}
		else if (!keyPressed)
		{
			Main.getEditor().setSelectionCubeDirection(name);
		}
	}

	public boolean isLeftClicPressed()
	{
		return leftClicPressed;
	}
}