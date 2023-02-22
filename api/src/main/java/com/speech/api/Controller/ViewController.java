package com.speech.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

@Controller
public class ViewController{

  @RequestMapping("/speak")
  public void speak(Model model){
    model.addAttribute("text", "Hello");
  }

}