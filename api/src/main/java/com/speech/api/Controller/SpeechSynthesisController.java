package com.speech.api.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import net.sf.sociaal.freetts.Voice;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import org.springframework.stereotype.Controller;
// @RestController

@Controller
@RequestMapping("/api")
public class SpeechSynthesisController {
    
    private static final String VOICENAME_kevin = "kevin16";

    @PostMapping("/speak")
    public String speak(@RequestParam("text") String text) throws Exception {
        // Voice voice = new Voice("kevin16");
        // voice.say(text);
        // voice.allocate();
        // voice.speak("Hello, world!");
        // System.out.println("음성: " + voice.getOutputQueue().toString());
        // voice.save(new File("output.wav"));
        // voice.deallocate();
        System.out.println(text);
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICENAME_kevin);
        try{
            voice.allocate();
            voice.speak(text);
        } catch(NullPointerException e){
            System.out.println(e);
        }
        return "redirect:/speak";
    }
}