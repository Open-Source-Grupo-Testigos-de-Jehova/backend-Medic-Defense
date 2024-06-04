package com.medicdefense.backend.consultation.domain.model.valueobjects;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalIssue;
import com.medicdefense.backend.consultation.domain.model.entities.MessageItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Messages {

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    private List<MessageItem> messageItemList;

    public Messages() {
        this.messageItemList = new ArrayList<>();
    }

    public void addMessageItem(MessageItem messageItem) {
        System.out.println("Adding message item");
        this.messageItemList.add(messageItem);
    }

    public void addMessageItems(LegalIssue legalIssue, String message, Long senderId) {
        System.out.println("Adding message item");
        MessageItem messageItem = new MessageItem(legalIssue, message, senderId);
        System.out.println("Message ID " + messageItem.getId());
        messageItemList.add(messageItem);
    }

    public MessageItem getLastMessageItem() {
        if (!messageItemList.isEmpty()) {
            return messageItemList.getLast();
        } else {
            System.out.println("Message list is empty");
            return null;
        }
    }

    public boolean hasMessages() {
        return !messageItemList.isEmpty();
    }
}
