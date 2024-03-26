package com.example.campuschool_backend.domain.lecture;

import com.example.campuschool_backend.domain.auditing.AuditingField;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ChatRoom extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String guest;

    protected ChatRoom() {}
    public ChatRoom of(String guest) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setGuest(guest);
        return chatRoom;
    }
}
