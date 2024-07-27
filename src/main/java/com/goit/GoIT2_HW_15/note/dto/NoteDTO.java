package com.goit.GoIT2_HW_15.note.dto;

import com.goit.GoIT2_HW_15.note.Note;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NoteDTO {
    private long id;
    private String title;
    private String content;


    public static NoteDTO fromNote(Note note) {
        return NoteDTO.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    }

    public static Note fromDTO(NoteDTO noteDTO) {
        Note note = new Note();
        note.setId(noteDTO.getId());
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        return note;
    }
}
