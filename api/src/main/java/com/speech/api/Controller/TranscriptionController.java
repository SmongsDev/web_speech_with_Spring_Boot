@RestController
@RequestMapping("/api")
public class TranscriptionController {
    
    @PostMapping("/transcribe")
    public String transcribe(@RequestBody String audioData) {
        // Web Speech API를 사용하여 오디오 데이터를 텍스트로 변환하는 로직을 구현합니다.
        return transcribedText;
    }
} 