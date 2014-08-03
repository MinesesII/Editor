package Editor;

import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

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
		Main.getEditor().getInputManager().addMapping("right", new KeyTrigger(KeyInput.KEY_RIGHT));
		Main.getEditor().getInputManager().addMapping("left", new KeyTrigger(KeyInput.KEY_LEFT));
		Main.getEditor().getInputManager().addMapping("down", new KeyTrigger(KeyInput.KEY_DOWN));
		Main.getEditor().getInputManager().addMapping("up", new KeyTrigger(KeyInput.KEY_UP));
		Main.getEditor().getInputManager().addMapping("suppr", new KeyTrigger(KeyInput.KEY_DELETE));
		Main.getEditor().getInputManager().addListener(this, "leftclick", "rightclick");
		Main.getEditor().getInputManager().addListener(this, "down", "up", "right", "left", "suppr");
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
		}
		else if (name.contentEquals("suppr"))
		{
			Main.getEditor().getObject().deleteBlock();
		}
		else
		{
			Main.getEditor().setSelectionCubeDirection(name);
		}
	}
	
	public boolean isLeftClicPressed()
	{
		return leftClicPressed;
	}
}