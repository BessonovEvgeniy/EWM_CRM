package evg.testt.util.fullcalendar;

import evg.testt.model.FullcalendarEvent;
import evg.testt.model.GroupEvent;

import java.util.ArrayList;
import java.util.List;

public class FullcalendarHeleper {
    public static FullcalendarEvent convertGroupEventToFullcalendarEvent(GroupEvent groupEvent){
        FullcalendarEvent fullcalendarEvent = new FullcalendarEvent();
        fullcalendarEvent.setColor(groupEvent.getRoom().getColor());
        fullcalendarEvent.setId(groupEvent.getId());
        fullcalendarEvent.setStart(groupEvent.getStartDate());
        fullcalendarEvent.setEnd(groupEvent.getEndDate());
        fullcalendarEvent.setTitle(groupEvent.getTitle());
        return fullcalendarEvent;
    }

    public static FullcalendarEvent convertGroupEventToFullcalendarEventWithUrl(GroupEvent groupEvent){
        FullcalendarEvent fullcalendarEvent = convertGroupEventToFullcalendarEvent(groupEvent);
        fullcalendarEvent.setUrl("/group/" + groupEvent.getGroupId() + "/info");
        return fullcalendarEvent;
    }

    public static GroupEvent convertFullcalendarEventToGroupEvent(FullcalendarEvent fullcalendarEvent){
        GroupEvent groupEvent = new GroupEvent();
        groupEvent.setId(fullcalendarEvent.getId());
        groupEvent.setTitle(fullcalendarEvent.getTitle());
        groupEvent.setStartDate(fullcalendarEvent.getStart());
        groupEvent.setEndDate(fullcalendarEvent.getEnd());
        return groupEvent;
    }

    public static List<FullcalendarEvent> convertGroupEventsToFullcalendarEvents(List<GroupEvent> groupEvents){
        List<FullcalendarEvent> fullcalendarEvents = new ArrayList<>();
        for(GroupEvent groupEvent : groupEvents){
            fullcalendarEvents.add(convertGroupEventToFullcalendarEvent(groupEvent));
        }
        return fullcalendarEvents;
    }

    public static List<FullcalendarEvent> convertGroupEventsToFullcalendarEventsWithUrls(List<GroupEvent> groupEvents){
        List<FullcalendarEvent> fullcalendarEvents = new ArrayList<>();
        for(GroupEvent groupEvent : groupEvents){
            fullcalendarEvents.add(convertGroupEventToFullcalendarEventWithUrl(groupEvent));
        }
        return fullcalendarEvents;
    }

    public static List<FullcalendarEvent> convertGroupEventsToFullcalendarEventsDefinedAsBadTime(List<GroupEvent> groupEvents){
        List<FullcalendarEvent> fullcalendarEvents = new ArrayList<>();
        for(GroupEvent groupEvent : groupEvents){
            FullcalendarEvent fullcalendarEvent = convertGroupEventToFullcalendarEvent(groupEvent);
            fullcalendarEvent.setColor("#ff0000");
            fullcalendarEvent.setOverlap(false);
            fullcalendarEvent.setRendering("background");
            fullcalendarEvents.add(fullcalendarEvent);
        }
        return fullcalendarEvents;
    }

    public static List<GroupEvent> convertFullcalendarEventsToGroupEvents(List<FullcalendarEvent> fullcalendarEvents){
        List<GroupEvent> groupEvents = new ArrayList<>();
        for(FullcalendarEvent fullcalendarEvent : fullcalendarEvents){
            groupEvents.add(convertFullcalendarEventToGroupEvent(fullcalendarEvent));
        }
        return groupEvents;
    }
}
