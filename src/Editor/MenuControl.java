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
	   Main.getEditor().getGui().createDefaultObject();
   }
}
};

