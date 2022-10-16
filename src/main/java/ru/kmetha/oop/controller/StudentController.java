package ru.kmetha.oop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.oop.entity.Student;
import ru.kmetha.oop.service.StudentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("students", studentService.index());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("student", studentService.show(id));
        return "show";
    }

    @GetMapping("/new")
    public String newStudent(@ModelAttribute("student") Student student) {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("student", studentService.show(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute("student") Student student) {
        studentService.save(studentService.show(id));
        return "redirect:/students/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        studentService.delete(studentService.show(id));
        return "redirect:/students";
    }
}
