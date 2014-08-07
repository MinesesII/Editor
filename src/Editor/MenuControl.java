package Editor;

import org.bushe.swing.event.EventTopicSubscriber;

import Editor.GUI.menuItem;
import de.lessvoid.nifty.controls.MenuItemActivatedEvent;

@SuppressWarnings("rawtypes")
public class MenuControl 
implements EventTopicSubscriber<MenuItemActivatedEvent> 
{

	@Override
	public void onEvent(final String id, final MenuItemActivatedEvent event) 
	{
		Editor.GUI.menuItem item = (menuItem) event.getItem();
		if ("newblock".equals(item.id)) 
		{
			Main.getEditor().getGui().showMenuCreateBlock();
		}
		if ("newSimpleBlock".equals(item.id)) 
		{
			Main.getEditor().getGui().createDefaultObject();
		}
		if ("newComplexeBlock".equals(item.id)) 
		{
			Main.getEditor().getGui().createComplexeObject();
		}
		if ("advancemod".equals(item.id)) 
		{
			if(!Main.getEditor().isAdvancedMode() && Main.getEditor().getObject()!=null)
			{
				Main.getEditor().passToAdvancedEdit();
			}
		}
		if ("normalmod".equals(item.id)) 
		{
			if(Main.getEditor().isAdvancedMode())
			{
				Main.getEditor().passToNormalEdit();
			}
		}
		if ("normalmod".equals(item.id)) 
		{
			if(Main.getEditor().isAdvancedMode())
			{
				Main.getEditor().passToNormalEdit();
			}
		}
		if ("stonetexture".equals(item.id)) 
		{
			Main.getEditor().setMat("Stone");
		}
		if ("dirttexture".equals(item.id)) 
		{
			Main.getEditor().setMat("Dirt");
		}
		if ("bedrocktexture".equals(item.id)) 
		{
			Main.getEditor().setMat("Bedrock");
		}
	}
};

