package com.example.campuschool_backend.domain.lecture;

import com.example.campuschool_backend.domain.auditing.AuditingField;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Chat extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String receiver;
    @Setter
    @Getter
    private String content;

    protected Chat() {}
    private Chat of(String receiver, String content) {
        Chat chat = new Chat();
        chat.setReceiver(receiver);
        chat.setContent(content);
        return chat;
    }


}
