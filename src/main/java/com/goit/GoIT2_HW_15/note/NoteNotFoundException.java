package com.goit.GoIT2_HW_15.note;

public class NoteNotFoundException extends Exception {
    public NoteNotFoundException() {
        super("Note not found");
    }
}
