package com.goit.GoIT2_HW_15.note;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {

    private final Map<Long, Note> noteStorage;

    public NoteService() {
        noteStorage = new HashMap<>();
    }

    public List<Note> listAll() {
        return new ArrayList<Note>(noteStorage.values());
    }

    public Note add(Note note) {
        long noteId;

        do {
            noteId = NoteIdGenerator.generateId();
        } while (noteStorage.get(noteId) != null);

        note.setId(noteId);
        noteStorage.put(noteId, note);
        return note;
    }

    public void deleteById(long id) throws NoteNotFoundException {
        if (!noteStorage.containsKey(id)) {
            throw new NoteNotFoundException();
        }
        noteStorage.remove(id);
    }

    public void update(Note note) throws NoteNotFoundException {
        if (!noteStorage.containsKey(note.getId())) {
            throw new NoteNotFoundException();
        }
        noteStorage.put(note.getId(), note);
    }

    public Note getById(long id) throws NoteNotFoundException {
        if (!noteStorage.containsKey(id)) {
            throw new NoteNotFoundException();
        }
        return noteStorage.get(id);
    }
}
