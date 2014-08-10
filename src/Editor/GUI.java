package Editor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.scene.control.MenuItem;

import com.jme3.material.Material;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Geometry;

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
	public Nifty nifty;
	public GUI()
	{	
		NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(Main.getEditor().getAssetManager(), Main.getEditor().getInputManager(), Main.getEditor().getAudioRenderer(), Main.getEditor().getGuiViewPort());
		nifty = niftyDisplay.getNifty();
		nifty.fromXml("Interface/Button.xml", "start", this);
		Main.getEditor().getGuiViewPort().addProcessor(niftyDisplay);
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
	public void createPopupMenuCreateBlock()
	{
		popup = nifty.createPopup("niftyPopupMenu");
		Menu menucreate = popup.findNiftyControl("#menu", Menu.class);
		menucreate.setWidth(new SizeValue("140px")); 
		menucreate.addMenuItem("simple block", "Arrow.png", new menuItem("newSimpleBlock", "Create new block"));
		menucreate.addMenuItem("complexe block", "Arrow.png", new menuItem("newComplexeBlock", "Create new block"));
		nifty.subscribe(nifty.getCurrentScreen(), menucreate.getId(), MenuItemActivatedEvent.class, new MenuControl());
	}

	public void showMenuCreateBlock() 
	{ 
		createPopupMenuCreateBlock() ;
		nifty.showPopup(nifty.getCurrentScreen(), popup.getId(), null); 
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createPopupMenuCreateBlockFrom()
	{
		popup = nifty.createPopup("niftyPopupMenu");
		Menu menucreate = popup.findNiftyControl("#menu", Menu.class);
		menucreate.setWidth(new SizeValue("140px")); 
		menucreate.addMenuItem("From nothing", "Arrow.png", new menuItem("fromNothing", "From nothing"));
		menucreate.addMenuItem("From a full block", "Arrow.png", new menuItem("fromFullBlock", "From full block"));
		nifty.subscribe(nifty.getCurrentScreen(), menucreate.getId(), MenuItemActivatedEvent.class, new MenuControl());
	}

	public void showMenuCreateBlockFrom() 
	{ 
		createPopupMenuCreateBlockFrom() ;
		nifty.showPopup(nifty.getCurrentScreen(), popup.getId(), null); 
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createPopupMenuEdit()
	{
		popup = nifty.createPopup("niftyPopupMenu");
		Menu menucreate = popup.findNiftyControl("#menu", Menu.class);
		menucreate.setWidth(new SizeValue("140px")); 
		if(!Main.getEditor().isAdvancedMode())
		{
			menucreate.addMenuItem("Advanced mod", "Arrow.png", new menuItem("advancemod", "Advanced mod"));
		}
		else
		{
			menucreate.addMenuItem("Normal mod", "Arrow.png", new menuItem("normalmod", "Normal mod"));
		}
		nifty.subscribe(nifty.getCurrentScreen(), menucreate.getId(), MenuItemActivatedEvent.class, new MenuControl());
	}

	public void showMenuEdit() 
	{ 
		createPopupMenuEdit() ;
		nifty.showPopup(nifty.getCurrentScreen(), popup.getId(), null); 
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createPopupInventory()
	{
		popup = nifty.createPopup("niftyPopupMenu");
		Menu inventory = popup.findNiftyControl("#menu", Menu.class);
		inventory.setWidth(new SizeValue("140px")); 
		inventory.addMenuItem("Stone", "Stone.jpg", new menuItem("stonetexture", "Select stone texture"));
		inventory.addMenuItem("Dirt", "Dirt.jpg", new menuItem("dirttexture", "Select dirt texture"));
		inventory.addMenuItem("Bedrock", "Bedrock.jpg", new menuItem("bedrocktexture", "Select bedrock texture"));
		nifty.subscribe(nifty.getCurrentScreen(), inventory.getId(), MenuItemActivatedEvent.class, new MenuControl());
	}

	public void showPopupInventory() 
	{ 
		createPopupInventory() ;
		nifty.showPopup(nifty.getCurrentScreen(), popup.getId(), null); 
	}

	public void export() 
	{ 
		if(Main.getEditor().getObject()!=null)
		{
			BlockFile export = new BlockFile();
			export.exportObject();
		}
	}

	public void showMenuImport() 
	{ 
		createPopupMenuImport() ;
		nifty.showPopup(nifty.getCurrentScreen(), popup.getId(), null); 
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createPopupMenuImport()
	{
		popup = nifty.createPopup("niftyPopupMenu");
		Menu imported = popup.findNiftyControl("#menu", Menu.class);
		imported.setWidth(new SizeValue("140px")); 
		nifty.subscribe(nifty.getCurrentScreen(), imported.getId(), MenuItemActivatedEvent.class, new MenuControl());
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