package com.example.hw_5.controllers.UI;

import com.example.hw_5.models.IssuesBooks;
import com.example.hw_5.services.IssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class IssuesControllerUI {

    private final IssueService issuerService;
    public IssuesControllerUI(IssueService issuerService) {
        this.issuerService = issuerService;
    }
    @GetMapping("/issues")
    public String issuesTable(Model model){
        List<IssuesBooks> issues = issuerService.getAllIssue();
        model.addAttribute("issues", issues);
        return "issues";
    }
    @GetMapping("/reader/{id}")
    public String issuesReaderTable(@PathVariable long id, Model model){
        List<IssuesBooks> issues = issuerService.getReaderIssue(id);
        model.addAttribute("readerIssues", issues);
        return "readerIssues";
    }
}
