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
		Main.getEditor().getInputManager().addMapping("middleclick", new MouseButtonTrigger(MouseInput.BUTTON_MIDDLE));
		Main.getEditor().getInputManager().addMapping("leftclick", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		Main.getEditor().getInputManager().addMapping("right", new KeyTrigger(KeyInput.KEY_RIGHT));
		Main.getEditor().getInputManager().addMapping("left", new KeyTrigger(KeyInput.KEY_LEFT));
		Main.getEditor().getInputManager().addMapping("down", new KeyTrigger(KeyInput.KEY_DOWN));
		Main.getEditor().getInputManager().addMapping("up", new KeyTrigger(KeyInput.KEY_UP));
		Main.getEditor().getInputManager().addListener(this, "leftclick", "rightclick", "middleclick");
		Main.getEditor().getInputManager().addListener(this, "down", "up", "right", "left", "add");
	}

	@Override
	public void onAction(String name, boolean keyPressed, float tpf) 
	{
		if(name.contentEquals("middleclick"))
		{
			leftClicPressed = keyPressed;
		}
		else if (name.contentEquals("rightclick") && !keyPressed)
		{
			if(Main.getEditor().isAdvancedMode())
			{
				CollisionResults results = new CollisionResults();
				Vector2f click2d = Main.getEditor().getInputManager().getCursorPosition();
				Vector3f click3d = Main.getEditor().getCam().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
				Vector3f dir = Main.getEditor().getCam().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
				Ray ray = new Ray(click3d, dir);
				Main.getEditor().getObject().collideWith(ray, results);

				if (results.size()>1)
				{
					switch(results.getCollision(1).getTriangleIndex())
					{
					case 0:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x, results.getCollision(0).getGeometry().getLocalTranslation().y, results.getCollision(0).getGeometry().getLocalTranslation().z-0.25f);
						break;
					case 1:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x, results.getCollision(0).getGeometry().getLocalTranslation().y, results.getCollision(0).getGeometry().getLocalTranslation().z-0.25f);
						break;
					case 2:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x+0.25f, results.getCollision(0).getGeometry().getLocalTranslation().y, results.getCollision(0).getGeometry().getLocalTranslation().z);
						break;
					case 3:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x+0.25f, results.getCollision(0).getGeometry().getLocalTranslation().y, results.getCollision(0).getGeometry().getLocalTranslation().z);
						break;
					case 4:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x, results.getCollision(0).getGeometry().getLocalTranslation().y, results.getCollision(0).getGeometry().getLocalTranslation().z+0.25f);
						break;
					case 5:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x, results.getCollision(0).getGeometry().getLocalTranslation().y, results.getCollision(0).getGeometry().getLocalTranslation().z+0.25f);
						break;	
					case 6:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x-0.25f, results.getCollision(0).getGeometry().getLocalTranslation().y, results.getCollision(0).getGeometry().getLocalTranslation().z);
						break;
					case 7:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x-0.25f, results.getCollision(0).getGeometry().getLocalTranslation().y, results.getCollision(0).getGeometry().getLocalTranslation().z);
						break;	
					case 8:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x, results.getCollision(0).getGeometry().getLocalTranslation().y+0.25f, results.getCollision(0).getGeometry().getLocalTranslation().z);
						break;
					case 9:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x, results.getCollision(0).getGeometry().getLocalTranslation().y+0.25f, results.getCollision(0).getGeometry().getLocalTranslation().z);
						break;	
					case 10:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x, results.getCollision(0).getGeometry().getLocalTranslation().y-0.25f, results.getCollision(0).getGeometry().getLocalTranslation().z);
						break;
					case 11:
						Main.getEditor().getObject().addBlock(results.getCollision(1).getGeometry().getLocalTranslation().x, results.getCollision(0).getGeometry().getLocalTranslation().y-0.25f, results.getCollision(0).getGeometry().getLocalTranslation().z);
						break;	
					}				
				}
			}
		}
		else if (name.contentEquals("leftclick") && !keyPressed && Main.getEditor().isAdvancedMode())
		{
			Main.getEditor().getObject().deleteBlock();
		}
	}

	public boolean isLeftClicPressed()
	{
		return leftClicPressed;
	}
}