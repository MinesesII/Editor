package Editor;

import com.jme3.material.Material;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class GUI implements ScreenController{
	
	private Nifty nifty;
	private boolean createDefaultObject = false;

	public GUI(){
		
		NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(Main.getEditor().getAssetManager(), Main.getEditor().getInputManager(), Main.getEditor().getAudioRenderer(), Main.getEditor().getGuiViewPort());
		nifty = niftyDisplay.getNifty();
		nifty.fromXml("Interface/Button.xml", "start", this);
		Main.getEditor().getGuiViewPort().addProcessor(niftyDisplay);
	}
	

	public void createDefaultObject()
	{
		createDefaultObject = true;
	}
	
	public boolean isCreatingDefaultObject(){
		
		if(createDefaultObject){
			
			createDefaultObject = false;
			return true;
		}
		
		return false;
	}

	@Override
	public void bind(Nifty arg0, Screen arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartScreen() {
		// TODO Auto-generated method stub
		
	}
}