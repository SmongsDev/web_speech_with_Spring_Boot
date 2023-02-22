@RestController
@RequestMapping("/api")
public class SpeechSynthesisController {
    
    @PostMapping("/speak")
    public void speak(@RequestBody String text) throws Exception {
        Voice voice = new Voice("kevin16");
        voice.say(text);
    }
}