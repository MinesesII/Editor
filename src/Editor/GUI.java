package Editor;

import com.jme3.niftygui.NiftyJmeDisplay;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Menu;
import de.lessvoid.nifty.controls.MenuItemActivatedEvent;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.SizeValue;

public class GUI implements ScreenController
{
	private Element popup;
	private Nifty nifty;
	private boolean createDefaultObject = false;

	public GUI()
	{	
		NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(Main.getEditor().getAssetManager(), Main.getEditor().getInputManager(), Main.getEditor().getAudioRenderer(), Main.getEditor().getGuiViewPort());
		nifty = niftyDisplay.getNifty();
		nifty.fromXml("Interface/Button.xml", "start", this);
		Main.getEditor().getGuiViewPort().addProcessor(niftyDisplay);
	}
	

	public void createDefaultObject()
	{
		createDefaultObject = true;
	}
	
	public boolean isCreatingDefaultObject()
	{
		
		if(createDefaultObject)
		{
			createDefaultObject = false;
			return true;
		}
		
		return false;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createPopupMenuCreate()
	{
		popup = nifty.createPopup("niftyPopupMenu");
		Menu menucreate = popup.findNiftyControl("#menu", Menu.class);
		menucreate.setWidth(new SizeValue("140px")); 
		menucreate.addMenuItem("new block", "Arrow.png", new menuItem("newblock", "Create new block"));
		menucreate.addMenuItem("new item", "Arrow.png", new menuItem("newitem", "Create new item"));
		menucreate.addMenuItem("new mob", "Arrow.png", new menuItem("newmob", "Create new mob"));
		menucreate.addMenuItem("new structure", "Arrow.png", new menuItem("newstructure", "Create new structure"));
		nifty.subscribe(nifty.getCurrentScreen(), menucreate.getId(), MenuItemActivatedEvent.class, new MenuControl());
	}

	public void showMenuCreate() 
	{ 
		createPopupMenuCreate() ;
		nifty.showPopup(nifty.getCurrentScreen(), popup.getId(), null); 
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createPopupMenuEdit()
	{
		popup = nifty.createPopup("niftyPopupMenu");
		Menu menucreate = popup.findNiftyControl("#menu", Menu.class);
		menucreate.setWidth(new SizeValue("140px")); 
		menucreate.addMenuItem("new block", "Arrow.png", new menuItem("newblock", "Create new block"));
		menucreate.addMenuItem("new item", "Arrow.png", new menuItem("newitem", "Create new item"));
		menucreate.addMenuItem("new mob", "Arrow.png", new menuItem("newmob", "Create new mob"));
		menucreate.addMenuItem("new structure", "Arrow.png", new menuItem("newstructure", "Create new structure"));
		nifty.subscribe(nifty.getCurrentScreen(), menucreate.getId(), MenuItemActivatedEvent.class, new MenuControl());
	}
	
	public void showMenuEdit() 
	{ 
		createPopupMenuEdit() ;
		nifty.showPopup(nifty.getCurrentScreen(), popup.getId(), null); 
	}

	class menuItem 
	{
		public String id;
		public String name;
		public menuItem(String id, String name)
		{
			this.id= id;
			this.name = name;
		}
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