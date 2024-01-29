package com.example.hw_5.controllers.UI;

import com.example.hw_5.models.Reader;
import com.example.hw_5.services.ReaderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class ReaderControllerUI {
    private final ReaderService readerService;

    private ReaderControllerUI(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/readers")
    public String tableReader(Model model) {
        List<Reader> readers = readerService.getAllReader();
        model.addAttribute("readers", readers);
        return "readers";
    }
}
