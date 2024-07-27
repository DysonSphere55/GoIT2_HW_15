package com.goit.GoIT2_HW_15.note;

import ch.qos.logback.core.model.Model;
import com.goit.GoIT2_HW_15.note.dto.NoteDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Collection;

@RequiredArgsConstructor
@RequestMapping("/note")
@Controller
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView listAll() {
        ModelAndView modelAndView = new ModelAndView("note/list");
        modelAndView.addObject("notes", noteService.listAll());
        return modelAndView;
    }


    @GetMapping("/delete")
    public void deleteById(@RequestParam(name = "id") long id, HttpServletResponse resp) {
        try {
            noteService.deleteById(id);
            resp.sendRedirect("/note/list");
        } catch (NoteNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/edit/{id}")
    public ModelAndView getEditById(@PathVariable(name = "id") long id) {
        try {
            Note note = noteService.getById(id);
            ModelAndView modelAndView = new ModelAndView("/note/edit");
            modelAndView.addObject("id", id);
            modelAndView.addObject("title", note.getTitle());
            modelAndView.addObject("content", note.getContent());
            return modelAndView;
        } catch (NoteNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @PostMapping("/edit")
    public void postEditById(@RequestParam(name = "noteId") long id, @RequestParam(name = "noteTitle") String title,
                             @RequestParam(name = "noteContent") String content, HttpServletResponse resp) {

        NoteDTO noteDTO = NoteDTO.builder().id(id).title(title).content(content).build();

        try {
            noteService.update(NoteDTO.fromDTO(noteDTO));
            resp.sendRedirect("/note/list");
        } catch (IOException | NoteNotFoundException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/create")
    public ModelAndView getCreate() {
        Note note = new Note();
        noteService.add(note);
        long id = note.getId();
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("id", id);
        return modelAndView;
    }


    @PostMapping("/create")
    public void postCreate(@RequestParam(name = "noteId") long id, @RequestParam(name = "noteTitle") String title,
                             @RequestParam(name = "noteContent") String content, HttpServletResponse resp) {

        NoteDTO noteDTO = NoteDTO.builder().id(id).title(title).content(content).build();

        try {
            noteService.update(NoteDTO.fromDTO(noteDTO));
            resp.sendRedirect("/note/list");
        } catch (IOException | NoteNotFoundException e) {
            e.printStackTrace();
        }
    }


    @GetMapping()
    public ModelAndView idle() {
        createRandomNotes();
        ModelAndView modelAndView = new ModelAndView("note/index");
        return modelAndView;
    }


    private void createRandomNotes() {
        for (int i = 1; i < 6; i++) {
            Note note = new Note();
            note.setId(NoteIdGenerator.generateId());
            note.setTitle("title_"+i);
            note.setContent("content_"+i+" Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius" +
                    "egestas lacinia. Maecenas dignissim vel ipsum ut dignissim. Ut condimentum, orci at hendrerit" +
                    "luctus, orci sem dictum elit, ac sagittis.");
            noteService.add(note);
        }
    }
}
