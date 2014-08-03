package Editor;

import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;

public class Events implements ActionListener{
	
	private boolean leftClicPressed = false;
	
	public Events(){
		
		initializeControls();
	}
	
	private void initializeControls()
	{
		
		Main.getEditor().getInputManager().setCursorVisible(true);
		Main.getEditor().getInputManager().addMapping("leftclick", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		Main.getEditor().getInputManager().addListener(this, "leftclick");

	}

	@Override
	public void onAction(String name, boolean keyPressed, float tpf) {
		// TODO Auto-generated method stub
		if(name.contentEquals("leftclick"))
		{
			leftClicPressed = keyPressed;
		}
	}
	
	public boolean isLeftClicPressed(){
		
		return leftClicPressed;
	}
}